package support;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MessageReader implements Runnable {
    private static final Executor POOL = Executors.newFixedThreadPool(16);
    final private MessageClient client;
    private DataInputStream stream;
    private boolean connected = true;

    public MessageReader(MessageClient client, InputStream stream) {
        this.client = client;
        this.stream = new DataInputStream(stream);
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public void run() {
        while (this.isConnected()){
            try {
                int size = this.stream.readInt(); //size of reading in bytes
                byte[] message =  new byte[size];
                for (int x =0; x < size; x++){
                    message[x] = (byte)this.stream.read();}
                final String realMessage =  new String(message);
                Runnable runnable =  new Runnable() {
                    @Override
                    public void run() {
                        client.onMessage(realMessage);
                    }
                };
                POOL.execute(runnable);
            } catch (IOException e) {
                client.errorOnRead(e);
                e.printStackTrace();
            }
        }
    }
}

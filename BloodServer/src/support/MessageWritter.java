package support;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class MessageWritter implements Runnable {
    private MessageClient client;
    private DataOutputStream stream;
    private BlockingQueue<String> queue = new LinkedBlockingDeque<>();
    private volatile boolean connected = true;

    public MessageWritter(MessageClient client, OutputStream stream) {
        this.client = client;
        this.stream = new DataOutputStream(stream);
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void writeMessage(String message){
        this.queue.offer(message);
    }

    @Override
    public void run() {
        while (this.isConnected()){
            try {
                String message = this.queue.poll(1, TimeUnit.SECONDS);
                if (message != null){
                    byte[] bytes = message.getBytes();
                    this.stream.writeInt(bytes.length);
                    this.stream.write(bytes);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

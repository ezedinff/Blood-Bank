package support;

import java.io.IOException;
import java.net.Socket;

public class BaseMessageClient implements MessageClient {
    private Socket socket;
    private MessageReader reader;
    private MessageWritter writter;


    public BaseMessageClient(String host, int port) throws IOException {
       this(new Socket(host,port));
    }

    public BaseMessageClient(Socket socket) {
        try {
            this.socket = socket;
            this.reader =  new MessageReader(this,this.socket.getInputStream());
            this.writter = new MessageWritter(this,this.socket.getOutputStream());
            new Thread(this.reader).start();
            new Thread(this.writter).start();
        }catch (Exception e){

        }
    }

    public MessageReader getReader() {
        return reader;
    }

    public MessageWritter getWritter() {
        return writter;
    }

    public Socket getSocket() {
        return socket;
    }


    @Override
    public void errorOnWrite(Exception e) {
        System.out.println("an error happened while writing");
    }

    @Override
    public void errorOnRead(Exception e) {
            System.out.println("an error happened while reading");
    }

    public void writeMessage(String message){
        this.getWritter().writeMessage(message);
    }

    @Override
    public void onMessage(String message) {

    }
    public void disconnect(){
        this.reader.setConnected(false);
        this.writter.setConnected(false);
        try {
            Thread.sleep(1000);
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

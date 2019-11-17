package view;

import support.BaseMessageClient;

import java.io.IOException;

public class Client extends BaseMessageClient {
    private String message;

    public Client(String host, int port) throws IOException {
        super(host,port);
    }

    public void onMessage(String message){
        System.out.println(message);
        this.message = message;

    }
    public String getMessage(){
        return this.message;
    }
}

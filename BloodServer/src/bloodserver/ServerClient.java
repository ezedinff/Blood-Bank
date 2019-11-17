package bloodserver;

import database.JDBCSingleton;
import support.BaseMessageClient;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerClient extends BaseMessageClient {
    private Server server;

    public ServerClient(Socket socket, Server server) {
        super(socket);
        this.server = server;
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Got message from client");
        System.out.println(message);
        interpret(message);
        this.server.onMessage(message,this);
    }
    public void interpret(String command){
        String [] comm = command.split(";");
        if(comm[2].toUpperCase().equals("POST")){
            HashMap<String, String> sh = JDBCSingleton.getInstance().jsonParser(command);
            ArrayList<String> fieldValue =  new ArrayList<>();
            for(String key : sh.keySet()){
                //System.err.println(sh.get(key));
                fieldValue.add(sh.get(key));
            }
            JDBCSingleton jdbc =  JDBCSingleton.getInstance();
            jdbc.inserData(comm[0], fieldValue);
            //   sendMessage(JDBCSingleton.getInstance().selectData("donor", "ALL"));
        }else if(comm[2].toUpperCase().equals("GET")){
            HashMap<String, String> shs = JDBCSingleton.getInstance().jsonParser(command);
            //System.err.println(JDBCSingleton.getInstance().selectData("donor" ,shs.get("condition")));
            String table = comm[0].toString().trim();
            System.out.println(command);
            if(table.equals("donor")){
                //System.out.println(shs.get("condition"));
                this.writeMessage(JDBCSingleton.getInstance().selectData("donor" ,shs.get("condition")));
            }else if(table.equals("receiver")){
                this.writeMessage(JDBCSingleton.getInstance().selectData("receiver" ,shs.get("condition")));
            }
            else if(table.equals("stock")){
                this.writeMessage(JDBCSingleton.getInstance().selectData("stock" ,shs.get("condition")));
            }

        }
        else if(comm[2].toUpperCase().equals("LOGIN")){
            HashMap<String, String> shs = JDBCSingleton.getInstance().jsonParser(command);
            ArrayList<String> fieldValue =  new ArrayList<>();
            for(String key : shs.keySet()){
                System.err.println(shs.get(key));
                fieldValue.add(shs.get(key));
            }
            JDBCSingleton jdbc = JDBCSingleton.getInstance();
            this.writeMessage(jdbc.login(fieldValue));
        }
    }

}

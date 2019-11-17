/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodserver;

import database.JDBCSingleton;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author TOSHIBA
 */
public class MyClient implements Runnable {
    private Socket client;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private BufferedReader reader;
    
    
    public MyClient(Socket client){
        this.client = client;
    }
    
    
    @Override
    public void run() {
        try{
            inputStream =  new DataInputStream(client.getInputStream());
            outputStream =  new DataOutputStream(client.getOutputStream());
            String msgin,msgout ="";
            msgin = inputStream.readUTF();
            String[] arr = msgin.split(";");          
            while(!msgin.equals("disconnect")){
                System.out.println(msgin);
                /* if(arr[2].toUpperCase().equals("POST")){
                      HashMap<String, String> sh = jsonParser();
                    ArrayList<String> fieldValue =  new ArrayList<>();
                    for(String key : sh.keySet()){
                        System.err.println(sh.get(key));
                        fieldValue.add(sh.get(key));
                 }
                   JDBCSingleton jdbc =  JDBCSingleton.getInstance();
                    jdbc.inserData(arr[0], fieldValue);
                   //   sendMessage(JDBCSingleton.getInstance().selectData("donor", "ALL")); 
                }else if(arr[2].toUpperCase().equals("GET")){
                     HashMap<String, String> shs = jsonParser();  
                    //System.err.println(JDBCSingleton.getInstance().selectData("donor" ,shs.get("condition")));
                    sendMessage(JDBCSingleton.getInstance().selectData("donor" ,shs.get("condition")));    
                               
                }
                else if(arr[2].toUpperCase().equals("LOGIN")){                    
                    HashMap<String, String> shs = jsonParser();
                    ArrayList<String> fieldValue =  new ArrayList<>();
                    for(String key : shs.keySet()){
                        System.err.println(shs.get(key));
                        fieldValue.add(shs.get(key));
                 }
                      JDBCSingleton jdbc =  JDBCSingleton.getInstance();
                      sendMessage(jdbc.login(fieldValue));
                } 
               // System.out.println(JDBCSingleton.getInstance().selectData("receiver", "ALL")); */
           }
         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public void sendMessage(String message){
        try {
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (Exception e) {
        }
    }
    
}


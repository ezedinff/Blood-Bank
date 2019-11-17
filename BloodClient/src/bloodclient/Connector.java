/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodclient;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import view.Client;

/**
 *
 * @author TOSHIBA
 */
public class Connector extends Thread{
    
    private static Connector connector;
    private BufferedReader inputStream;
    private DataOutputStream outputStream;
    private static Client client;
    private static String msgin;
    private Connector(){
    
    }
    
    public static Connector getInstance(){
        if(connector == null){
            synchronized(Connector.class){
                if(connector == null){
                connector =  new Connector();
                client = client;
                }
          }
            
        }
            return connector;
    }

    public static Client getClient() {
        return client;
    }

    public static void setMsgin(String msgin) {
        Connector.msgin = msgin;
    }

    public static String getMsgin() {
        return msgin;
    }

    public static void setClient(Client client) {
        Connector.client = client;
    }

    public synchronized void sendMessage(String message){
        try {
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (Exception e) {
        }
    }
    public String jsonGenerator(String tableName,String[] fieldList, String[] fieldValue, String method){
       String json = null;
       String start = tableName + ";" +"{";
       String temp = "";
       String temp2 = "";
       String end = "}";
       if(fieldList.length > 0){
           for(int i= 0; i < fieldValue.length; i++){
               temp += "\"" + fieldList[i] + "\" " + ":" +"\"" + fieldValue[i] + "\""  + ",";
           }
        temp2  = start + temp ;
        json = temp2.substring(0,temp2.lastIndexOf(",")) + end + ";" + method;
       // String h = +  "\"" +  "method" + "\""  + ":"+ "\"" + method + "\"";
       }
       return json;
   }
    public synchronized String receiveMessage(){
        String msg = null;
        try {
            msg =  inputStream.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public ArrayList<String[]> parseDonorJSON(String JSON_DATA) throws ParseException, JSONException{
        JSONObject obj =  new JSONObject(JSON_DATA);
        JSONArray arr = obj.getJSONArray("donor");
        ArrayList<String[]> a =  new ArrayList<>();
        String[] fieldList = {"first_name","last_name","age","gender","user_id","weight","blood_type","donated_date",};
        for(int i=0 ; i < arr.length(); i++){
            String[] tempArr =  new String[fieldList.length];
            JSONObject data =  arr.getJSONObject(i);  
          tempArr[0] = data.getString("first_name");
          tempArr[1] = data.getString("last_name");
          tempArr[2] = data.getString("age");
          tempArr[3] = data.getString("gender");
          tempArr[4] = data.getString("user_id");
          tempArr[5] = data.getString("weight");
          tempArr[6] = data.getString("blood_type");
          tempArr[7] = data.getString("donated_date");
          a.add(tempArr);
        }
        return a;
    }

    public ArrayList<String[]> parseReceiverJSON(String JSON_DATA) throws ParseException, JSONException{
        JSONObject obj =  new JSONObject(JSON_DATA);
        JSONArray arr = obj.getJSONArray("receiver");
        ArrayList<String[]> a =  new ArrayList<>();
        String[] fieldList = {"first_name","last_name","gender","blood_type","age"};
        for(int i=0 ; i < arr.length(); i++){
            String[] tempArr =  new String[fieldList.length];
            JSONObject data =  arr.getJSONObject(i);
            tempArr[0] = data.getString("first_name");
            tempArr[1] = data.getString("last_name");
            tempArr[2] = data.getString("gender");
            tempArr[3] = data.getString("blood_type");
            tempArr[4] = data.getString("age");
            a.add(tempArr);
        }
        return a;
    }
    
        public ArrayList<String[]> parseStockJSON(String JSON_DATA) throws ParseException, JSONException{
        JSONObject obj =  new JSONObject(JSON_DATA);
        JSONArray arr = obj.getJSONArray("stock");
        ArrayList<String[]> a =  new ArrayList<>();
        String[] fieldList = {"blood_type","quantity"};
        for(int i=0 ; i < arr.length(); i++){
            String[] tempArr =  new String[fieldList.length];
            JSONObject data =  arr.getJSONObject(i);
            tempArr[0] = data.getString("blood_type");
            tempArr[1] = data.getString("quantity");
            a.add(tempArr);
        }
        return a;
    }
}

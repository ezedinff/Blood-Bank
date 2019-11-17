/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author TOSHIBA
 */
public class JDBCSingleton {
    
    private static JDBCSingleton  jdbc = null;
    private static PreparedStatement ps = null;
    private static Connection conn = null;
  private static final String[] receiverFields = {"first_name","last_name","gender","blood_type", "age"};
  private static final String[] donorFields = {"first_name","last_name","age","gender","user_id","weight","blood_type","donated_date"};
  private static final String[] stockField = {"blood_type","quantity"};
   
        private static String[] tables={
          "CREATE TABLE IF NOT EXISTS admin(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,first_name varchar(100),last_name varchar(100),user_name varchar(100),password varchar(100))"
          ,"CREATE TABLE IF NOT EXISTS donor(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,first_name varchar(100),last_name varchar(100),age varchar(100),gender varchar(100),user_id varchar(100),weight varchar(100),blood_type varchar(50),donated_date varchar(100))",
            "CREATE TABLE IF NOT EXISTS receiver(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT ,first_name varchar(100),last_name varchar(100),gender varchar(100),blood_type varchar(50),age varchar(100))",
            "CREATE TABLE IF NOT EXISTS event(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,title VARCHAR(100),description TEXT)",
            "CREATE TABLE IF NOT EXISTS stock(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,blood_type VARCHAR(100),quantity varchar(100))"
    
    };
    private JDBCSingleton(){}
    private void prepareFields(){
        
    }
    
    public static JDBCSingleton getInstance(){
        try {
                  if(jdbc == null){
                    synchronized(JDBCSingleton.class){
                        if(jdbc ==null){
                        jdbc =  new JDBCSingleton();
                        }
                    }
                } 
        
        } catch (Exception e) {
        }
        return jdbc;
    }
      private static Connection getConnection()throws ClassNotFoundException, SQLException  
          {  
                
              Connection con=null;  
              Class.forName("com.mysql.jdbc.Driver");  
              con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank?autoReconnect=true&useSSL=false", "root", "georgeberhanu");
              return con;  
                 
          } 
    public  void createTables(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
          connection = this.getConnection();
            for (int j=0; j<tables.length; j++){
                 preparedStatement = connection.prepareStatement(tables[j]);
                 System.out.print(preparedStatement.executeUpdate());
           }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void inserData(String tableName, ArrayList<String> fieldValue){
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       if(tableName.equals("donor")){
           System.err.println(fieldValue.get(6) );
       addBlood(fieldValue.get(6));
       }
       String start = "INSERT INTO  "   + tableName + "  VALUES(" ;
       String temp = "";
       for(int i = 0;  i <= fieldValue.size(); i++){
           temp += "?,";
       }
       String sql = start + temp;
       sql = sql.substring(sql.indexOf("I"), sql.lastIndexOf(",")) +  ")";
         try {
            connection = this.getConnection();
           preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, 0);
           for(int i = 0,j=2; i<fieldValue.size(); i++,j++){
               preparedStatement.setString(j, fieldValue.get(i));
           }
             System.out.println( preparedStatement.executeUpdate());
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     public String login(ArrayList<String> credentials){
         String admin = "false";
               Connection connection = null;
         PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM admin WHERE user_name ='" +credentials.get(1)+"' and password = '" +credentials.get(0)+"'";
         try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
                admin = "true";
          }
         } catch (Exception e) {
         }
            return admin;
    }
     
     public String selectData(String tableName, String condition){
         Connection connection = null;
         PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql  = null, json = "{" + "\"" + tableName + "\"" + ": [", temp = ""; 
        String[] arr = null;
        if(tableName  == "donor"){
            arr = donorFields;
        }
        else if(tableName == "receiver"){
        arr =receiverFields;
        }
         else if(tableName == "stock"){
        arr =stockField;
        }
       if(condition.toUpperCase().equals("ALL")){
          sql = "SELECT * FROM " + tableName ;
       }else{
           sql = "SELECT * FROM " + tableName + " WHERE " + condition;
       }
        try{
        connection = this.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String n = "";
            for(int i=0,j=2; i<arr.length; i++,j++){
                      n +=   "\"" +  arr[i] + "\"" + ":" + "\"" + resultSet.getString(j) + "\"" + ",";
            }
            
            temp += "{" +  n.substring(0, n.lastIndexOf(",")) + "},";
        }
        json = json +  temp.substring(0, temp.lastIndexOf(",")) + "]}";
        }catch(Exception e){
        e.printStackTrace();
        }
         return json;
     }
     public void addBlood(String bloodtype){
     Connection connection = null;
      PreparedStatement preparedStatement = null;
      int bloodQuantity = getBloodQuantity(bloodtype) + 1;
      String sql =  "UPDATE stock SET quantity = '" +bloodQuantity+ "' WHERE blood_type = '"+bloodtype+"'";
         try {
                   connection = this.getConnection();
                    preparedStatement = connection.prepareStatement(sql);  
                    preparedStatement.executeUpdate();
         } catch (Exception e) {
         }
     }
     public int getBloodQuantity(String bloodtype){
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int quantity = 0;
        String sql = "SELECT quantity FROM stock WHERE blood_type = '"+bloodtype+"'";
         try {
                   connection = this.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();  
        while(resultSet.next()){
            quantity = Integer.parseInt(resultSet.getString(3));
        }
        
         } catch (Exception e) {
         }
         return quantity;
     }
    public HashMap<String, String> jsonParser(String msgin){
        HashMap<String, String> data =  new  HashMap<>();
        JSONArray arr = null;
        try {
            String [] arrMessage   = msgin.split(";");
            JSONParser parser =  new JSONParser();
            JSONObject json =  (JSONObject)parser.parse(arrMessage[1]);
            Set keys = json.keySet();
            for(Object key : keys){
                data.put(key.toString(), json.get(key).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}

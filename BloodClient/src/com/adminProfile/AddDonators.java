/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adminProfile;

import bloodclient.Connector;
import com.standards.StandardButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author TOSHIBA
 */
public class AddDonators extends JPanel {

    private JPanel information_panel;
    private JLabel first_name_label, last_name_label, gender_label, age_label, weight_label, blood_type_label, user_id_label;
    private JTextField first_name_edt, last_name_edt, age_edt, weight_edt, user_id_field;
    private JComboBox gender_box, blood_type_box;
    private StandardButton donate_btn;

    public AddDonators() {
        super.setLayout(null);

        information_panel = new JPanel();
        information_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 50));

        first_name_label = new JLabel("First Name");
        first_name_label.setFont(new Font("arial", Font.PLAIN, 20));
        information_panel.add(first_name_label);

        first_name_edt = new JTextField(30);
        first_name_edt.setFont(new Font("arial", Font.PLAIN, 18));
        information_panel.add(first_name_edt);

        last_name_label = new JLabel("Last Name");
        last_name_label.setFont(first_name_label.getFont());
        information_panel.add(last_name_label);

        last_name_edt = new JTextField(30);
        last_name_edt.setFont(first_name_edt.getFont());
        information_panel.add(last_name_edt);

        gender_label = new JLabel("gender     ");
        gender_label.setFont(first_name_label.getFont());
        information_panel.add(gender_label);

        gender_box = new JComboBox();
        gender_box.setPreferredSize(new Dimension(430, 50));
        gender_box.addItem("Select your gender");
        gender_box.addItem("Male");
        gender_box.addItem("Female");
        gender_box.setFont(first_name_label.getFont());
        information_panel.add(gender_box);

        age_label = new JLabel("Age        ");
        age_label.setFont(first_name_label.getFont());
        information_panel.add(age_label);

        age_edt = new JTextField(30);
        age_edt.setFont(first_name_edt.getFont());
        information_panel.add(age_edt);

        weight_label = new JLabel("Weight    ");
        weight_label.setFont(first_name_label.getFont());
        information_panel.add(weight_label);

        weight_edt = new JTextField(30);
        weight_edt.setFont(first_name_edt.getFont());
        information_panel.add(weight_edt);

        blood_type_label = new JLabel("Blood type ");
        blood_type_label.setFont(first_name_label.getFont());
        information_panel.add(blood_type_label);

        blood_type_box = new JComboBox();
        blood_type_box.setPreferredSize(gender_box.getPreferredSize());
        blood_type_box.addItem("Select your blood type");
        blood_type_box.addItem("A");
        blood_type_box.addItem("B");
        blood_type_box.addItem("AB");
        blood_type_box.addItem("O");
        blood_type_box.setFont(gender_box.getFont());
        information_panel.add(blood_type_box);

        user_id_label = new JLabel("User id     ");
        user_id_label.setFont(first_name_label.getFont());
        information_panel.add(user_id_label);

        user_id_field = new JTextField(30);
        user_id_field.setFont(first_name_edt.getFont());
        information_panel.add(user_id_field);

        JLabel hiden_label = new JLabel("                          ");
        hiden_label.setFont(first_name_label.getFont());
        information_panel.add(hiden_label);

        donate_btn = new StandardButton("  Donate");
        donate_btn.setFont(new Font("arial", Font.PLAIN, 50));
        donate_btn.setPreferredSize(new Dimension(200, 50));
        information_panel.add(donate_btn);
        donate_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String first_name_value = first_name_edt.getText().toString();
                String last_name_value = last_name_edt.getText().toString();
                String gender_value = gender_box.getSelectedItem().toString();
                String user_id = user_id_field.getText().toString();
                int age_value = 0;
                float weight_value = 0.0f;
                try {
                    age_value = Integer.valueOf(age_edt.getText().toString());
                } catch (NumberFormatException ex) {

                }

                try {
                    weight_value = Float.valueOf(weight_edt.getText());
                } catch (Exception ef) {

                }
                String blood_type_value = blood_type_box.getSelectedItem().toString();
                if (first_name_value.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your name");

                } else if (last_name_value.equals("")) {
                    JOptionPane.showMessageDialog(null, "please enter your last name");

                } else if (gender_value.equals("Select your gender")) {
                    JOptionPane.showMessageDialog(null, "please select your gender");

                } else if (age_value == 0) {
                    JOptionPane.showMessageDialog(null, "please enter your age");

                } else if (age_value < 18) {
                    JOptionPane.showMessageDialog(null, "Your age must be greater than 18");
                } else if (weight_value == 0.0f) {
                    JOptionPane.showMessageDialog(null, "please enter your weight");

                } else if (weight_value < 45.0) {
                    JOptionPane.showMessageDialog(null, "Your weight must be greater than 45");
                } else if (blood_type_value.equals("Select your blood type")) {
                    JOptionPane.showMessageDialog(null, "please select your blood type");

                } else if (user_id.equals("")) {
                    JOptionPane.showMessageDialog(null, "please add donor user id");
                } else {
//                 ArrayList<String[]> ds = Connector.getInstance().parseDonorJSON(this.datas);
                    SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy/mm/dd");
                    Date date =  new Date();
                    String todays = dateFormat.format(date);
                    String[] fieldList = {"firstName","lastName","gender","user_id","weight","bloodType","age","donated_date"};
                    String[] fieldValue = {first_name_value, last_name_value , age_edt.getText(), gender_value, user_id,weight_edt.getText(),blood_type_value,todays};
                    Connector.getClient().writeMessage(Connector.getInstance().jsonGenerator("donor",fieldList, fieldValue,"post"));
//                    registerDonator(first_name_value, last_name_value, gender_value, age_value, weight_value, blood_type_value,user_id);
                }

            }
        });

        information_panel.setBounds(200, 10, 800, 800);
        add(information_panel);

    }

  /*  public void registerDonator(String fname, String lname, String gender, int age, float weight, String blood_type,String user_id) {
        if(!checkUserID(user_id)){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "insert into users(first_name,last_name,user_id,age,gender,weight,blood_type ,user_name,password ,role,registration_date) values(?,?,?,?,?,?,?,?,?,?,?)";
        Calendar calender = Calendar.getInstance();
        int current_date = calender.get(Calendar.DAY_OF_MONTH);
        int current_month = calender.get(Calendar.MONTH);
        int current_year = calender.get(Calendar.YEAR);
        String registration_date = current_date + "/" + current_month + "/" + current_year;
        

        try {
            connection = DriverManager.getConnection(GetDBConnection.URL);
            ps = connection.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, user_id);
            ps.setInt(4, age);
            ps.setString(5, gender);
            ps.setFloat(6, weight);
            ps.setString(7, blood_type);
            ps.setString(8, "nouser");
            ps.setString(9, "nopass");
            ps.setString(10, "donor");
            ps.setString(11, registration_date);

            int id = ps.executeUpdate();
            if (id == 1) {
                insertDonorTable(user_id, blood_type, registration_date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null, user_id+" is registered before please change the user id");
        }
    }
    
    
    public boolean checkUserID(String user_id){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String query="select * from users where user_id='"+user_id+"'";
        try{
            conn=DriverManager.getConnection(GetDBConnection.URL);
            ps=conn.prepareStatement(query);
            rs= ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void insertDonorTable(String id, String blod_type, String registration_date) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "insert into donor(user_id,blood_type,donated_date) values(?,?,?)";
        try {
            connection = DriverManager.getConnection(GetDBConnection.URL);
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, blod_type);
            ps.setString(3, registration_date);
            int result = ps.executeUpdate();
            if (result == 1) {
                JLabel success_label = new JLabel("Thank you for registering to our system. donate a blood and save life. ");
                success_label.setForeground(Color.red);
                success_label.setFont(first_name_label.getFont());
                information_panel.removeAll();
                information_panel.add(success_label);
                information_panel.repaint();
                information_panel.revalidate();
            }
        } catch (Exception ex) {

        }
    } */

}

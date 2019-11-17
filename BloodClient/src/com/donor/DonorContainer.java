/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donor;


import bloodclient.Connector;
import com.standards.StandardButton;
import com.standards.StandardTextField;
import org.json.JSONException;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author TOSHIBA
 */
public class DonorContainer extends JPanel {

    private SpringLayout layout;
    private StandardTextField user_id_field;
    private StandardButton search_btn, add_donor;
    private JLabel full_name_label, age_label, weight_label, blood_type_label, user_id_label;
    private JLabel full_name_value, age_value, weight_value, blood_type_value, user_id_value;

    public DonorContainer() {

        layout = new SpringLayout();
        setLayout(layout);

        user_id_field = new StandardTextField(50, 50, 50);
        user_id_field.setPlaceholder("user id");
        layout.putConstraint(SpringLayout.WEST, user_id_field, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, user_id_field, 50, SpringLayout.NORTH, this);
        add(user_id_field);

        search_btn = new StandardButton(" Search ");
        search_btn.setFont(new Font("arial", Font.PLAIN, 20));
        search_btn.setPreferredSize(new Dimension(250, 50));
        layout.putConstraint(SpringLayout.WEST, search_btn, 550, SpringLayout.WEST, user_id_field);
        layout.putConstraint(SpringLayout.NORTH, search_btn, 50, SpringLayout.NORTH, this);
        add(search_btn);

        full_name_label = new JLabel("Full Name: ");
        full_name_label.setFont(new Font("arial", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, full_name_label, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, full_name_label, 150, SpringLayout.NORTH, search_btn);
        add(full_name_label);

        full_name_value = new JLabel();
        full_name_value.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, full_name_value, 250, SpringLayout.WEST, full_name_label);
        layout.putConstraint(SpringLayout.NORTH, full_name_value, 150, SpringLayout.NORTH, search_btn);
        add(full_name_value);

        age_label = new JLabel(" Age ");
        age_label.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, age_label, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, age_label, 70, SpringLayout.NORTH, full_name_label);
        add(age_label);

        age_value = new JLabel();
        age_value.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, age_value, 250, SpringLayout.WEST, age_label);
        layout.putConstraint(SpringLayout.NORTH, age_value, 70, SpringLayout.NORTH, full_name_value);
        add(age_value);

        weight_label = new JLabel("Weight: ");
        weight_label.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, weight_label, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, weight_label, 70, SpringLayout.NORTH, age_label);
        add(weight_label);

        weight_value = new JLabel();
        weight_value.setFont(full_name_value.getFont());
        layout.putConstraint(SpringLayout.WEST, weight_value, 250, SpringLayout.WEST, weight_label);
        layout.putConstraint(SpringLayout.NORTH, weight_value, 70, SpringLayout.NORTH, age_value);
        add(weight_value);

        blood_type_label = new JLabel("Blood Type:");
        blood_type_label.setFont(weight_label.getFont());
        layout.putConstraint(SpringLayout.WEST, blood_type_label, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, blood_type_label, 70, SpringLayout.NORTH, weight_label);
        add(blood_type_label);

        blood_type_value = new JLabel();
        blood_type_value.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, blood_type_value, 250, SpringLayout.WEST, blood_type_label);
        layout.putConstraint(SpringLayout.NORTH, blood_type_value, 70, SpringLayout.NORTH, weight_label);
        add(blood_type_value);

        user_id_label = new JLabel("user ID");
        user_id_label.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, user_id_label, 450, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, user_id_label, 70, SpringLayout.NORTH, blood_type_label);
        add(user_id_label);

        user_id_value = new JLabel();
        user_id_value.setFont(full_name_label.getFont());
        layout.putConstraint(SpringLayout.WEST, user_id_value, 250, SpringLayout.WEST, user_id_label);
        layout.putConstraint(SpringLayout.NORTH, user_id_value, 70, SpringLayout.NORTH, blood_type_label);
        add(user_id_value);

      add_donor = new StandardButton("");
        //add_donor.setPreferredSize(new Dimension(300, 50));
        //layout.putConstraint(SpringLayout.WEST, add_donor, 250, SpringLayout.WEST, full_name_label);
        //layout.putConstraint(SpringLayout.NORTH, add_donor, 70, SpringLayout.NORTH, user_id_value);
        add(add_donor);

        search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_id_value = user_id_field.getText().toString();
                if (user_id_value.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter user id of donor");
                } else {
                    String condition = "user_id =" + '\'' + user_id_value + '\'';
                    String message = "donor;" + "{" + "\"" + "condition" + "\"" + ":" + "\"" + condition + "\"" + "};GET";
                    Connector.getClient().writeMessage(message);
                    try {
                        ArrayList<String[]> arr = Connector.getInstance().parseDonorJSON(Connector.getClient().getMessage());
                        full_name_value.setText(arr.get(0)[0] + " " + arr.get(0)[1]);
                        age_value.setText(arr.get(0)[2]);
                        weight_value.setText(arr.get(0)[5]);
                        blood_type_value.setText(arr.get(0)[6]);
                        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy/mm/dd");
                        Date date =  new Date();
                        String todays = dateFormat.format(date);
                        String day = arr.get(0)[7];
                        Calendar cal1 =  new GregorianCalendar();
                        Calendar cal2 =  new GregorianCalendar();
                        cal1.setTime(dateFormat.parse(todays));
                        cal2.setTime(dateFormat.parse(day));
                        int numDays = daysBetween(cal2.getTime(),cal1.getTime());
                        if (numDays > 90){
                            user_id_label.setText("you can donate!");
                        }else{
                            user_id_label.setText("Thank you for your voluntarism. you can not donate now. ");
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    //  findDonator(user_id_value);
                }
            }
        });

        add_donor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_ids=user_id_value.getText().toString();
                String blood_type=blood_type_value.getText().toString();
                //addToDonators(user_ids,blood_type);
            }
        });

    }

    public int daysBetween(Date d1, Date d2){
        return (int)((d2.getTime() - d1.getTime())/ (1000 * 60 * 60 * 24));
    }
  
    public void paintComponent(Graphics g) {
        g.setColor(Color.decode("#2D94DF"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}

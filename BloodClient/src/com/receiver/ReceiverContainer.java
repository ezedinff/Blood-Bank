/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.receiver;

import bloodclient.Connector;
import com.standards.StandardButton;
import view.Client;

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
import java.util.Calendar;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Meseret
 */
public class ReceiverContainer extends JPanel{
    private JPanel information_panel;
    private JLabel first_name_label, last_name_label, gender_label, age_label, weight_label, blood_type_label;
    private JTextField first_name_edt, last_name_edt, age_edt, weight_edt;
    private JComboBox gender_box, blood_type_box;
    private StandardButton donate_btn;
    public ReceiverContainer() {
     super.setLayout(null);

        information_panel = new JPanel();
        information_panel.setBackground(Color.decode("#2D94DF"));
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

        JLabel hiden_label = new JLabel("                          ");
        hiden_label.setFont(first_name_label.getFont());
        information_panel.add(hiden_label);

        donate_btn = new StandardButton("  Registered");
        donate_btn.setFont(new Font("arial", Font.PLAIN, 50));
        donate_btn.setPreferredSize(new Dimension(200, 50));
        information_panel.add(donate_btn);
        donate_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connector con =  Connector.getInstance();
                String first_name_value = first_name_edt.getText().toString();
                String last_name_value = last_name_edt.getText().toString();
                String gender_value = gender_box.getSelectedItem().toString();
                int age_value = 0;
                try {
                    age_value = Integer.valueOf(age_edt.getText().toString());
                } catch (NumberFormatException ex) {

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
                } else {
                    String[] fieldList = {"firstName","lastName","gender","age","bloodType"};
                    String[] fieldValue = {first_name_value, last_name_value , gender_value, age_edt.getText(), blood_type_value};    
                  //  con.sendMessage(Connector.getInstance().jsonGenerator("receiver",fieldList, fieldValue,"post"));
                    Connector.getClient().writeMessage(Connector.getInstance().jsonGenerator("receiver",fieldList, fieldValue,"post"));
                    // registerDonator(first_name_value, last_name_value, gender_value, age_value,blood_type_value);
                }

            }
        });

        information_panel.setBounds(500, 10, 800, 800);
        add(information_panel);

    }

   

    public void paintComponent(Graphics g) {
        g.setColor(Color.decode("#2D94DF"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
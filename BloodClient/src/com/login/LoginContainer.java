/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import bloodclient.Connector;
import com.adminProfile.AdminProfile;
import com.homepage.FrontPage;
import com.homepage.FrontPageContainer;
import com.standards.StandardButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author TOSHIBA
 */
public class LoginContainer extends JPanel {

    private SpringLayout layout;
    private JLabel login_info;
    private JLabel user_name, password, error_label;
    private JTextField user_name_field;
    private JPasswordField password_field;
    private StandardButton login_btn;

    public LoginContainer() {

       layout = new SpringLayout();

        login_info = new JLabel("Login");
        login_info.setForeground(Color.WHITE);
        login_info.setFont(new Font("arial", Font.BOLD, 50));

        layout.putConstraint(SpringLayout.WEST, login_info, 350, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, login_info, 20, SpringLayout.NORTH, this);
        add(login_info);

        user_name = new JLabel("User name");
        user_name.setFont(new Font("arial", Font.PLAIN, 20));

        layout.putConstraint(SpringLayout.WEST, user_name, 220, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, user_name, 100, SpringLayout.NORTH, login_info);
        add(user_name);

        user_name_field = new JTextField(30);
        layout.putConstraint(SpringLayout.WEST, user_name_field, 150, SpringLayout.WEST, user_name);
        layout.putConstraint(SpringLayout.NORTH, user_name_field, 120, SpringLayout.NORTH, this);
        add(user_name_field);

        password = new JLabel("Password");
        password.setFont(new Font("arial", Font.PLAIN, 20));

        layout.putConstraint(SpringLayout.WEST, password, 220, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, password, 100, SpringLayout.NORTH, user_name);
        add(password);

        password_field = new JPasswordField(30);
        layout.putConstraint(SpringLayout.WEST, password_field, 150, SpringLayout.WEST, password);
        layout.putConstraint(SpringLayout.NORTH, password_field, 100, SpringLayout.NORTH, user_name_field);
        add(password_field);

        error_label = new JLabel();
        error_label.setFont(new Font("arial", Font.PLAIN, 20));
        error_label.setForeground(Color.red);
        layout.putConstraint(SpringLayout.WEST, error_label, 150, SpringLayout.WEST, password);
        layout.putConstraint(SpringLayout.NORTH, error_label, 50, SpringLayout.NORTH, password_field);
        add(error_label);

        login_btn = new StandardButton("   Login");
        login_btn.setPreferredSize(new Dimension(200, 45));
        layout.putConstraint(SpringLayout.WEST, login_btn, 150, SpringLayout.WEST, password);
        layout.putConstraint(SpringLayout.NORTH, login_btn, 100, SpringLayout.NORTH, password_field);
        add(login_btn);

        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_name_value = user_name_field.getText();
                String password_value = password_field.getText();
                if (user_name_value.equals("")) {
                    error_label.setText("Please enter your user name");
                } else if (password_value.equals("")) {
                    error_label.setText("Please enter your password");
                } else {
                    String message = "admin;{" + "\"" + "username" + "\"" + ":" + "\"" + user_name_value + "\"" + "," + "\"" + "password" + "\"" + ":" + "\"" + password_value + "\"" + "};LOGIN";
                    Connector.getClient().writeMessage(message);
                    String response = Connector.getClient().getMessage();
                    if (response != null) {
                        if (response.equals("true")) {
                            Window window = SwingUtilities.getWindowAncestor(LoginContainer.this);
                            FrontPage page = (FrontPage) window;
                            page.setVisible(false);
                            AdminProfile profile = new AdminProfile();
                            profile.setVisible(true);
                        } else {
                            error_label.setText("Unknown user");
                        }
                    }
                }
            }
        });

        super.setLayout(layout);

    }

    /*public void LoginAdmin(String user_name,String password){
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String query="select * from users where user_name='"+user_name+"' and password='"+password+"'";
        try{
            connection=DriverManager.getConnection(GetDBConnection.URL);
            ps= connection.prepareStatement(query);
            
            rs= ps.executeQuery();
            if(rs.next()){
                Window window=SwingUtilities.getWindowAncestor(this);
                FrontPage page=(FrontPage)window;
                page.setVisible(false);
                
                AdminProfile profile=new AdminProfile();
                profile.setVisible(true);
            }else{
                error_label.setText("Unknown user");
            }
        }catch(Exception e){
            
        }
    }*/
    public void paintComponent(Graphics g) {
        g.setColor(Color.decode("#2D94DF"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}

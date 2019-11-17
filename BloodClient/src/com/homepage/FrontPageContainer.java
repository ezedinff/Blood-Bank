/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homepage;

import com.donor.DonorContainer;
import com.login.LoginContainer;
import com.receiver.ReceiverContainer;
import com.standards.StandardButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author TOSHIBA
 */
public class FrontPageContainer  extends JPanel{
    
    //creating the red panel at the top of this container and initializing north panel components
    //==========================================================================================
  private JPanel north_panel=new JPanel(){
      public void paintComponent(Graphics g){
          g.setColor(Color.decode("#EC3627"));
          g.fillRect(0, 0, getWidth(), getHeight());
    } 
  };
  private JPanel donate_receive_buttons_panel=new JPanel(){
      public void paintComponent(Graphics g){
          g.setColor(Color.decode("#EC3627"));
          g.fillRect(0, 0, getWidth(), getHeight());
    } 
  };
  
  private JLabel motto_label;
  private StandardButton home_btn,admin_login,donate_btn,receive_btn;
  
    //end of ceating and initializing north panel components
    //==========================================================================================
  
  
  //adding center or image slider panel to the center of this container
  //=================================================================
  
  private CenterImageSlider image_slider;
  private JPanel center_panel;
  //end of image slider panel
  //===================================================================
  
  
    public FrontPageContainer() {
        setLayout(new BorderLayout());
        
        
        //adding the red panel to north of this panel,and adding the north panel components to north panel
        //===============================================================================================
        north_panel.setPreferredSize(new Dimension(200,80));
        north_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        north_panel.setLayout(new BorderLayout(50,20));
        
        motto_label=new JLabel("Donate Blood and save life");
        motto_label.setFont(new Font("arial",Font.BOLD,29));
        motto_label.setForeground(Color.WHITE);
        
        donate_receive_buttons_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        home_btn=new StandardButton("  Home");
        home_btn.setFont(new Font("arial",Font.PLAIN,50));
        home_btn.setPreferredSize(new Dimension(200, 50));
        home_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CenterImageSlider slider=new CenterImageSlider();
                addComponent(slider);
            }
        });
        donate_receive_buttons_panel.add(home_btn);
        
        
        admin_login=new StandardButton("Admin login");
        admin_login.setFont(new Font("arial",Font.PLAIN,50));
        admin_login.setPreferredSize(new Dimension(300, 50));
        admin_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginContainer loginContainer =  new LoginContainer();
                addComponent(loginContainer);
            }
        });
        donate_receive_buttons_panel.add(admin_login);
        
        donate_btn=new StandardButton("  Donate");
        donate_btn.setFont(new Font("arial",Font.PLAIN,50));
        donate_btn.setPreferredSize(new Dimension(200, 50));
        donate_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DonorContainer donor =  new DonorContainer();
                addComponent(donor);
            }
        });
        donate_receive_buttons_panel.add(donate_btn);
        
        receive_btn=new StandardButton("  Receive");
        receive_btn.setFont(new Font("arial",Font.PLAIN,50));
        receive_btn.setPreferredSize(new Dimension(200, 50));
        receive_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ReceiverContainer receiver =  new ReceiverContainer();
                addComponent(receiver);
            }
        });
        donate_receive_buttons_panel.add(receive_btn);
        
        north_panel.add(motto_label,BorderLayout.WEST);//adding motto label to west at north panel
        north_panel.add(donate_receive_buttons_panel,BorderLayout.EAST);//adding donate and receive panel to east north panel
        
        
        add(north_panel,BorderLayout.NORTH);
        //end of adding north panel to this panel and end of adding nroth panel components to north panel
        //===============================================================================================
        
        
        //initializing image slider panel and adding to the center of this container
        //=========================================================================
        center_panel =new JPanel();
        center_panel.setLayout(new BorderLayout());
        image_slider=new CenterImageSlider();
        addComponent(image_slider);
        add(center_panel,BorderLayout.CENTER);
        //end of adding image slider panel to center of this panel
        //======================================================
        
        
    }
    
    public void addComponent(JComponent component){
        center_panel.removeAll();
        center_panel.add(component,BorderLayout.CENTER);
        center_panel.repaint();
        center_panel.revalidate();
    }
    
    
    public void paintComponent(Graphics g){
         
    }
    
    public  void closeFrame(){
       Window window=SwingUtilities.getWindowAncestor(this);
       FrontPage page=(FrontPage)window;
       page.setVisible(false);
    }
    
}

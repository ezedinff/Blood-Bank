/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adminProfile;

import bloodclient.Connector;
import com.homepage.FrontPage;
import com.standards.RoundButton;
import com.standards.StandardButton;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import org.json.JSONException;


/**
 *
 * @author TOSHIBA
 */
public class AdminContainer extends JPanel{
 private JPanel left_panel=new JPanel(){
     
       public void paintComponent(Graphics g) {
        g.setColor(Color.decode("#2D94DF"));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
       
 };
 private JPanel centerpanel=new JPanel(){
      public void paintComponent(Graphics g) {
            Color c = Color.decode("#d0cdc6");
           
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.gray);
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            Dimension d = getSize();
            AffineTransform ct = AffineTransform.getTranslateInstance(d.width / 2,
                    d.height * 3 / 4);
            g2d.transform(ct);

            String s = "Blooad management";
            Font f = new Font("nyala", Font.PLAIN, 128);
            g2d.setFont(f);

            int count = 6;
            for (int i = 1; i <= count; i++) {
                AffineTransform oldTransform = g2d.getTransform();

                float ratio = (float) i / (float) count;
                g2d.transform(AffineTransform.getRotateInstance(Math.PI
                        * (ratio - 1.0f)));
                float alpha = ((i == count) ? 1.0f : ratio / 3);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        alpha));
                g2d.drawString(s, -450, 10);

                g2d.setTransform(oldTransform);
            }
        }
 };
 private RoundButton admin_image;
 private JLabel admin_name;
 private SpringLayout left_panel_layout;
 private StandardButton add_donator,show_donator,show_receivers,add_blood_type,logout;
    public AdminContainer() {
      
        setLayout(new BorderLayout());
        
        left_panel.setPreferredSize(new Dimension(400,500));
        left_panel_layout=new SpringLayout();
        left_panel.setLayout(left_panel_layout);
        
        admin_image=new RoundButton("Admin");
        admin_image.setPreferredSize(new Dimension(170, 150));
        admin_image.setFont(new Font("arial",Font.BOLD,30));
        admin_image.setForeground(Color.decode("#2D94DF"));
        admin_image.setBackground(Color.WHITE);
        left_panel_layout.putConstraint(SpringLayout.WEST, admin_image, 100, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, admin_image, 20, SpringLayout.NORTH, left_panel);
        left_panel.add(admin_image);
        
        admin_name=new JLabel("George");
        admin_name.setFont(new Font("arial",Font.BOLD,50));
        admin_name.setForeground(Color.WHITE);
        left_panel_layout.putConstraint(SpringLayout.WEST, admin_name, 100, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, admin_name, 180, SpringLayout.NORTH, left_panel);
        left_panel.add(admin_name);
        
        add_donator=new StandardButton(" Add donors data ");
        add_donator.setFont(new Font("arial",Font.PLAIN,20));
        add_donator.setPreferredSize(new Dimension(400, 50));
        left_panel_layout.putConstraint(SpringLayout.WEST, add_donator, 0, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, add_donator, 150, SpringLayout.NORTH, admin_name);
        left_panel.add(add_donator);
        
        
        show_donator=new StandardButton(" Show donors");
        show_donator.setFont(add_donator.getFont());
        show_donator.setPreferredSize(add_donator.getPreferredSize());
        left_panel_layout.putConstraint(SpringLayout.WEST, show_donator, 0, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, show_donator, 70, SpringLayout.NORTH, add_donator);
        left_panel.add(show_donator);
        
        show_receivers=new StandardButton(" Show Receivers");
        show_receivers.setFont(add_donator.getFont());
        show_receivers.setPreferredSize(add_donator.getPreferredSize());
        left_panel_layout.putConstraint(SpringLayout.WEST, show_receivers, 0, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, show_receivers, 70, SpringLayout.NORTH, show_donator);
        left_panel.add(show_receivers);
        
        add_blood_type=new StandardButton("Stock");
        add_blood_type.setFont(add_donator.getFont());
        add_blood_type.setPreferredSize(add_donator.getPreferredSize());
        left_panel_layout.putConstraint(SpringLayout.WEST, add_blood_type, 0, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, add_blood_type, 70, SpringLayout.NORTH, show_receivers);
        left_panel.add(add_blood_type);
        add_blood_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String message = "stock;{" +"\"" + "condition" + "\"" + ":" + "\"" + "ALL" + "\"" + "};GET";
                Connector.getClient().writeMessage(message);
                System.err.println(Connector.getClient().getMessage());
           //     ShowStock showStock =  new ShowStock(Connector.getClient().getMessage());
              //  addCenterComponent(showStock);
            }
        });
        
        logout=new StandardButton(" Logout ");
        logout.setFont(add_donator.getFont());
        logout.setPreferredSize(add_donator.getPreferredSize());
        left_panel_layout.putConstraint(SpringLayout.WEST, logout, 0, SpringLayout.WEST, left_panel);
        left_panel_layout.putConstraint(SpringLayout.NORTH, logout, 70, SpringLayout.NORTH, add_blood_type);
        left_panel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Window window=SwingUtilities.getWindowAncestor(logout);
                AdminProfile profile=(AdminProfile)window;
                profile.setVisible(false);
                
                
                FrontPage page=new FrontPage();
                page.setVisible(true);
                
                
                
            }
        });
        
        centerpanel.setLayout(new BorderLayout());
        add(left_panel,BorderLayout.WEST);
        add(centerpanel,BorderLayout.CENTER);
        
        add_donator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDonators donators=new AddDonators();
                addCenterComponent(donators);
            }
        });
        
        show_donator.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String message =  "donor; {"+ "\"" + "condition" + "\"" +":" + "\"" + "ALL" + "\"" + "};GET";
                Connector.getClient().writeMessage(message);
                ShowDonators donators=new ShowDonators(Connector.getClient().getMessage());
                addCenterComponent(donators);

              //  }
            }
        });
        show_receivers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message =  "receiver; {"+ "\"" + "condition" + "\"" +":" + "\"" + "ALL" + "\"" + "};GET";
                Connector.getClient().writeMessage(message);
                ShowReceivers receivers=new ShowReceivers(Connector.getClient().getMessage());
                addCenterComponent(receivers);
                   
            }
        });
        
        
        
        
        
    }  
    
    public void addCenterComponent(JComponent component){
        centerpanel.removeAll();
        centerpanel.add(component,BorderLayout.CENTER);
        centerpanel.repaint();
        centerpanel.revalidate();
        
    }
    
  
    
}

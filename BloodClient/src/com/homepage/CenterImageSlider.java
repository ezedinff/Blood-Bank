/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homepage;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author TOSHIBA
 */
public class CenterImageSlider extends JPanel{
    JLabel pic;
    Timer tm;
    int x = 0;


    //Images Path In Array
    String[] list = {
                      "/com/images/image1.jpg",//0
                      "/com/images/image2.jpg",//1
                      "/com/images/image3.jpg",//2
                      "/com/images/image4.jpg",//3
                    };

    public CenterImageSlider() {
        pic=new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/com/images/image4.jpg"));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(2000,1000, Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
        tm = new Timer(5000,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 
            }
        });
        setLayout(new BorderLayout());
        add(pic,BorderLayout.CENTER);
       
        tm.start();
    }
    
    public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(this.getClass().getResource(list[i]));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
    
}

 


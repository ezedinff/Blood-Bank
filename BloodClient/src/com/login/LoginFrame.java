/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *#2D94DF
 * @author Meseret
 */
public class LoginFrame extends JDialog{
   private LoginContainer contentPane;
    public LoginFrame() {
        
        contentPane=new LoginContainer();
        super.setContentPane(contentPane);
        setSize(new Dimension(850, 500));
        setLocationRelativeTo(null);
        
    }
}

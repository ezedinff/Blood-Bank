/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adminProfile;

import javax.swing.JFrame;

/**
 *
 * @author TOSHIBA
 */
public class AdminProfile extends JFrame{
    private AdminContainer admin;
    public AdminProfile() {
        
        admin=new AdminContainer();
        setContentPane(admin);
        
        setSize(1700, 800);
        setLocationRelativeTo(null);
    }
    
    
    
}

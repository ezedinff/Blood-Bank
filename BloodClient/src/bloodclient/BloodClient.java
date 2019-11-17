/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodclient;

import com.homepage.FrontPage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import view.Getway;

/**
 *
 * @author TOSHIBA
 */
public class BloodClient {
  
    public static void main(String[] args) {
        Getway getway = new Getway();
        getway.setVisible(true);
    }
    
}

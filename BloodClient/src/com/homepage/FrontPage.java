/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homepage;

import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author TOSHIBA
 */
public class FrontPage  extends JFrame{
  private FrontPageContainer contentPane;
    public FrontPage() {
        
        contentPane=new FrontPageContainer();
        setContentPane(contentPane);
        setSize(1800,800);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }
    
    public  void closeFrame(){
       Window window=SwingUtilities.getWindowAncestor(contentPane);
       FrontPage page=(FrontPage)window;
       page.setVisible(false);
        
    }
    
    
    
}

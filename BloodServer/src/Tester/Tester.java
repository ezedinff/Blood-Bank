/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import database.JDBCSingleton;
import view.ServerContainer;

/**
 *
 * @author TOSHIBA
 */
public class Tester {
    
    public static void main(String[] args){
        ServerContainer container =  new ServerContainer();
        container.setVisible(true);
         JDBCSingleton singleton =  JDBCSingleton.getInstance();
    }
}

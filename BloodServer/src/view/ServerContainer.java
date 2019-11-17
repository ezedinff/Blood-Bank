/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bloodserver.BloodServer;
import bloodserver.StandardTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author TOSHIBA
 */
public class ServerContainer extends JDialog implements ActionListener {
   private StandardTextField portField;
   private JButton portButton;
   private BloodServer server;
    public ServerContainer() {
        super.setLayout(null);
        setSize(new Dimension(320, 300));
        portField =  new StandardTextField();
        portField.setPlaceholder("Enter the port");
        portField.setBounds(10, 10, 190, 40);
        portField.setCaretColor(Color.blue);
        add(portField);
        portButton   =  new JButton("Submit");
        portButton.setBounds(210, 10, 80, 40);
        portButton.addActionListener(this);
portButton.setBackground(Color.blue);
        add(portButton);
        server =  new BloodServer();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(portButton)) {
            if(portField.getText() != null){
                server.runServer(Integer.parseInt(portField.getText()));
                System.out.println("server started");
            }
        }
    }
   
   
}
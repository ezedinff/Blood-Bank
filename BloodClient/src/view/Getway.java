package view;

import bloodclient.Connector;
import com.homepage.FrontPage;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author TOSHIBA
 */
public class Getway extends JDialog{
    private JLabel  hostLabel;
    private JLabel portLabel;
    private JTextField hostField, portField;
    private JButton submit;

    public Getway() {
        setLayout(null);
        setSize(new Dimension(300, 200));
        setLocation(600, 200);
        hostLabel =  new JLabel("Host name:");
        hostLabel.setBounds(10, 10,100, 40);
        add(hostLabel);
        
        portLabel =  new JLabel("Port: ");
        portLabel.setBounds(10, 60,100, 40);
        add(portLabel); 
       
        hostField =  new JTextField("localhost");
        hostField.setBounds(120, 15,100, 30);
        add(hostField); 
        
        portField =  new JTextField("8000");
        portField.setBounds(120, 65,100, 30);
        add(portField); 
        
        submit =  new JButton("Start");
        submit.setBounds(130, 115,80, 30);
        add(submit); 
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    if (!portField.getText().equals(null) && !hostField.getText().equals(null)) {
                        try {
                            Client client =  new Client(hostField.getText().trim(),Integer.parseInt(portField.getText()));
                            Connector.setClient(client);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FrontPage frontPage =  new FrontPage();
                        frontPage.setVisible(true);
                }
            }
        });
    }
    


}

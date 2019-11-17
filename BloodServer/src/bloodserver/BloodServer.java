/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author TOSHIBA
 */
public class BloodServer {
    public void runServer(int port) {
        final Server server = new Server(port);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                server.start();
            };
        };
        new Thread(r).start();
    }

    
}

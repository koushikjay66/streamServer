/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koushik
 */
public class main {

    public static void main(String[] args) {
        try {
            
            /*
               This try block initializes the serverSocket method and server socket may throw an IO Exception. 
                ServerSocket Initializes a port and keeps listening to that port. To keep listening it needs to accept the request which is 
                done by ss.accept(); ss.accept returns an socket Object. 
                While loop is for running the server for infinite time.
            */
            ServerSocket ss = new ServerSocket(1140);
              
            while(true){
            Socket s = ss.accept();
            Threads t = new Threads("tuntni", s);
            t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

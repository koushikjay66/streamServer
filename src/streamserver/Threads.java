/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koushik
 */
public class Threads implements Runnable {

    private String t_name;
    private Socket s;

    public Threads(String t_name, Socket s) {
        this.t_name = t_name;
        this.s = s;
    }

    public void start() {
        Thread t = new Thread(this, t_name);
        t.start();
    }

    @Override
    public void run() {
        /*
            The first thing run object does is calling the readInput Method . readInput returns a string 
            containg all the reqested head. 
        */
        String h=readInput();
        Parser p = new Parser(h);
        p.parse();
        FileOperation fp = new FileOperation(p);
        p = null;
        
        if(fp.check()){
            fp.stream(s);
        }else{
            System.out.println("Invalid FileName");
        }
       // generateHeader();
    }// End of method run

    private String readInput() {
           String h="";
        while (true) {
            try {
                h=h+(char)s.getInputStream().read();
                if (s.getInputStream().available() == 0) {
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       // System.out.println(h);
        return h;
    }// End of method readInput

}// End of class threads

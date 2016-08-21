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
        readInput();
        generateHeader();
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
        System.out.println(h);
        return h;
    }// End of method readInput

    private void generateHeader() {
        String content = "getAll the things wyou ";
        
        
        try {
            byte [] f=Files.readAllBytes(new File("http://doridro.net/file/ILK17VFY/0c0e3e0defc3abda0ac870e4e4758e49/Bappa%20-%20Pori.mp3").toPath());
            String head = "HTTP/1.1 200 OK\r\nContent-Type: audio/mpeg\r\nContent-Length:" + f.length+ "\r\n\r\n";
            
            s.getOutputStream().write(head.getBytes());
            s.getOutputStream().write(f);
            s.close();
        } catch (Exception e) {

        }
    }

}// End of class threads

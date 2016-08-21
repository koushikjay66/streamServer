/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamserver;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koushik
 */
public class FileOperation {
    
    /**
     *
     */
    
    private Path p;
    private double start=0.0d;
    
    public FileOperation(Parser p){
        this.p =Paths.get(p.requestedFile+".mp3");
        this.start=p.start;
        p = null;
    }
    
    public boolean check(){
        File f = new File(p.toString());
        System.out.println(p.toString());
        if(!f.exists() || f.isDirectory()){
            return false;
        }
        
        return true;
    }
    
    public void stream(Socket s){
        try {
            byte [] f=Files.readAllBytes(p);
            int streamFrom =(int)(start*f.length)/100;
            String head = "HTTP/1.1 200 OK\r\nContent-Type: audio/mpeg\r\nContent-Length:" + (f.length-streamFrom)+ "\r\n\r\n";
            s.getOutputStream().write(head.getBytes());
            byte temp[]=Arrays.copyOfRange(f, streamFrom, f.length);
            System.out.println(temp.length);
            s.getOutputStream().write(temp);
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

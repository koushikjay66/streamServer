/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Koushik
 */
public class Parser {

    public String requestedMethod;
    public String requestedFile;
    public double httpVersion;
    public double start ;

    private String Pstr[];
    private int pointer = 0;

    public Parser(String str) {

        /*
            This constructor is responsible for split all the header Lines and it only works with the first Line of the header. 
            Firstly it splits the lines by using \r\n
            Then from the first line it determines which method is requested
            The URL can also contain the time length from which to start.
            The requested start time entity comes from the get requested. 
            Solution is easy, just need to parse the input in the first line 
         */
        
        this.Pstr = str.split("\r\n");
    }// End of constructor

    public String parse() {

        /*
            temp array is consisted to taking all the argument by spliting the with space respect to the first line of the given header
         */
        String temp[] = Pstr[pointer].split(" ");
        pointer++;

        if (temp.length != 3) {
            return "-1";
        }

        requestedMethod = temp[0];

        /*
            The below segment of code is parsing the reqiested file name with the starting position of the audio file. 
            Parsing is done through java regex.
         */
        Pattern p = Pattern.compile("^(/(\\w+))(\\?((start)=(\\d+.\\d+)))?");
        Matcher m = p.matcher(temp[1]);

        if (m.find()) {
            if (m.group(1) != null) {
                requestedFile = m.group(2);

                if (m.group(5) != null && m.group(6) != null) {
                    start = Double.parseDouble(m.group(6));
                }

            } else {
                System.out.println("Error 404");
            }
        } else {
            System.out.println("Kon file chas ko");
        }


        /*
            The below line is responsible for parsing the HTTp version which comes in to form HTTP/1.1 for example.
         */
        httpVersion = Double.parseDouble(temp[2].split("/")[1]);

        return "";

    }// End of method parse

}//End of class Parser

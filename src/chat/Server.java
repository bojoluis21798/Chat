/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Server implements Runnable{
    static ServerSocket echoServer = null;
    static DataInputStream is;
    static PrintStream os;
    static Socket clientSocket = null;
    static Scanner keyin = null;
    static String name = "";
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String response;
        try {
            while((response = is.readUTF()) != null){
                System.out.println("\n"+response);
                System.out.print(name+": ");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main (String[] args){
        
        try {
           echoServer = new ServerSocket(8080);
           
           clientSocket = echoServer.accept();
           is = new DataInputStream(clientSocket.getInputStream());
           os = new PrintStream(clientSocket.getOutputStream());
           keyin = new Scanner(System.in);
           
           System.out.print("Enter name of server: ");
           name = keyin.nextLine();
           Thread t = new Thread(new Server());
           t.start();
           while(true){
                System.out.print(name+": ");
                String line = keyin.nextLine();

                if(line.equals("exit")){
                    os.close();
                    is.close(); 
                    echoServer.close();
                    break;
                }

                os.println(name+": "+line);
                
            } 
           
        }catch (IOException e) {
           System.out.println(e);
        }
    }
}


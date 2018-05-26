/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import app.Main;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
    public String name = "";
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String response;
        try {
            while((response = is.readUTF()) != null){
                Main.appendMessage(response);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void sendMessage(String line){
        try{
            if(line.equals("exit")){
                os.close();
                is.close(); 
                echoServer.close();
            }

            os.println(name+": "+line);
                
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void start (){
        try{
            echoServer = new ServerSocket(8080);
            System.out.println("HERE");
            clientSocket = echoServer.accept();
            System.out.println("HERE2");
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        Thread t = new Thread(this);
        t.start();               
    }
}


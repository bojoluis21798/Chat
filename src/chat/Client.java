/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import app.Main;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author student
 */
public class Client implements Runnable{
    private Socket s = null; 
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Scanner keyin = null;
    private String name = "";

    public Client(){
        
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            String response; 
            while((response = in.readLine()) != null){
                Main.appendMessage(response);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void sendMessage(String line){
        try {
            if(line.equals("exit")){
                        out.close();
                        in.close(); 
                        s.close();
            }
            out.writeUTF(name+": "+line);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void start (){
       
                try{
                    System.out.println("Here");
                    this.s = new Socket("localhost", 8080);
                    this.out = new DataOutputStream(this.s.getOutputStream());
                    this.in = new DataInputStream(this.s.getInputStream());
                    //this.keyin = new Scanner(System.in);
                }catch(IOException e){
                    System.out.println(e);
                }

                //System.out.println(name);
                //System.out.println("sent");
                Thread t = new Thread(this);
                t.start();
       
        
    }

    
}

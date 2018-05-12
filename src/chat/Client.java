/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

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
public class Client {
    public static void main (String[] args){
        Socket s = null; 
        DataOutputStream out = null;
        DataInputStream in = null;
        Scanner keyin = null;
        try{
            s = new Socket("127.0.0.1", 8080);
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());
            keyin = new Scanner(System.in);
        }catch(IOException e){
            System.out.println(e);
        }
        
        if (s != null && in != null && out != null && keyin != null) {
            try{
                System.out.print("Enter name for client: ");
                String name = keyin.nextLine();
                System.out.println(name);
                //System.out.println("sent");
                while(true){
                    String response;
                    while((response = in.readLine()) != null)
                        System.out.println(response);
                    
                    System.out.print(name+": ");
                    String line = keyin.nextLine();
                    
                    if(line.equals("exit")){
                        out.close();
                        in.close(); 
                        s.close();
                        break;
                    }
                    out.writeUTF(name+": "+line);
                }
            
            }catch(UnknownHostException e){
                System.out.println(e);
            }catch(IOException e){
                System.out.println(e);
            }
        }
        
    }
}

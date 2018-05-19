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
public class Client implements Runnable{
        private static Socket s = null; 
        private static DataOutputStream out = null;
        private static DataInputStream in = null;
        private static Scanner keyin = null;
        private static String name = "";
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            String response; 
            while((response = in.readLine()) != null){
                System.out.println("\n"+response);
                System.out.print(name+": ");
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void start (){
        
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
                name = keyin.nextLine();
                //System.out.println(name);
                //System.out.println("sent");
                Thread t = new Thread(new Client());
                t.start();
                while(true){
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

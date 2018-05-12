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

/**
 *
 * @author student
 */
public class Server {
    public static void main (String[] args){
        ServerSocket echoServer = null;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
        Scanner keyin = null;
        try {
           echoServer = new ServerSocket(8080);
           
           clientSocket = echoServer.accept();
           is = new DataInputStream(clientSocket.getInputStream());
           os = new PrintStream(clientSocket.getOutputStream());
           keyin = new Scanner(System.in);
           
           System.out.print("Enter name of server: ");
           String name = keyin.nextLine();
           
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
                String response;
                while((response = is.readLine()) != null)
                    System.out.println(response);
            } 
           
        }catch (IOException e) {
           System.out.println(e);
        }
    }
}


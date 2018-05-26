
package app;
import chat.Client;
import chat.Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bojo Alcisto
 */
public class Main {
    private static Client client = null;
    private static Server server = null;
    //private static String text = "";
    private static EnterName en = null;
    private static ChatWindow cw = null;
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EnterName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                en = new EnterName();
                en.setVisible(true);
            }
        });
    }
    
    public static void switchWindow(){
        cw = new ChatWindow();
        en.setVisible(false);
        cw.setVisible(true);
    }
    
    public static void start(){
        if(active().equals("CLIENT")){
            client.start();
        }else if(active().equals("SERVER")){
            server.start();
        }
    }
    
    public static String active(){
        String type;
        if(client == null && server != null){
            type = "SERVER";
        }else if(client != null && server == null){
            type = "CLIENT";
        }else{
            type = "";
        }
        return type;
    }
    
    public static void initClient(){
        client = new Client();
    }
    
    public static void initServer(){
        server = new Server();
    }
    
    public static void appendMessage(String s){
        cw.appendMessage(s);        
    }
    
    public static void sendMessage(String s){
        if(active().equals("CLIENT")){
            client.sendMessage(s);
        }else if(active().equals("SERVER")){
            server.sendMessage(s);
        }
    }
    
    public static String getActiveName(){
        String name = "";
        if(active().equals("CLIENT")){
            name = client.getName();
        }else if(active().equals("SERVER")){
            name = server.getName();
        }
        return name;
    }
    
    public static Client getClient(){
        if(client == null){
            initClient();
        }
        return client;
    }
    
    public static Server getServer(){
        if(server == null){
            initServer();
        }
        return server;
    }
}

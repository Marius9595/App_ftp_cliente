/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FPT;

import static FPT.Config.password;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Mario
 */
public class Connection_FTP {
    
        static private FTPClient client;
        
        public Connection_FTP(String server, String user,  String password){
                        
            try {
                this.client = new  FTPClient();
                this.client.connect(server);
                
                boolean login = this.client.login(user, password);
                
                if(!login){
                    System.out.println("Login Incorrecto");
                    this.client.disconnect();
                    System.exit(1);
                }
            } catch (IOException ex) {
                Logger.getLogger(Connection_FTP.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        
        public static FTPClient get_connection(){
            

            return new Connection_FTP(Config.server, Config.user, Config.password).client;     
        }
        
    
}

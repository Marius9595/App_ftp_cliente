/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FPT;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Mario
 */
public class Controller_FTP_files {
    
    private FTPClient client = Connection_FTP.get_connection(Config.server, Config.user, Config.password);
    
     
    public void upload_files(String directory, HashMap<String, String> files){
        
        try {
            client.changeWorkingDirectory(directory);
            
            client.setFileType(FTP.BINARY_FILE_TYPE);
            
            
            for (String file_name : files.keySet()){
                
                String file_path = files.get(file_name);
                
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file_path + "\\" + file_name));
            
                client.retrieveFile(file_name, out); 
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller_FTP_files.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public void download_files(String directory, HashMap<String, String> files){
        
        try {
            client.changeWorkingDirectory(directory);
       
            for (String file_name : files.keySet()){
                
                String file_path = files.get(file_name);
                
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file_path + "\\" + file_name));
            
                client.storeFile(file_name, in); 
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller_FTP_files.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void rename_files(String directory, HashMap<String, String> files){
        
        try {
            client.changeWorkingDirectory(directory);
            
            
            for (String old_name : files.keySet()){
                
                String new_name = files.get(old_name);
                
                client.rename(old_name, new_name);
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller_FTP_files.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void delete_files(String directory, ArrayList<String> files){
        
        try {
            client.changeWorkingDirectory(directory);
            
            for (String file : files) {
                
                client.deleteFile(file);
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller_FTP_files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

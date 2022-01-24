/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FPT;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Mario
 */
public class Controller_FTP_files {
    
    private FTPClient client;
    
    public Controller_FTP_files(FTPClient client){
        this.client = client;
    }
    
     
    public void upload_files(File[] files){
        BufferedInputStream in = null;
        try {      
            client.setFileType(FTP.BINARY_FILE_TYPE);
            
            for (File file : files){              
                in  = new BufferedInputStream(new FileInputStream(file));           
                client.storeFile(file.getName(), in); 
            }
            in.close();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
    } 
    
    public void download_files(String directory_destiny, ArrayList<FTPFile> files){
        
        try {
            BufferedOutputStream out = null; 
            
            for (FTPFile file : files){
                
                out = new BufferedOutputStream(new FileOutputStream(directory_destiny + "\\" + file.getName()+"_descargado"));
                client.retrieveFile(file.getName(), out); 
            }
            out.close();

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

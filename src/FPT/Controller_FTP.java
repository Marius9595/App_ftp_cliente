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
public class Controller_FTP {
    
    private FTPClient client;
    
    public Controller_FTP(FTPClient client){
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
            Logger.getLogger(Controller_FTP.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controller_FTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void delete_files(ArrayList<String> files){
        
        try {

            for (String file : files) {             
                client.deleteFile(file);
            }

        } catch (IOException ex) {
            Logger.getLogger(Controller_FTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    public void create_folder(String absolute_path_directory){
        
        try {
            client.makeDirectory(absolute_path_directory);
        } catch (IOException ex) {
            Logger.getLogger(Controller_FTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void delete_folders(ArrayList<String> absolute_paths_directory){
        
        
        for (String path : absolute_paths_directory) {
            try {
                client.removeDirectory(path);
            } catch (IOException ex) {
                Logger.getLogger(Controller_FTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }


}

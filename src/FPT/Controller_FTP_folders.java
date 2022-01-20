/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FPT;

import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Mario
 */
public class Controller_FTP_folders {
    private FTPClient client = Connection_FTP.get_connection(Config.server, Config.user, Config.password);
}

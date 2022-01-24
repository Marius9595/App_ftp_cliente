
import FPT.Config;
import FPT.Connection_FTP;
import FPT.Controller_FTP_files;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mario
 */
public class Home extends javax.swing.JFrame {

    FTPClient cliente_ftp = Connection_FTP.get_connection();
    Controller_FTP_files controller_files;
    
    FTPFile[] files;
    
    String current_directory = "";
    HashMap<String,Integer> info_directory;
    
    
    
    /**
     * Creates new form Home
     */
    public Home() {
        
        
        initComponents();
        
        controller_files = new Controller_FTP_files(cliente_ftp);
        
        list_directorio.addMouseListener(new MouseListener());
        
        //iniciar parametros servidor FTP
        try {
            lb_directorio_raiz.setText(cliente_ftp.printWorkingDirectory());
            lb_ip_server.setText(Config.server);
            lb_usuario.setText(Config.user);
            
            put_info_textblock();
            fill_JList();
 
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void put_info_textblock() {
        
        try {
            info_directory = get_info_directorio();
            
            current_directory =  cliente_ftp.printWorkingDirectory();
            
            txtp_localizacion_directorio.setText( "INFO DIRECTORY: "
                    + " Files: " + info_directory.get("num_files")
                    + ", Directories: " + info_directory.get("num_directories")
                    + " Total: " + info_directory.get("total")
            );
            
            txtp_info_directorio.setText("DIRECTORY: " + current_directory);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private HashMap<String,Integer> get_info_directorio() throws IOException{
        
        HashMap<String,Integer> info_directory = new HashMap<String,Integer>();
        
        FTPFile[] content = cliente_ftp.listFiles();
        

        int num_files = 0;
        int num_directories = 0;
        int total = 0;
        
        for (FTPFile fTPFile : content) {
            
            //fTPFile.setType(fTPFile.DIRECTORY_TYPE);//dsfads
            //cliente_ftp.makeDirectory(current_directory);/adsfadsf
            
            if (fTPFile.isDirectory()) {              
                num_directories++;
            }else if(fTPFile.isFile()) {
                num_files++;
            }
            total++;          
        }
        
        info_directory.put("num_files", num_files);
        info_directory.put("num_directories", num_directories);
        info_directory.put("total", total);
        
        return info_directory;
        
        
    }
    
    private void fill_JList(){
        
        try {
            files = cliente_ftp.listFiles();
            
            DefaultListModel<String> modelo = new DefaultListModel();
            
            modelo.add(0,cliente_ftp.printWorkingDirectory());

            for (FTPFile file : files) {
                
                if (file.isDirectory()){
                    modelo.addElement("   //--- " + file.getName() + " ---//");
                }else{
                    modelo.addElement("   " + file.getName());
                }
            }
            list_directorio.setModel(modelo);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private class MouseListener extends MouseAdapter{
        
        public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {

            try {
                // Double-click detected
                int index = list.locationToIndex(evt.getPoint());

                if(index == 0){
                    
                    cliente_ftp.changeToParentDirectory();
                    txtp_localizacion_directorio.setText(cliente_ftp.printWorkingDirectory());
                    
                }else{
                    FTPFile File = files[index-1];
                    if(File.isDirectory()){
                        cliente_ftp.changeWorkingDirectory(File.getName());
                    }
                }
                
   
                files = cliente_ftp.listFiles();
                put_info_textblock();
                fill_JList();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        } else if (evt.getClickCount() == 3) {

            // Triple-click detected
            int index = list.locationToIndex(evt.getPoint());
        }
    }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lb_ip_server = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_directorio_raiz = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_usuario = new javax.swing.JLabel();
        btn_subir_fichero = new javax.swing.JButton();
        btn_descargar_fichero = new javax.swing.JButton();
        btn_eliminar_fichero = new javax.swing.JButton();
        btn_crear_carpeta = new javax.swing.JButton();
        btn_eliminar_carpeta = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_directorio = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtp_info_directorio = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtp_localizacion_directorio = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Users");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Servidor FTP:");

        lb_ip_server.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lb_ip_server.setText("jLabel2");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("DIRECTORIO RAIZ:");

        lb_directorio_raiz.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lb_directorio_raiz.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Usuario:");

        lb_usuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lb_usuario.setText("jLabel4");

        btn_subir_fichero.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_subir_fichero.setText("Subir fichero");
        btn_subir_fichero.setToolTipText("");
        btn_subir_fichero.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_subir_fichero.setMinimumSize(new java.awt.Dimension(150, 32));
        btn_subir_fichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_subir_ficheroActionPerformed(evt);
            }
        });

        btn_descargar_fichero.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_descargar_fichero.setText("Descargar fichero");
        btn_descargar_fichero.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_descargar_fichero.setMinimumSize(new java.awt.Dimension(150, 32));
        btn_descargar_fichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_descargar_ficheroActionPerformed(evt);
            }
        });

        btn_eliminar_fichero.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_eliminar_fichero.setText("Eliminar fichero");
        btn_eliminar_fichero.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_eliminar_fichero.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_crear_carpeta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_crear_carpeta.setText("Crear carpeta");
        btn_crear_carpeta.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_crear_carpeta.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_eliminar_carpeta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_eliminar_carpeta.setText("Eliminar carpeta");
        btn_eliminar_carpeta.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_eliminar_carpeta.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_salir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_salir.setMinimumSize(new java.awt.Dimension(150, 32));

        list_directorio.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(list_directorio);

        txtp_info_directorio.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jScrollPane2.setViewportView(txtp_info_directorio);

        txtp_localizacion_directorio.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jScrollPane3.setViewportView(txtp_localizacion_directorio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lb_ip_server)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_usuario)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lb_directorio_raiz)))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_crear_carpeta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_eliminar_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_descargar_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_subir_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_eliminar_carpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lb_ip_server)
                    .addComponent(jLabel3)
                    .addComponent(lb_usuario))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lb_directorio_raiz))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_subir_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_descargar_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_eliminar_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_crear_carpeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_eliminar_carpeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_subir_ficheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_subir_ficheroActionPerformed
 
        final JFileChooser file_chooser = new JFileChooser();
        File[] files;
        
        file_chooser.setMultiSelectionEnabled(true);
        file_chooser.setApproveButtonText("Subir Archivos");
        file_chooser.setDialogTitle("Subir Archivos");
        
        if (file_chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            
            files = file_chooser.getSelectedFiles();
            controller_files.upload_files(files);
        } else {
            System.out.println("Open command cancelled by user.");
        }     
    }//GEN-LAST:event_btn_subir_ficheroActionPerformed

    private void btn_descargar_ficheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_descargar_ficheroActionPerformed
        
        final JFileChooser file_chooser = new JFileChooser();
        ArrayList<FTPFile> files_to_download = new ArrayList<FTPFile>();
        
        for (int index : list_directorio.getSelectedIndices()) {
            
            files_to_download.add(this.files[index-1]);
        }

        file_chooser.setApproveButtonText("Selección de carpeta de destino");
        file_chooser.setDialogTitle("Descargar");
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (file_chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            
            File directory_destiny = file_chooser.getSelectedFile();
            controller_files.download_files(directory_destiny.getAbsolutePath(),files_to_download);
        } else {
            System.out.println("Open command cancelled by user.");
        }
        
    }//GEN-LAST:event_btn_descargar_ficheroActionPerformed

      
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_crear_carpeta;
    private javax.swing.JButton btn_descargar_fichero;
    private javax.swing.JButton btn_eliminar_carpeta;
    private javax.swing.JButton btn_eliminar_fichero;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_subir_fichero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_directorio_raiz;
    private javax.swing.JLabel lb_ip_server;
    private javax.swing.JLabel lb_usuario;
    private javax.swing.JList<String> list_directorio;
    private javax.swing.JTextPane txtp_info_directorio;
    private javax.swing.JTextPane txtp_localizacion_directorio;
    // End of variables declaration//GEN-END:variables
}

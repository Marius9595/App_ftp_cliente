
import FPT.Config;
import FPT.Connection_FTP;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
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

    FTPClient cliente_ftp = Connection_FTP.get_connection(Config.server, Config.user, Config.password);
    FTPFile[] files;
    
    /**
     * Creates new form Home
     */
    public Home() {
        
        initComponents();
        
        //iniciar parametros servidor FTP
        try {
            lb_directorio_raiz.setText(cliente_ftp.printWorkingDirectory());
            lb_ip_server.setText(Config.server);
            lb_usuario.setText(Config.user);
   
            fill_JList();
  
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void fill_JList(){
        
        try {
            files = cliente_ftp.listFiles();
            
            DefaultListModel<String> modelo = new DefaultListModel();
            
            modelo.add(0,cliente_ftp.printWorkingDirectory());

            for (FTPFile file : files) {
                modelo.addElement("   " + file.getName());
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

            // Double-click detected
            int index = list.locationToIndex(evt.getPoint()) + 1;
            
            FTPFile File = files[index];
            
            File.isDirectory()
            
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

        btn_subir_fichero.setText("Subir fichero");
        btn_subir_fichero.setToolTipText("");
        btn_subir_fichero.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_subir_fichero.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_descargar_fichero.setText("Descargar fichero");
        btn_descargar_fichero.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_descargar_fichero.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_eliminar_fichero.setText("Eliminar fichero");
        btn_eliminar_fichero.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_eliminar_fichero.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_crear_carpeta.setText("Crear carpeta");
        btn_crear_carpeta.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_crear_carpeta.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_eliminar_carpeta.setText("Eliminar carpeta");
        btn_eliminar_carpeta.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_eliminar_carpeta.setMinimumSize(new java.awt.Dimension(150, 32));

        btn_salir.setText("Salir");
        btn_salir.setMaximumSize(new java.awt.Dimension(150, 32));
        btn_salir.setMinimumSize(new java.awt.Dimension(150, 32));

        list_directorio.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(list_directorio);

        jScrollPane2.setViewportView(txtp_info_directorio);

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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_crear_carpeta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_eliminar_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_descargar_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_subir_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_eliminar_carpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lb_directorio_raiz))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lb_ip_server)
                            .addComponent(jLabel3)
                            .addComponent(lb_usuario))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lb_directorio_raiz))
                        .addGap(47, 47, 47)
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
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

      
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

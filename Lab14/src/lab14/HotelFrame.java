/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab14;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Thomas
 */
public class HotelFrame extends javax.swing.JFrame {

    /**
     * Creates new form HotelFrame
     */
    public HotelFrame() {
         File f;
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileNameExtensionFilter(null, ".db"));
        if(jfc.showOpenDialog(HotelFrame.this)==JFileChooser.APPROVE_OPTION){
            f = jfc.getSelectedFile();
            pfadAkt = f;
            tm = new HotelTableModel(f.toString());
        }
        initComponents();
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new DateRenderer());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new DateEditor());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        itemDelete = new javax.swing.JMenuItem();
        itemNew = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        save = new javax.swing.JMenuItem();

        popupMenu.setComponentPopupMenu(popupMenu);
        popupMenu.setInvoker(jTable1);

        itemDelete.setText("Zeile löschen");
        itemDelete.setOpaque(true);
        itemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeleteActionPerformed(evt);
            }
        });
        popupMenu.add(itemDelete);

        itemNew.setText("Neue Zeile hinzufügen");
        itemNew.setOpaque(true);
        itemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewActionPerformed(evt);
            }
        });
        popupMenu.add(itemNew);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(tm);
        jTable1.setComponentPopupMenu(popupMenu);
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        jMenu1.setText("File");

        save.setText("Save as");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jMenu1.add(save);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        File f;
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileNameExtensionFilter(null, ".db"));
        if(jfc.showOpenDialog(HotelFrame.this)==JFileChooser.APPROVE_OPTION){
            f = jfc.getSelectedFile();   
            HotelReadWrite.writeHotelList(f.toString(), tm.getData());
        }
    }//GEN-LAST:event_saveActionPerformed

    private void itemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeleteActionPerformed
        int row =jTable1.getSelectedRow();
        tm.getData().get(row).setIstGeloescht(true);
        tm.getAllHotels().get(row).setIstGeloescht(true);
        tm.getData().remove(row);
        tm.fireTableRowsDeleted(row, row);
    }//GEN-LAST:event_itemDeleteActionPerformed

    private void itemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewActionPerformed
        List<String> l = tm.getLocations();
        String []s = new String[l.size()];
        InsertDialog d = new InsertDialog(HotelFrame.this, true,  l.toArray(s));
        d.setVisible(true);
        Hotel h =  d.getH();
        if(h != null)
            tm.addHotel(pfadAkt.toString(),h);
    }//GEN-LAST:event_itemNewActionPerformed

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
            java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelFrame().setVisible(true);
            }
        });
    }
    
    private File pfadAkt;
    private HotelTableModel tm;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JMenuItem itemNew;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem save;
    // End of variables declaration//GEN-END:variables
}
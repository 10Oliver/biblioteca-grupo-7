/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

/**
 *
 * @author Oliver
 */
public class prestamo extends javax.swing.JPanel {

    /**
     * Creates new form prestamos
     */
    public prestamo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnPrestamo = new javax.swing.JButton();
        btnDevoluciones = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1030, 640));
        setMinimumSize(new java.awt.Dimension(1030, 640));
        setLayout(new java.awt.GridBagLayout());

        btnPrestamo.setText("Préstamo");
        btnPrestamo.setPreferredSize(new java.awt.Dimension(300, 75));
        btnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        add(btnPrestamo, gridBagConstraints);

        btnDevoluciones.setText("Devoluciones");
        btnDevoluciones.setPreferredSize(new java.awt.Dimension(300, 75));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        add(btnDevoluciones, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrestamoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDevoluciones;
    private javax.swing.JButton btnPrestamo;
    // End of variables declaration//GEN-END:variables
}

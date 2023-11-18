/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

/**
 *
 * @author Oliver
 */
public class realizarPrestamo extends javax.swing.JPanel {

    /**
     * Creates new form prestamo
     */
    public realizarPrestamo() {
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

        lblTitulo = new javax.swing.JLabel();
        pnlSeleccionUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblFechaEntrega = new javax.swing.JLabel();
        txtFechaEntrega = new javax.swing.JTextField();
        lblUsuarioSelecionado = new javax.swing.JLabel();
        txtUsuarioSeleccionado = new javax.swing.JTextField();
        btnBuscarUsuario = new javax.swing.JButton();
        pnlSeleccionProductos = new javax.swing.JPanel();
        lblIndiciacionProducto = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        cmbTipoProducto = new javax.swing.JComboBox<>();
        btnBuscarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        pnlSeleccionFinalizado = new javax.swing.JPanel();
        btnRegistrarPrestamo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1030, 640));
        setPreferredSize(new java.awt.Dimension(1030, 640));

        lblTitulo.setText("Realizar préstamo");

        pnlSeleccionUsuario.setBackground(new java.awt.Color(255, 255, 255));

        lblUsuario.setText("Ingresa el nombre del usuario:");

        lblFechaEntrega.setText("Ingresa la fecha de entrega para el préstamo");

        lblUsuarioSelecionado.setText("Usuario seleccionado:");

        txtUsuarioSeleccionado.setMinimumSize(new java.awt.Dimension(250, 22));
        txtUsuarioSeleccionado.setPreferredSize(new java.awt.Dimension(250, 22));

        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSeleccionUsuarioLayout = new javax.swing.GroupLayout(pnlSeleccionUsuario);
        pnlSeleccionUsuario.setLayout(pnlSeleccionUsuarioLayout);
        pnlSeleccionUsuarioLayout.setHorizontalGroup(
            pnlSeleccionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeleccionUsuarioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlSeleccionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSeleccionUsuarioLayout.createSequentialGroup()
                        .addComponent(lblUsuarioSelecionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuarioSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFechaEntrega)
                    .addComponent(lblUsuario)
                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSeleccionUsuarioLayout.createSequentialGroup()
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarUsuario)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        pnlSeleccionUsuarioLayout.setVerticalGroup(
            pnlSeleccionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeleccionUsuarioLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSeleccionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarUsuario))
                .addGap(33, 33, 33)
                .addGroup(pnlSeleccionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioSelecionado)
                    .addComponent(txtUsuarioSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(lblFechaEntrega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pnlSeleccionProductos.setBackground(new java.awt.Color(255, 255, 255));

        lblIndiciacionProducto.setText("Buscar el producto a prestar:");

        cmbTipoProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSeleccionProductosLayout = new javax.swing.GroupLayout(pnlSeleccionProductos);
        pnlSeleccionProductos.setLayout(pnlSeleccionProductosLayout);
        pnlSeleccionProductosLayout.setHorizontalGroup(
            pnlSeleccionProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeleccionProductosLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlSeleccionProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIndiciacionProducto)
                    .addGroup(pnlSeleccionProductosLayout.createSequentialGroup()
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSeleccionProductosLayout.setVerticalGroup(
            pnlSeleccionProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeleccionProductosLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblIndiciacionProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSeleccionProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscarProducto)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        pnlSeleccionFinalizado.setBackground(new java.awt.Color(255, 255, 255));
        pnlSeleccionFinalizado.setLayout(new java.awt.GridBagLayout());

        btnRegistrarPrestamo.setText("Registrar préstamo");
        btnRegistrarPrestamo.setMinimumSize(new java.awt.Dimension(250, 50));
        btnRegistrarPrestamo.setPreferredSize(new java.awt.Dimension(250, 50));
        pnlSeleccionFinalizado.add(btnRegistrarPrestamo, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(lblTitulo)
                .addContainerGap(479, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(pnlSeleccionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlSeleccionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlSeleccionFinalizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSeleccionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSeleccionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSeleccionFinalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnRegistrarPrestamo;
    private javax.swing.JComboBox<String> cmbTipoProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblIndiciacionProducto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioSelecionado;
    private javax.swing.JPanel pnlSeleccionFinalizado;
    private javax.swing.JPanel pnlSeleccionProductos;
    private javax.swing.JPanel pnlSeleccionUsuario;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtUsuarioSeleccionado;
    // End of variables declaration//GEN-END:variables
}

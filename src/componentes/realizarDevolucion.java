/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import classes.Otros.Prestamo;
import classes.Conexion.ConnectionDb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import classes.RecursosFisicosFolder.*;
import classes.RecursosDigitalesFolder.*;
import java.util.Date;
/**
 *
 * @author Oliver
 */
public class realizarDevolucion extends javax.swing.JPanel {

    private Prestamo prestamo = new Prestamo();
    private List<Prestamo> productosPrestados = new ArrayList<>();
    private List<String> codigoProductos = new ArrayList<>();
    private ConnectionDb con = new ConnectionDb();
    private String codigoPrestamosGlobal = "";
    private String[] columnas = {"Código de identificación", "Titulo", "Tipo de producto", "Mora"};
    private float totalMora = 0;

    /**
     * Creates new form realizarDevolucion
     */
    public realizarDevolucion() {
        initComponents();
    }

    private void actualizarMora(List<Prestamo> productos) {
        for (Prestamo item : productos) {
            prestamo.procesarDevolucion(con, item.getId(), item.getCodigoEjemplar(), item.getFechaDevolucion());
        }
    }
    
    private DefaultTableModel cargarTabla(List<Prestamo> productos) {
        Vector<Vector<Object>> datos = new Vector<>();
        for (Prestamo item : productos) {
            datos.add(this.obtenerContenido(item.getCodigoEjemplar(),item.getMora()));
            this.codigoProductos.add(item.getCodigoEjemplar());
            this.totalMora = this.totalMora + item.getMora();
        }

        Vector<Object> columnasTabla = new Vector<>(Arrays.asList(this.columnas));
        DefaultTableModel contenido = new DefaultTableModel(datos, columnasTabla);
        return contenido;
    }

    private Vector<Object> obtenerContenido(String codigo, float mora) {
        Vector<Object> fila = new Vector<>();
        String tipoProducto = String.valueOf(codigo.charAt(0)) + String.valueOf(codigo.charAt(1)) + String.valueOf(codigo.charAt(2));
        switch (tipoProducto) {
            case "LIB":
                Libro libro = new Libro(codigo);
                Libro libroSeleccionado = libro.selectLibro(con);
                fila.add(libroSeleccionado.getCodigoIdentificacion());
                fila.add(libroSeleccionado.getTitulo());
                fila.add("Libro");
                break;
            case "REV":
                Revista revista = new Revista(codigo);
                Revista revistaSeleccionada = revista.selectRevista(con);
                fila.add(revistaSeleccionada.getCodigoIdentificacion());
                fila.add(revistaSeleccionada.getTitulo());
                fila.add("Revista");
                break;
            case "CDA":
                Cd cd = new Cd(codigo);
                Cd cdSeleccionado = cd.selectCd(con);
                fila.add(cdSeleccionado.getCodigoIdentificacion());
                fila.add(cdSeleccionado.getTitulo());
                fila.add("CD");
                break;
            case "PEL":
                Pelicula pelicula = new Pelicula(codigo);
                Pelicula peliculaSeleccionada = pelicula.selectPelicula(con);
                fila.add(peliculaSeleccionada.getCodigoIdentificacion());
                fila.add(peliculaSeleccionada.getTitulo());
                fila.add("Pelicula");
                break;
            case "EBK":
                Ebook ebook = new Ebook(codigo);
                Ebook ebookSeleccionado = ebook.selectEbook(con);
                fila.add(ebookSeleccionado.getCodigoIdentificacion());
                fila.add(ebookSeleccionado.getTitulo());
                fila.add("EBook");
                break;
            case "PER":
                Periodico periodico = new Periodico(codigo);
                Periodico periodicoSeleccionado = periodico.selectPeriodico(con);
                fila.add(periodicoSeleccionado.getCodigoIdentificacion());
                fila.add(periodicoSeleccionado.getTitulo());
                break;
            case "TES":
                Tesis tesis = new Tesis(codigo);
                Tesis tesisSeleccionado = tesis.selectTesis(con);
                fila.add(tesisSeleccionado.getCodigoIdentificacion());
                fila.add(tesisSeleccionado.getTitulo());
                fila.add("Tesis");
                break;
            default:
                System.out.println("NO VALID CODE");
                break;
        }
        fila.add(mora);
        return fila;
    }
    
    private void limpiarCampos() {
        txtTotal.setText("");
        txtFechaEntrega.setText("");
        txtEstado.setText("");
        txtMora.setText("");
        txtCodigoPrestamo.setText("");
        for (int i = 0; i < tblContenido.getRowCount(); i++) {
            tblContenido.remove(i);
        }
    }
    
    private void cargarDatosPanel(List<Prestamo> listaProductos) {
        txtTotal.setText(String.valueOf(listaProductos.size()));
        Date fechaDevolucion = listaProductos.get(0).getFechaDevolucion();
        txtFechaEntrega.setText(String.valueOf(fechaDevolucion));
        String estado = "Vigente";
        if (fechaDevolucion.before(new Date())) {
            estado = "Vencida";
        }
        txtEstado.setText(estado);
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
        pnlDetalleContenido = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContenido = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblFechaEntrega = new javax.swing.JLabel();
        txtFechaEntrega = new javax.swing.JTextField();
        lblMora = new javax.swing.JLabel();
        txtMora = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        btnRegistar = new javax.swing.JButton();
        lnlBuscarTitulo = new javax.swing.JLabel();
        txtCodigoPrestamo = new javax.swing.JTextField();
        btnBuscarPrestamo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1030, 640));
        setPreferredSize(new java.awt.Dimension(1030, 640));

        jLabel1.setText("Realizar devoluciones");

        pnlDetalleContenido.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalleContenido.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(500, 300));

        tblContenido.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblContenido);

        pnlDetalleContenido.add(jScrollPane1, new java.awt.GridBagConstraints());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTotal.setText("Total de productos prestados:");

        txtTotal.setText("0");
        txtTotal.setMinimumSize(new java.awt.Dimension(100, 22));
        txtTotal.setName(""); // NOI18N
        txtTotal.setPreferredSize(new java.awt.Dimension(100, 22));

        lblFechaEntrega.setText("Fecha de entrega:");

        txtFechaEntrega.setPreferredSize(new java.awt.Dimension(150, 22));

        lblMora.setText("Mora aplicada ($):");

        txtMora.setMinimumSize(new java.awt.Dimension(100, 22));
        txtMora.setPreferredSize(new java.awt.Dimension(100, 22));

        lblEstado.setText("Estado del préstamo");

        txtEstado.setMinimumSize(new java.awt.Dimension(150, 22));
        txtEstado.setName(""); // NOI18N
        txtEstado.setPreferredSize(new java.awt.Dimension(150, 22));

        btnRegistar.setText("Registrar devolución");
        btnRegistar.setPreferredSize(new java.awt.Dimension(200, 50));
        btnRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblFechaEntrega)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaEntrega)
                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMora)
                    .addComponent(txtMora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btnRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        lnlBuscarTitulo.setText("Ingresa el código del préstamo:");

        btnBuscarPrestamo.setText("Búscar préstamo");
        btnBuscarPrestamo.setMaximumSize(new java.awt.Dimension(250, 50));
        btnBuscarPrestamo.setMinimumSize(new java.awt.Dimension(250, 50));
        btnBuscarPrestamo.setPreferredSize(new java.awt.Dimension(250, 50));
        btnBuscarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlDetalleContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(lnlBuscarTitulo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnlBuscarTitulo)
                    .addComponent(txtCodigoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDetalleContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPrestamoActionPerformed
        if (txtCodigoPrestamo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes de colocar el código del préstamo.", "Valor inválido", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.codigoPrestamosGlobal = txtCodigoPrestamo.getText();
        prestamo.setCodigoPrestamo(this.codigoPrestamosGlobal);
        this.productosPrestados = prestamo.selectAllPrestamosByTransaction(con);
        if (this.productosPrestados.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningún préstamo con el código ingresado.", "Préstamo no encontrado.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Cargar datos iniciales
        this.cargarDatosPanel(this.productosPrestados);
        this.actualizarMora(this.productosPrestados);
        // Se vuelven a obtner los productos con la mora ya actualizada
        prestamo.setCodigoPrestamo(this.codigoPrestamosGlobal);
        this.productosPrestados = prestamo.selectAllPrestamosByTransaction(con);
        tblContenido.setModel(this.cargarTabla(this.productosPrestados));
        txtMora.setText(String.valueOf(totalMora));
    }//GEN-LAST:event_btnBuscarPrestamoActionPerformed

    private void btnRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistarActionPerformed
        this.prestamo.devolverRecursos(con, this.codigoProductos, this.codigoPrestamosGlobal);
        JOptionPane.showMessageDialog(null, "Se ha devuelto todos los productos del préstamo.", "Préstamo devuelto.", JOptionPane.INFORMATION_MESSAGE);
        this.limpiarCampos();
    }//GEN-LAST:event_btnRegistarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPrestamo;
    private javax.swing.JButton btnRegistar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblMora;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lnlBuscarTitulo;
    private javax.swing.JPanel pnlDetalleContenido;
    private javax.swing.JTable tblContenido;
    private javax.swing.JTextField txtCodigoPrestamo;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtMora;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

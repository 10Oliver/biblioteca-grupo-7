/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.util.HashMap;
import java.util.Map;

import classes.Conexion.ConnectionDb;
import validaciones.validaciones;

import classes.Otros.Prestamo;
import classes.Otros.ParametroMora;
import classes.Otros.Usuario;
import classes.RecursosDigitalesFolder.*;
import classes.RecursosFisicosFolder.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oliver
 */
public class realizarPrestamo extends javax.swing.JPanel {

    private Map<String, Integer> tipoProductoMap = new HashMap<>();
    private validaciones validaciones = new validaciones();
    private ConnectionDb con = new ConnectionDb();
    private Cd cd = new Cd();
    private Ebook ebook = new Ebook();
    private Pelicula pelicula = new Pelicula();
    private Libro libro = new Libro();
    private Periodico periodico = new Periodico();
    private Revista revista = new Revista();
    private Tesis tesis = new Tesis();
    private Vector<Vector<Object>> productosPrestar = new Vector<>();
    private String[] productosTitulos = {"Código de identificación", "Nombre del producto", "Tipo de producto"};

    private int userId;
    private int userRolId;

    /**
     * Creates new form prestamo
     */
    public realizarPrestamo() {
        initComponents();
        this.cargarHashMap();
        this.cargarTipoProducto();
        // Se colocan las columnas de la tabla
        this.cargarDatosTabla();
        con.getConnection();
        txtUsuarioSeleccionado.setEnabled(false);
    }

    private void cargarDatosTabla() {
        Vector<Object> columnas = new Vector<>(Arrays.asList(this.productosTitulos));
        DefaultTableModel datos = new DefaultTableModel(this.productosPrestar, columnas);
        tblProductos.setModel(datos);
    }

    private void cargarHashMap() {
        this.tipoProductoMap.put("Libro", 1);
        this.tipoProductoMap.put("Periodico", 2);
        this.tipoProductoMap.put("Revista", 3);
        this.tipoProductoMap.put("Tesis", 4);
        this.tipoProductoMap.put("CD", 5);
        this.tipoProductoMap.put("EBook", 6);
        this.tipoProductoMap.put("Pelicula", 7);
    }

    private void cargarTipoProducto() {
        cmbTipoProducto.removeAllItems();
        for (String nombreProducto : tipoProductoMap.keySet()) {
            cmbTipoProducto.addItem(nombreProducto);
        }

    }

    private void alertaNoEncontrado() {
        JOptionPane.showMessageDialog(null, "No se ha encontrado el producto.", "Producto inexistente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void alertaUsuarioNoEncontrado() {
        JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario con el nombre de usuario escrito.", "Usuario inexistente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarNuevoContenido(String codigo, String titulo, String tipo) {
        Vector fila = new Vector();
        fila.add(codigo);
        fila.add(titulo);
        fila.add(tipo);
        this.productosPrestar.add(fila);
        // Mostrar los datos en la tabla
        this.cargarDatosTabla();
        // Limpia el buscador
        txtNombreProducto.setText("");
    }

    private List<String> obtenerCodigo() {
        List<String> codigos = new ArrayList<String>();
        for (Vector<Object> item : this.productosPrestar) {
            codigos.add(item.get(0).toString());
        }
        return codigos;
    }

    private void limpiarContenido() {
        txtUsuarioSeleccionado.setText("");
        txtFechaEntrega.setText("");
        txtNombreProducto.setText("");
        txtNombreProducto.setText("");
        this.productosPrestar = new Vector<>();
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
        txtNombreUsuario = new javax.swing.JTextField();
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
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        btnRegistrarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPrestamoActionPerformed(evt);
            }
        });
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
        Usuario usuario = new Usuario();
        usuario.setCorreo(txtNombreUsuario.getText());
        Usuario usuarioSeleccionado = usuario.selectUsuarioByCorreo(con);
        if (usuarioSeleccionado == null) {
            this.alertaUsuarioNoEncontrado();
            return;
        }
        txtUsuarioSeleccionado.setText(usuarioSeleccionado.getNombreUsuario());
        this.userId = usuarioSeleccionado.getId();
        this.userRolId = usuarioSeleccionado.getIdRol();
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnRegistrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPrestamoActionPerformed
        try {
            Prestamo prestamos = new Prestamo();
            ParametroMora mora = new ParametroMora();
            mora.setIdRol(this.userRolId);
            mora.selectParametrosByRol(con);

            // Se revisa cuántos puede prestar
            int totalPrestado = prestamos.cuantosPuedePrestar(con, this.userId, "");
            int totalPosible = mora.getMaxPrestamo();
            if (totalPrestado >= totalPosible) {
                JOptionPane.showMessageDialog(null, "No tienes la cantidad necesaria de préstamos disponibles \n Cantidad disponible" + (totalPosible - totalPrestado), "Limite alcanzado", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // Intenta convertir el valor a una fecha válida
            Date fechaDevolver = this.validaciones.validarFecha(txtFechaEntrega.getText());
            if (fechaDevolver == null) {
                return;
            }
            prestamos.setIdUsuario(this.userId);
            prestamos.setFechaDevolucion(fechaDevolver);
            String codigoAutorizacion = prestamos.procesarVariosPrestamos(this.obtenerCodigo());
            // Se coloca en un JPanel para que se pueda copiar
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new JLabel("Se ha guarado el préstamo:"));
            panel.add(new JTextField(codigoAutorizacion));
            JOptionPane.showMessageDialog(null, panel, "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarContenido();
        } catch (Exception exception) {
            System.out.println(exception);
            JOptionPane.showMessageDialog(null, "Error al guardar el préstamo.", "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarPrestamoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        int tipoProducto = this.tipoProductoMap.get(cmbTipoProducto.getSelectedItem().toString());
        switch (tipoProducto) {
            case 1:
                this.libro.setCodigoIdentificacion(txtNombreProducto.getText());
                Libro libroSeleccionado = this.libro.selectLibro(con);
                if (libroSeleccionado == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(libroSeleccionado.getCodigoIdentificacion(), libroSeleccionado.getTitulo(), "Libro");
                break;
            case 2:
                this.periodico.setCodigoIdentificacion(txtNombreProducto.getText());
                Periodico periodicoSeleccionado = this.periodico.selectPeriodico(con);
                if (periodicoSeleccionado == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(periodicoSeleccionado.getCodigoIdentificacion(), periodicoSeleccionado.getTitulo(), "Periodico");
                break;

            case 3:
                this.revista.setCodigoIdentificacion(txtNombreProducto.getText());
                Revista revistaSeleccionado = this.revista.selectRevista(con);
                if (revistaSeleccionado == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(revistaSeleccionado.getCodigoIdentificacion(), revistaSeleccionado.getTitulo(), "Revista");
                break;
            case 4:
                this.tesis.setCodigoIdentificacion(txtNombreProducto.getText());
                Tesis tesisSeleccionada = this.tesis.selectTesis(con);
                if (tesisSeleccionada == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(tesisSeleccionada.getCodigoIdentificacion(), tesisSeleccionada.getTitulo(), "Tesis");

                break;
            case 5:
                this.cd.setCodigoIdentificacion(txtNombreProducto.getText());
                Cd cdSeleccionado = this.cd.selectCd(con);
                if (cdSeleccionado == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(cdSeleccionado.getCodigoIdentificacion(), cdSeleccionado.getTitulo(), "CD");
                break;
            case 6:
                this.ebook.setCodigoIdentificacion(txtNombreProducto.getText());
                Ebook ebookSeleccionado = this.ebook.selectEbook(con);
                if (ebookSeleccionado == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(ebookSeleccionado.getCodigoIdentificacion(), ebookSeleccionado.getTitulo(), "E-Book");
                break;
            case 7:
                this.pelicula.setCodigoIdentificacion(txtNombreProducto.getText());
                Pelicula peliculaSeleccionada = this.pelicula.selectPelicula(con);
                if (peliculaSeleccionada == null) {
                    this.alertaNoEncontrado();
                    return;
                }
                this.cargarNuevoContenido(peliculaSeleccionada.getCodigoIdentificacion(), peliculaSeleccionada.getTitulo(), "Pelicula");
                break;
        }
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
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtUsuarioSeleccionado;
    // End of variables declaration//GEN-END:variables
}

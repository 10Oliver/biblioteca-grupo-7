
package vistas;

import classes.Conexion.ConnectionDb;
import classes.Otros.Usuario;

import javax.swing.JOptionPane;

public class usuario extends javax.swing.JPanel {
    
     private int numeroDeFila = 1;

    public usuario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Agregar = new javax.swing.JButton();
        Restablecer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1030, 640));
        setPreferredSize(new java.awt.Dimension(1030, 640));

        jLabel1.setText("Usuarios");

        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });

        Restablecer.setText("Restablecer");
        Restablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestablecerActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "#", "Nombre de usuario", "Correo", "Fecha de nacimiento", "Telefono", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(Agregar)
                        .addGap(141, 141, 141)
                        .addComponent(Restablecer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(495, 495, 495)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Agregar)
                    .addComponent(Restablecer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
       
        // TODO add your handling code here:
        String nombreUsuario = JOptionPane.showInputDialog(this, "Ingrese nombre de usuario:");
        String correo = JOptionPane.showInputDialog(this, "Ingrese correo:");
        String fechaNacimiento = JOptionPane.showInputDialog(this, "Ingrese fecha de nacimiento (YYYY-MM-DD):");
        String telefono = JOptionPane.showInputDialog(this, "Ingrese teléfono:");
        String rol = JOptionPane.showInputDialog(this, "Ingrese rol:");
        String contrasenia = JOptionPane.showInputDialog(this, "Ingrese contraseña:");
        String confirmarContrasenia = JOptionPane.showInputDialog(this, "Confirme la contraseña:");

        // Verificar si las contraseñas coinciden
        if (contrasenia.equals(confirmarContrasenia)) {
            Usuario nuevoUsuario = new Usuario(0, nombreUsuario, contrasenia, correo, java.sql.Date.valueOf(fechaNacimiento), null, Integer.parseInt(telefono), Integer.parseInt(rol));
            ConnectionDb connection = new ConnectionDb();
            nuevoUsuario.Registrar(connection);
            agregarUsuario(nuevoUsuario);
        } else {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Inténtelo de nuevo.");
        }           
        System.out.println("Usuario registrado en la base de datos.");
    }//GEN-LAST:event_AgregarActionPerformed

    private void RestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestablecerActionPerformed
      // Pide al usuario que ingrese su nombre de usuario
    String nombreUsuario = JOptionPane.showInputDialog(this, "Ingrese su nombre de usuario:");

    // Verifica si el nombre de usuario existe en la tabla
    if (buscarUsuario(nombreUsuario)) {
        // Si existe, permite al usuario restablecer la contraseña
        restablecerContrasenia(nombreUsuario);
    } else {
        JOptionPane.showMessageDialog(this, "El nombre de usuario no existe. Inténtelo de nuevo.");
    }
}  
// Método para restablecer la contraseña de un usuario
private void restablecerContrasenia(String nombreUsuario) {
    // Obtener el modelo de la tabla
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
    ConnectionDb connection = new ConnectionDb();
    // Buscar la fila correspondiente al usuario
    int filaUsuario = -1;
    for (int i = 0; i < model.getRowCount(); i++) {
        if (nombreUsuario.equals(model.getValueAt(i, 1))) {
            filaUsuario = i;
            break;
        }
    }

        // Obtener el ID del usuario
    int usuarioId = (int) model.getValueAt(filaUsuario, 0);
    // Solicitar la nueva contraseña
    String nuevaContrasenia = JOptionPane.showInputDialog(this, "Ingrese la nueva contraseña:");

    // Verificar que se haya ingresado una nueva contraseña
    if (nuevaContrasenia != null) {
        // Confirmar la nueva contraseña
        String confirmarNuevaContrasenia = JOptionPane.showInputDialog(this, "Confirme la nueva contraseña:");

        // Verificar que la contraseña confirmada coincida
        if (nuevaContrasenia.equals(confirmarNuevaContrasenia)) {
                        // Actualizar la contraseña en la base de datos
            Usuario usuario = new Usuario(0, null, nuevaContrasenia, null, null, null, 0, 0);
            usuario.actualizarContrasenia(connection, nuevaContrasenia, usuarioId);
            JOptionPane.showMessageDialog(this, "La contraseña ha sido restablecida exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Inténtelo de nuevo.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "No se ha ingresado una nueva contraseña.");
    }
    }//GEN-LAST:event_RestablecerActionPerformed

private void agregarUsuario(Usuario nuevoUsuario) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();

        model.addRow(new Object[]{
                numeroDeFila,
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getCorreo(),
                nuevoUsuario.getFechaNacimiento().toString(),
                nuevoUsuario.getTelefono(),
                nuevoUsuario.getIdRol()
        });
        JOptionPane.showMessageDialog(this, "Usuario agregado:\n" + nuevoUsuario.toString());
        model.fireTableDataChanged();
        numeroDeFila++;
        jTable1.repaint();
        System.out.println("Nuevo usuario agregado a la tabla.");
    }


private boolean buscarUsuario(String nombreUsuario) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (nombreUsuario.equals(model.getValueAt(i, 1))) {
                return true;
            }
        }

        return false;
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Restablecer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
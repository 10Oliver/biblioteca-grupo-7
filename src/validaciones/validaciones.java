/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Oliver
 */
public class validaciones {

    public validaciones() {
    }

    public Date validarFecha(String fecha) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date newFecha = formatter.parse(fecha);
            return newFecha;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "La fecha ingresada no es válida.", "Error de dato", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

}

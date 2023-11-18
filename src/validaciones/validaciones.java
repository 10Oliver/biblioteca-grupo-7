/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Oliver
 */
public class validaciones {
    
    public validaciones() {}
    
    public boolean validarFecha(String fecha) {
        try {
           Date newFecha = new Date(fecha);
           return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "La fecha ingresada no es válida.", "Error de dato", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
}

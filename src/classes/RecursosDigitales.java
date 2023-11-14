package classes;

import java.sql.Date;

public class RecursosDigitales extends Recursos {
    private String genero;

    public RecursosDigitales(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante);
        this.genero = genero;
    }
    public RecursosDigitales(){

    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

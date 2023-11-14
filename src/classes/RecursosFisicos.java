package classes;

import java.sql.Date;

public class RecursosFisicos extends Recursos {
    private int numeroPaginas;

    public RecursosFisicos(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante);
        this.numeroPaginas = numeroPaginas;
    }
    public RecursosFisicos(){

    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}

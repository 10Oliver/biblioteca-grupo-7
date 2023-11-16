package classes;

import java.sql.Date;

public class Recursos {
private int id;
    private String codigoIdentificacion;
    private String titulo;
    private Date fechaPublicacion;
    private int stock;
    private String nombreEstante;

    public Recursos(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante) {
        this.id = id;
        this.codigoIdentificacion = codigoIdentificacion;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.stock = stock;
        this.nombreEstante = nombreEstante;
    }
    public Recursos(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public void setCodigoIdentificacion(String codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombreEstante() {
        return nombreEstante;
    }

    public void setNombreEstante(String nombreEstante) {
        this.nombreEstante = nombreEstante;
    }
}

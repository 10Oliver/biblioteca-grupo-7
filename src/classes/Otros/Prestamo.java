package classes.Otros;

import java.util.Date;

public class Prestamo {
    private int id;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Date fechaDevolucionReal;
    private float mora;
    private String codigoEjemplar;

    private String selectAllPrestamos = "SELECT * FROM Prestamos";

    private String selectPrestamoByID = "SELECT * FROM Prestamos WHERE id = ?";

    private String selectPrestamosByUsuarioID = "SELECT * FROM Prestamos WHERE idUsuario = ?";


    public Prestamo(int id, int idUsuario, Date fechaPrestamo, Date fechaDevolucion, Date fechaDevolucionReal, float mora, String codigoEjemplar) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.mora = mora;
        this.codigoEjemplar = codigoEjemplar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(Date fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public float getMora() {
        return mora;
    }

    public void setMora(float mora) {
        this.mora = mora;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }
}

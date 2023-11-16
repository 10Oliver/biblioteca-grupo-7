package classes.Otros;

public class ParametroMora {
    private int idParametros;
    private float mora;
    private int maxPrestamo;
    private int idRol;

    private String selectAllParametrosMora = "SELECT * FROM ParametrosMora";

    private String selectParametrosMoraByID = "SELECT * FROM ParametrosMora WHERE idParametros = ?";


    public ParametroMora(int idParametros, float mora, int maxPrestamo, int idRol) {
        this.idParametros = idParametros;
        this.mora = mora;
        this.maxPrestamo = maxPrestamo;
        this.idRol = idRol;
    }

    public int getIdParametros() {
        return idParametros;
    }

    public void setIdParametros(int idParametros) {
        this.idParametros = idParametros;
    }

    public float getMora() {
        return mora;
    }

    public void setMora(float mora) {
        this.mora = mora;
    }

    public int getMaxPrestamo() {
        return maxPrestamo;
    }

    public void setMaxPrestamo(int maxPrestamo) {
        this.maxPrestamo = maxPrestamo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}

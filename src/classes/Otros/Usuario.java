package classes.Otros;

import java.util.Date;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private Date fechaNacimiento;
    private String passTemporal;
    private int telefono;
    private int idRol;

    private String SELECT_ALL_USERS = "SELECT * FROM Usuarios";

    private String SELECT_USER_BY_ID = "SELECT * FROM Usuarios WHERE id = ?";

    private String SELECT_USER_BY_NAME = "SELECT * FROM Usuarios WHERE NombreUsuario = ?";


    public Usuario(int id, String nombreUsuario, String contrasena, String correo, Date fechaNacimiento, String passTemporal, int telefono, int idRol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.passTemporal = passTemporal;
        this.telefono = telefono;
        this.idRol = idRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassTemporal() {
        return passTemporal;
    }

    public void setPassTemporal(String passTemporal) {
        this.passTemporal = passTemporal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}

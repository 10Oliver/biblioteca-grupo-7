package classes.Otros;

public class Rol {
    private int id;
    private String nombreRol;

    private String selectAllRoles = "SELECT * FROM Roles";

    private String selectRolByID = "SELECT * FROM Roles WHERE id = ?";

    private String selectRolByNombreRol = "SELECT * FROM Roles WHERE NombreRol = ?";


    public Rol(int id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}

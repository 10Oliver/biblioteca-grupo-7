package classes.Otros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Conexion.ConnectionDb;
public class Rol {
    private int id;
    private String nombreRol;

    private String selectAllRoles = "SELECT * FROM Roles";

    private String selectRolByID = "SELECT * FROM Roles WHERE id = ?";

    private String selectRolByNombreRol = "SELECT * FROM Roles WHERE NombreRol = ?";
    private String insertRol = "INSERT INTO Roles (NombreRol) VALUES (?)";
    private String updateRol = "UPDATE Roles SET NombreRol WHERE id =?";


    public Rol(int id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }
    public Rol(){

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
    public List<Rol> selectAllRols(ConnectionDb connection) {
        List<Rol> roles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(selectAllRoles);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Map ResultSet to Cd
                setId(resultSet.getInt("id"));
                setNombreRol(resultSet.getString("NombreRol"));
                Rol rol = new Rol(getId(),getNombreRol());
                roles.add(rol);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
            e.printStackTrace();
        }
        return roles;
    }
    public Rol selectRolsById(ConnectionDb connection) {
        Rol rol = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(selectRolByID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setNombreRol(resultSet.getString("NombreRol"));
                rol = new Rol(getId(),getNombreRol());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
            e.printStackTrace();
        }
        return rol;
    }
}

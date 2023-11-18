package classes.Otros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Conexion.ConnectionDb;

public class Estante {
    private int id;
    private String nombreEstante;

    private String selectAllEstantes = "SELECT * FROM Estantes";

    private String selectEstanteByID = "SELECT * FROM Estantes WHERE id = ?";

    private String selectEstanteByNombreEstante = "SELECT * FROM Estantes WHERE NombreEstante = ?";


    public Estante(int id, String nombreEstante) {
        this.id = id;
        this.nombreEstante = nombreEstante;
    }
    public Estante(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEstante() {
        return nombreEstante;
    }

    public void setNombreEstante(String nombreEstante) {
        this.nombreEstante = nombreEstante;
    }
    public List<Estante> selectAllEstantes(ConnectionDb connection) {
        List<Estante> estantes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(selectAllEstantes);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Map ResultSet to Cd
                setId(resultSet.getInt("id"));
                setNombreEstante(resultSet.getString("NombreEstante"));
                Estante estante = new Estante(getId(),getNombreEstante());
                estantes.add(estante);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Estantes: " + e.getMessage());
            e.printStackTrace();
        }
        return estantes;
    }
}

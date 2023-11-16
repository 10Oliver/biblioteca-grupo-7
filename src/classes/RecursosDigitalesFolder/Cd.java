package classes.RecursosDigitalesFolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.RecursosDigitales;
import classes.Conexion.ConnectionDb;
// import classes.RecursosFisicosFolder.ResultSet;
// import classes.RecursosFisicosFolder.SQLException;


public class Cd extends RecursosDigitales {
    private String autor;
    private String numCanciones;

    private String UPDATE_STATEMENT = "UPDATE Cds SET Titulo = ?, Autor = ?, NumCanciones = ?, Genero = ?, FechaPublicacion = ?, Stock = ?, idEstante = ? WHERE id = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Cds (Titulo, Autor, NumCanciones, Genero, FechaPublicacion, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Cds WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT * FROM Cds WHERE id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT * FROM Cds";

    public Cd(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String autor, String numCanciones) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.autor = autor;
        this.numCanciones = numCanciones;
    }

    public Cd(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }
    public Cd() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumCanciones() {
        return numCanciones;
    }

    public void setNumCanciones(String numCanciones) {
        this.numCanciones = numCanciones;
    }

    public void insertCd(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setString(index++, getNumCanciones());
        statement.setString(index++, getGenero());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Cd was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Cd: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateCd(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setString(index++, getNumCanciones());
        statement.setString(index++, getGenero());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Cd was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Cd: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteCd(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Cd was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Cd: " + e.getMessage());
        e.printStackTrace();
    }
}

public Cd selectCd(ConnectionDb connection) {
    Cd cd = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Cd
            cd = mapResultSetToCdWithEstante(resultSet);
        } else {
            System.out.println("No Cd found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Cd: " + e.getMessage());
        e.printStackTrace();
    }
    return cd;
}

public List<Cd> selectAllCds(ConnectionDb connection) {
    List<Cd> cds = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Map ResultSet to Cd
            Cd cd = mapResultSetToCdWithEstante(resultSet);
            cds.add(cd);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
        e.printStackTrace();
    }
    return cds;
}

private Cd mapResultSetToCdWithEstante(ResultSet resultSet) throws SQLException {
    return new Cd(
    resultSet.getInt("id"),resultSet.getString("CodigoIdentificacion"),resultSet.getString("Titulo"),resultSet.getDate("FechaPublicacion"),resultSet.getInt("Stock"),resultSet.getString("NombreEstante"),resultSet.getString("Genero"),resultSet.getString("Autor"),resultSet.getString("NumCanciones")
    );
}








}

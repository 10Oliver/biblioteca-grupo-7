package classes.RecursosFisicosFolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.RecursosFisicos;
import classes.Conexion.ConnectionDb;

public class Periodico extends RecursosFisicos {
    private String nombrePeriodico;
    private String lugarPublicacion;

    private String UPDATE_STATEMENT = "UPDATE Periodicos SET Titulo = ?, NombrePeriodico = ?, FechaPublicacion = ?, NumeroPaginas = ?, LugarPublicacion = ?, Stock = ?, idEstante = ? WHERE CodigoIdentificacion = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Periodicos (Titulo, NombrePeriodico, FechaPublicacion, NumeroPaginas, LugarPublicacion, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Periodicos WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Periodicos.id, Periodicos.CodigoIdentificacion, Periodicos.Titulo, Periodicos.NombrePeriodico, Periodicos.FechaPublicacion, Periodicos.NumeroPaginas, Periodicos.LugarPublicacion, Periodicos.Stock, Estantes.NombreEstante FROM Periodicos LEFT JOIN Estantes ON Periodicos.idEstante = Estantes.id WHERE Periodicos.CodigoIdentificacion = ?;";
    private String SELECT_ALL_STATEMENT = "SELECT Periodicos.id, Periodicos.CodigoIdentificacion, Periodicos.Titulo, Periodicos.NombrePeriodico, Periodicos.FechaPublicacion, Periodicos.NumeroPaginas, Periodicos.LugarPublicacion, Periodicos.Stock, Estantes.NombreEstante FROM Periodicos LEFT JOIN Estantes ON Periodicos.idEstante = Estantes.id";

    public Periodico(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String nombrePeriodico, String lugarPublicacion) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.nombrePeriodico = nombrePeriodico;
        this.lugarPublicacion = lugarPublicacion;
    }
    public Periodico(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String nombrePeriodico, String lugarPublicacion) {
        super(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.nombrePeriodico = nombrePeriodico;
        this.lugarPublicacion = lugarPublicacion;
    }

    public Periodico(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Periodico() {
    }

    public String getNombrePeriodico() {
        return nombrePeriodico;
    }

    public void setNombrePeriodico(String nombrePeriodico) {
        this.nombrePeriodico = nombrePeriodico;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }



    public Periodico selectPeriodico(ConnectionDb connection) {
    Periodico periodico = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodico;
}

public List<Periodico> selectAllPeriodico(ConnectionDb connection) {
    List<Periodico> periodicos = new ArrayList<Periodico>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to periodico
            // periodico periodico = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setNombrePeriodico(resultSet.getString("NombrePeriodico"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Periodico periodico = new Periodico(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getNombrePeriodico(),getLugarPublicacion());
            periodicos.add(periodico);
        } else {
            System.out.println("No Periodico found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Periodico: " + e.getMessage());
        e.printStackTrace();
    }
    return periodicos;
}

public void insertPeriodico(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNombrePeriodico());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getNumeroPaginas());
        statement.setString(index++, getLugarPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Periodico was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Periodico: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updatePeriodico(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getNombrePeriodico());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getNumeroPaginas());
        statement.setString(index++, getLugarPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Periodico was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Periodico: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deletePeriodico(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Periodico was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Periodico: " + e.getMessage());
        e.printStackTrace();
    }
}


}

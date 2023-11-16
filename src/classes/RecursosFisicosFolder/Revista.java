package classes.RecursosFisicosFolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.RecursosFisicos;
import classes.Conexion.ConnectionDb;

public class Revista extends RecursosFisicos {
    private String autor;
    private int isbn;
    private String periodicidad;
    private String paisCiudad;
    private String notas;
    private String editorial;

    private String UPDATE_STATEMENT = "UPDATE Revistas SET Titulo = ?, Autor = ?, NumeroPaginas = ?, ISBN = ?, Editorial = ?, Periodicidad = ?, FechaPublicacion = ?, PaisCiudad = ?, Notas = ?, Stock = ?, idEstante = ? WHERE id = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Revistas (Titulo, Autor, NumeroPaginas, ISBN, Editorial, Periodicidad, FechaPublicacion, PaisCiudad, Notas, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Revistas WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT * FROM Revistas WHERE id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT * FROM Revistas";

    public Revista(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, int isbn, String periodicidad, String paisCiudad, String notas, String editorial) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.isbn = isbn;
        this.periodicidad = periodicidad;
        this.paisCiudad = paisCiudad;
        this.notas = notas;
        this.editorial = editorial;
    }

    public Revista(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Revista() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getPaisCiudad() {
        return paisCiudad;
    }

    public void setPaisCiudad(String paisCiudad) {
        this.paisCiudad = paisCiudad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

public void insertRevista(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEditorial());
        statement.setString(index++, getPeriodicidad());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getPaisCiudad());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Revista was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Revista: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateRevista(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEditorial());
        statement.setString(index++, getPeriodicidad());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getPaisCiudad());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Revista was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Revista: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteRevista(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Revista was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Revista: " + e.getMessage());
        e.printStackTrace();
    }
}

public Revista selectRevista(ConnectionDb connection) {
    Revista revista = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Revista
            revista = mapResultSetToRevistaWithEstante(resultSet);
        } else {
            System.out.println("No Revista found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Revista: " + e.getMessage());
        e.printStackTrace();
    }
    return revista;
}

public List<Revista> selectAllRevistas(ConnectionDb connection) {
    List<Revista> revistas = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Map ResultSet to Revista
            Revista revista = mapResultSetToRevistaWithEstante(resultSet);
            revistas.add(revista);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Revistas: " + e.getMessage());
        e.printStackTrace();
    }
    return revistas;
}

private Revista mapResultSetToRevistaWithEstante(ResultSet resultSet) throws SQLException {
    return new Revista(
            resultSet.getInt("id"),
            resultSet.getString("CodigoIdentificacion"),
            resultSet.getString("Titulo"),
            resultSet.getDate("FechaPublicacion"),
            resultSet.getInt("Stock"),
            resultSet.getString("NombreEstante"),
            resultSet.getInt("NumeroPaginas"),
            resultSet.getString("Autor"),
            resultSet.getInt("ISBN"),
            resultSet.getString("Periodicidad"),
            resultSet.getString("PaisCiudad"),
            resultSet.getString("Notas"),
            resultSet.getString("Editorial")
    );
}


}



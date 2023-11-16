package classes.RecursosDigitalesFolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.RecursosDigitales;
import classes.Conexion.ConnectionDb;
// import classes.RecursosFisicosFolder.SQLException;
// import classes.RecursosFisicosFolder.ResultSet;

public class Pelicula extends RecursosDigitales {
    private String director;
    private String duracion;
    private String productor;
    private String paisCiudad;
    private String UPDATE_STATEMENT = "UPDATE Peliculas SET Titulo = ?, Director = ?, Duracion = ?, Tipo = ?, Productor = ?, PaisCiudad = ?, FechaPublicacion = ?, Stock = ?, idEstante = ? WHERE id = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Peliculas (Titulo, Director, Duracion, Tipo, Productor, PaisCiudad, FechaPublicacion, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Peliculas WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT * FROM Peliculas WHERE id = ?";
    private String SELECT_ALL_STATEMENT = "SELECT * FROM Peliculas";

    public Pelicula(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero, String director, String duracion, String productor, String paisCiudad) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, genero);
        this.director = director;
        this.duracion = duracion;
        this.productor = productor;
        this.paisCiudad = paisCiudad;
    }

    public Pelicula(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Pelicula() {
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getPaisCiudad() {
        return paisCiudad;
    }

    public void setPaisCiudad(String paisCiudad) {
        this.paisCiudad = paisCiudad;
    }

public void insertPelicula(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getDirector());
        statement.setString(index++, getDuracion());
        statement.setString(index++, getGenero());
        statement.setString(index++, getProductor());
        statement.setString(index++, getPaisCiudad());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Pelicula was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updatePelicula(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getDirector());
        statement.setString(index++, getDuracion());
        statement.setString(index++, getGenero());
        statement.setString(index++, getProductor());
        statement.setString(index++, getPaisCiudad());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Pelicula was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deletePelicula(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Pelicula was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
}

public Pelicula selectPelicula(ConnectionDb connection) {
    Pelicula pelicula = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Pelicula
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("Director"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("Tipo"));
            setProductor(resultSet.getString("Productor"));
            setPaisCiudad(resultSet.getString("PaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
        } else {
            System.out.println("No Pelicula found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Pelicula: " + e.getMessage());
        e.printStackTrace();
    }
    return pelicula;
}

public List<Pelicula> selectAllPeliculas(ConnectionDb connection) {
    List<Pelicula> peliculas = new ArrayList<>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setDirector(resultSet.getString("Director"));
            setDuracion(resultSet.getString("Duracion"));
            setGenero(resultSet.getString("Tipo"));
            setProductor(resultSet.getString("Productor"));
            setPaisCiudad(resultSet.getString("PaisCiudad"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Pelicula pelicula = new Pelicula(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getGenero(),getDirector(),getDuracion(),getProductor(),getPaisCiudad());
            peliculas.add(pelicula);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while selecting all Peliculas: " + e.getMessage());
        e.printStackTrace();
    }
    return peliculas;
}


}

package classes.RecursosFisicosFolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.RecursosFisicos;
import classes.Conexion.ConnectionDb;

public class Libro extends RecursosFisicos {
    private String autor;
    private String editorial;
    private int isbn;
    private String edicion;
    private String lugarPublicacion;
    private String genero;
    private String idioma;
    private String notas;

    private String UPDATE_STATEMENT = "UPDATE Libros SET Titulo = ?, Autor = ?, Editorial = ?, NumeroPaginas = ?, ISBN = ?, Edicion = ?, LugarPublicacion = ?, FechaPublicacion = ?, Genero = ?, Idioma = ?, Notas = ?, Stock = ?, idEstante = ? WHERE CodigoIdentificacion = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Libros (Titulo, Autor, Editorial, NumeroPaginas, ISBN, Edicion, LugarPublicacion, FechaPublicacion, Genero, Idioma, Notas, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Libros WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Libros.id, Libros.CodigoIdentificacion, Libros.Titulo, Libros.Autor, Libros.Editorial, Libros.NumeroPaginas, Libros.ISBN, Libros.Edicion, Libros.LugarPublicacion, Libros.FechaPublicacion, Libros.Genero, Libros.Idioma, Libros.Notas, Libros.Stock, Estantes.NombreEstante FROM Libros LEFT JOIN Estantes ON Libros.idEstante = Estantes.id WHERE Libros.CodigoIdentificacion = ?;";
    private String SELECT_ALL_STATEMENT = "SELECT Libros.id, Libros.CodigoIdentificacion, Libros.Titulo, Libros.Autor, Libros.Editorial, Libros.NumeroPaginas, Libros.ISBN, Libros.Edicion, Libros.LugarPublicacion, Libros.FechaPublicacion, Libros.Genero, Libros.Idioma, Libros.Notas, Libros.Stock, Estantes.NombreEstante FROM Libros LEFT JOIN Estantes ON Libros.idEstante = Estantes.id";

    public Libro(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, String editorial, int isbn, String edicion, String lugarPublicacion, String genero, String idioma, String notas) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.edicion = edicion;
        this.lugarPublicacion = lugarPublicacion;
        this.genero = genero;
        this.idioma = idioma;
        this.notas = notas;
    }
    public Libro(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, String editorial, int isbn, String edicion, String lugarPublicacion, String genero, String idioma, String notas) {
        super(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.edicion = edicion;
        this.lugarPublicacion = lugarPublicacion;
        this.genero = genero;
        this.idioma = idioma;
        this.notas = notas;
    }

    public Libro(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Libro() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

public Libro selectLibro(ConnectionDb connection) {
    Libro libro = null;
    try {
        PreparedStatement statement = ConnectionDb.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("Genero"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return libro;
}

public List<Libro> selectAllLibros(ConnectionDb connection) {
    List<Libro> libros = new ArrayList<Libro>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setEditorial(resultSet.getString("Editorial"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setIsbn(resultSet.getInt("ISBN"));
            setEdicion(resultSet.getString("Edicion"));
            setLugarPublicacion(resultSet.getString("LugarPublicacion"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setGenero(resultSet.getString("Genero"));
            setIdioma(resultSet.getString("Idioma"));
            setNotas(resultSet.getString("Notas"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Libro libro = new Libro(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getIsbn(),getEdicion(),getLugarPublicacion(),getGenero(),getIdioma(),getNotas());
            libros.add(libro);
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return libros;
}

public void insertLibro(ConnectionDb connection) {
    int index = 1;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setString(index++, getEditorial());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEdicion());
        statement.setString(index++, getLugarPublicacion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getGenero());
        statement.setString(index++, getIdioma());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Libro was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Libro: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateLibro(ConnectionDb connection) {
    int index = 1;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setString(index++, getEditorial());
        statement.setInt(index++, getNumeroPaginas());
        statement.setInt(index++, getIsbn());
        statement.setString(index++, getEdicion());
        statement.setString(index++, getLugarPublicacion());
        statement.setDate(index++, getFechaPublicacion());
        statement.setString(index++, getGenero());
        statement.setString(index++, getIdioma());
        statement.setString(index++, getNotas());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Libro was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Libro: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteLibro(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Libro was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Libro: " + e.getMessage());
        e.printStackTrace();
    }
}








}

package classes.RecursosFisicosFolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.RecursosFisicos;
import classes.Conexion.ConnectionDb;

public class Tesis extends RecursosFisicos {
    private String autor;
    private String editorial;
    private String nivelAcademico;
    private String institucionAcademica;
    private String facultad;

    //queries
    private String UPDATE_STATEMENT = "UPDATE Tesis SET Titulo = ?, Autor = ?, FechaPublicacion = ?, NumeroPaginas = ?, Editorial = ?, NivelAcademico = ?, InstitucionAcademica = ?, Facultad = ?, Stock = ?, idEstante = ? WHERE CodigoIdentificacion = ?;";
    private String INSERT_STATEMENT = "INSERT INTO Tesis (Titulo, Autor, FechaPublicacion, NumeroPaginas, Editorial, NivelAcademico, InstitucionAcademica, Facultad, Stock, idEstante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String DELETE_STATEMENT = "DELETE FROM Tesis WHERE CodigoIdentificacion = ?;";
    private String SELECT_SINGLE_STATEMENT = "SELECT Tesis.id, Tesis.CodigoIdentificacion, Tesis.Titulo, Tesis.Autor, Tesis.FechaPublicacion, Tesis.NumeroPaginas, Tesis.Editorial, Tesis.NivelAcademico, Tesis.InstitucionAcademica, Tesis.Facultad, Tesis.Stock, Estantes.NombreEstante FROM Tesis LEFT JOIN Estantes ON Tesis.idEstante = Estantes.id WHERE Tesis.CodigoIdentificacion = ?;";
    private String SELECT_ALL_STATEMENT = "SELECT Tesis.id, Tesis.CodigoIdentificacion, Tesis.Titulo, Tesis.Autor, Tesis.FechaPublicacion, Tesis.NumeroPaginas, Tesis.Editorial, Tesis.NivelAcademico, Tesis.InstitucionAcademica, Tesis.Facultad, Tesis.Stock, Estantes.NombreEstante FROM Tesis LEFT JOIN Estantes ON Tesis.idEstante = Estantes.id";

    public Tesis(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, String editorial, String nivelAcademico, String institucionAcademica, String facultad) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.editorial = editorial;
        this.nivelAcademico = nivelAcademico;
        this.institucionAcademica = institucionAcademica;
        this.facultad = facultad;
    }
    public Tesis(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas, String autor, String editorial, String nivelAcademico, String institucionAcademica, String facultad) {
        super(titulo, fechaPublicacion, stock, nombreEstante, numeroPaginas);
        this.autor = autor;
        this.editorial = editorial;
        this.nivelAcademico = nivelAcademico;
        this.institucionAcademica = institucionAcademica;
        this.facultad = facultad;
    }

    public Tesis(String codigoIdentificacion) {
        setCodigoIdentificacion(codigoIdentificacion);
    }

    public Tesis() {
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

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getInstitucionAcademica() {
        return institucionAcademica;
    }

    public void setInstitucionAcademica(String institucionAcademica) {
        this.institucionAcademica = institucionAcademica;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

public Tesis selectTesis(ConnectionDb connection) {
    Tesis tesis = null;
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_SINGLE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setEditorial(resultSet.getString("Editorial"));
            setNivelAcademico(resultSet.getString("NivelAcademico"));
            setInstitucionAcademica(resultSet.getString("InstitucionAcademica"));
            setFacultad(resultSet.getString("Facultad"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            tesis = new Tesis(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getNivelAcademico(),getInstitucionAcademica(),getFacultad());
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return tesis;
}

public List<Tesis> selectAllTesis(ConnectionDb connection) {
    List<Tesis> Alltesis = new ArrayList<Tesis>();
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_STATEMENT);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Map ResultSet to Ebook
            // Ebook ebook = null;
            setId(resultSet.getInt("id"));
            setCodigoIdentificacion(resultSet.getString("CodigoIdentificacion"));
            setTitulo(resultSet.getString("Titulo"));
            setAutor(resultSet.getString("Autor"));
            setFechaPublicacion(resultSet.getDate("FechaPublicacion"));
            setNumeroPaginas(resultSet.getInt("NumeroPaginas"));
            setEditorial(resultSet.getString("Editorial"));
            setNivelAcademico(resultSet.getString("NivelAcademico"));
            setInstitucionAcademica(resultSet.getString("InstitucionAcademica"));
            setFacultad(resultSet.getString("Facultad"));
            setStock(resultSet.getInt("Stock"));
            setNombreEstante(resultSet.getString("NombreEstante"));
            Tesis tesis = new Tesis(getId(),getCodigoIdentificacion(),getTitulo(),getFechaPublicacion(),getStock(),getNombreEstante(),getNumeroPaginas(),getAutor(),getEditorial(),getNivelAcademico(),getInstitucionAcademica(),getFacultad());
            Alltesis.add(tesis);
        } else {
            System.out.println("No Ebook found with the provided ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error occurred while selecting the Ebook: " + e.getMessage());
        e.printStackTrace();
    }
    return Alltesis;
}

public void insertTesis(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getNumeroPaginas());
        statement.setString(index++, getEditorial());
        statement.setString(index++, getNivelAcademico());
        statement.setString(index++, getInstitucionAcademica());
        statement.setString(index++, getFacultad());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Tesis was inserted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while inserting Tesis: " + e.getMessage());
        e.printStackTrace();
    }
}

public void updateTesis(ConnectionDb connection) {
    try {
        int index = 1;
        PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_STATEMENT);
        statement.setString(index++, getTitulo());
        statement.setString(index++, getAutor());
        statement.setDate(index++, getFechaPublicacion());
        statement.setInt(index++, getNumeroPaginas());
        statement.setString(index++, getEditorial());
        statement.setString(index++, getNivelAcademico());
        statement.setString(index++, getInstitucionAcademica());
        statement.setString(index++, getFacultad());
        statement.setInt(index++, getStock());
        statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        statement.setString(index++, getCodigoIdentificacion());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Tesis was updated successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while updating Tesis: " + e.getMessage());
        e.printStackTrace();
    }
}

public void deleteTesis(ConnectionDb connection) {
    try {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_STATEMENT);
        statement.setString(1, getCodigoIdentificacion());
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Tesis was deleted successfully!");
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while deleting Tesis: " + e.getMessage());
        e.printStackTrace();
    }
}

}

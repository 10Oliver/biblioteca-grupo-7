package classes.Otros;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
// import java.sql.Date;
import java.util.List;
import java.util.UUID;

import classes.Conexion.ConnectionDb;


public class Prestamo {
    private int id;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Date fechaDevolucionReal;
    private float mora;
    private String codigoEjemplar;
    private String codigoPrestamo;

    private String SELECT_ALL_PRESTAMOS = "SELECT * FROM Prestamos";

    private String SELECT_ALL_PRESTAMOS_BY_ID = "SELECT * FROM Prestamos WHERE id = ?";

    private String SELECT_ALL_PRESTAMOS_BY_USER = "SELECT * FROM Prestamos WHERE idUsuario = ?";

    private String SELECT_ALL_PRESTAMOS_BY_TRANSACTION = "SELECT * FROM Prestamos WHERE CodigoPrestamo = ?";






// String select_ebooks_stock = "SELECT CodigoIdentificacion, Stock FROM Ebooks WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";
// String select_libros_stock = "SELECT CodigoIdentificacion, Stock FROM Libros WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";
// String select_periodicos_stock = "SELECT CodigoIdentificacion, Stock FROM Periodicos WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";
// String select_tesis_stock = "SELECT CodigoIdentificacion, Stock FROM Tesis WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";
// String select_peliculas_stock = "SELECT CodigoIdentificacion, Stock FROM Peliculas WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";
// String select_cds_stock = "SELECT CodigoIdentificacion, Stock FROM Cds WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";
// String select_revistas_stock = "SELECT CodigoIdentificacion, Stock FROM Revistas WHERE CodigoIdentificacion = WHERE CodigoIdentificacion = ?";


String update_ebooks_stock = "UPDATE Ebooks SET Stock = Stock + ? WHERE CodigoIdentificacion = ?";
String update_libros_stock = "UPDATE Libros SET Stock = Stock + ? WHERE CodigoIdentificacion = ?";
String update_periodicos_stock = "UPDATE Periodicos SET Stock = Stock + ? WHERE CodigoIdentificacion = ?";
String update_tesis_stock = "UPDATE Tesis SET Stock = Stock - ? WHERE CodigoIdentificacion = ?";
String update_peliculas_stock = "UPDATE Peliculas SET Stock = Stock + ? WHERE CodigoIdentificacion = ?";
String update_cds_stock = "UPDATE Cds SET Stock = Stock - ? WHERE CodigoIdentificacion = ?";
String update_revistas_stock = "UPDATE Revistas SET Stock = Stock + ? WHERE CodigoIdentificacion = ?";



    public Prestamo(int id, int idUsuario, Date fechaPrestamo, Date fechaDevolucion, Date fechaDevolucionReal, float mora, String codigoEjemplar) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.mora = mora;
        this.codigoEjemplar = codigoEjemplar;
    }
    public Prestamo(int idUsuario,String codigoEjemplar){

    }
    public Prestamo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    public java.sql.Date getFechaDevolucionSql() {
        return new java.sql.Date(fechaDevolucion.getTime());
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(Date fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public float getMora() {
        return mora;
    }

    public void setMora(float mora) {
        this.mora = mora;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }
    public void setCodigoPrestamo(String codigoPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
    }
    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public Date obtenerFechaDevolucionReal(ConnectionDb connection, int idPrestamo, String codigoEjemplar) throws SQLException {
        String selectPrestamoQuery = "SELECT FechaDevolucionReal FROM Prestamos WHERE id = ? AND CodigoEjemplar = ?";
        try (PreparedStatement prestamoStatement = connection.getConnection().prepareStatement(selectPrestamoQuery)) {
            prestamoStatement.setInt(1, idPrestamo);
            prestamoStatement.setString(2, codigoEjemplar);
            ResultSet prestamoResult = prestamoStatement.executeQuery();

            if (prestamoResult.next()) {
                return prestamoResult.getDate("FechaDevolucionReal");
            }
        }
        throw new SQLException("No se pudo encontrar el préstamo.");
    }

    public void procesarDevolucion(ConnectionDb connection, int idPrestamo, String codigoEjemplar, Date fecha) {
        try {
            // Date fechaDevolucionReal = obtenerFechaDevolucionReal(connection, idPrestamo, codigoEjemplar);

            Date ahora = new Date();
            // if (fechaDevolucionReal != null && fechaDevolucionReal.after(new Date())) {
            if (ahora != null && ahora.after(fecha)) {
                // ... (cálculo de mora)
                float moraTotal = calcularMora(fechaDevolucionReal);

                // Actualizar la mora en la tabla Prestamos
                actualizarMora(connection, idPrestamo, codigoEjemplar, moraTotal);

                System.out.println("Devolución con mora calculada: " + moraTotal);
            } else {
                System.out.println("Devolución realizada a tiempo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error
        }
    }

    public float calcularMora(Date fechaDevolucionReal) {
        // ... (lógica de cálculo de mora)
        Date fechaActual = new Date();

        // Calcular la diferencia en días entre la fecha de devolución real y la fecha actual
        long diasRetraso = ChronoUnit.DAYS.between(fechaDevolucionReal.toInstant(), fechaActual.toInstant());

        float moraDiariaPorAño = 0.5f;

        // Calcular la mora total
        float moraTotal = (diasRetraso / 365) * moraDiariaPorAño;

        return moraTotal;
    }

    private void actualizarMora(ConnectionDb connection, int idPrestamo, String codigoEjemplar, float moraTotal) throws SQLException {
        String updateMoraQuery = "UPDATE Prestamos SET Mora = ? WHERE id = ? AND CodigoEjemplar = ?";
        try (PreparedStatement updateMoraStatement = connection.getConnection().prepareStatement(updateMoraQuery)) {
            updateMoraStatement.setFloat(1, moraTotal);
            updateMoraStatement.setInt(2, idPrestamo);
            updateMoraStatement.setString(3, codigoEjemplar);
            updateMoraStatement.executeUpdate();
        }
    }

    public void crearPrestamo(ConnectionDb connection, String uId,String codigoEjemplar,int idUsuario) {
        try {
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(currentDate);
            String insertQuery = "INSERT INTO Prestamos (idUsuario, FechaPrestamo, CodigoEjemplar, CodigoPrestamo, FechaDevolucion) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, idUsuario);
                preparedStatement.setString(2, formattedDate);
                preparedStatement.setString(3, codigoEjemplar);
                preparedStatement.setString(4,uId);
                preparedStatement.setDate(5, getFechaDevolucionSql());

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("New Prestamo created successfully!");
                } else {
                    System.out.println("Failed to create new Prestamo. Check your input values.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void devolverEnEstantes(ConnectionDb connection, int cantidadDevuelta, String estante) {
        try {
            try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(estante)) {
                preparedStatement.setInt(1, cantidadDevuelta);
                preparedStatement.setString(2,getCodigoEjemplar());

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("New Prestamo created successfully!");
                } else {
                    System.out.println("Failed to create new Prestamo. Check your input values.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String procesarVariosPrestamos(List<String> codigosEjemplar)
    {
        ConnectionDb connection = new ConnectionDb();
        String processUuid = UUID.randomUUID().toString();
        for (String codigo : codigosEjemplar) {
            crearPrestamo(connection,processUuid,codigo,getIdUsuario());
        }
        return processUuid;
    }

    public void devolverRecursos(ConnectionDb connection,List<String> codigos, String codigoPrestamo) {

        int cantidadDevuelta = 0;
        for(String code : codigos){
            String codigo = String.valueOf(code.charAt(0)) + String.valueOf(code.charAt(1)) + String.valueOf(code.charAt(2));
                switch (codigo) {
                    case "LIB":
                    devolverEnEstantes(connection,cantidadDevuelta,update_libros_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    case "REV":
                    devolverEnEstantes(connection,cantidadDevuelta,update_revistas_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    case "CDA":
                    devolverEnEstantes(connection,cantidadDevuelta,update_cds_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    case "PEL":
                    devolverEnEstantes(connection,cantidadDevuelta,update_peliculas_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    case "EBK":
                    devolverEnEstantes(connection,cantidadDevuelta,update_ebooks_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    case "PER":
                    devolverEnEstantes(connection,cantidadDevuelta,update_periodicos_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    case "TES":
                    devolverEnEstantes(connection,cantidadDevuelta,update_tesis_stock);
                    EliminarPrestamo(connection,codigoPrestamo);
                    break;
                    default:
                    System.out.println("NO VALID CODE");
                    break;
                }
        }
    }

    public int cuantosPuedePrestar(ConnectionDb connection, int userId, String codigoPrestamo) throws SQLException{

        String selectQuery = "SELECT COUNT(*) FROM Prestamos WHERE idUsuario = ?";
        int cantidadParaPrestar = 0;
        try{
            PreparedStatement prestamoStatement = connection.getConnection().prepareStatement(selectQuery);
            prestamoStatement.setInt(1, userId);
            ResultSet prestamoResult = prestamoStatement.executeQuery();

            if (prestamoResult.next()) {
                cantidadParaPrestar = prestamoResult.getInt(1);
            }
        }catch(SQLException e){
            throw new SQLException("No se pudo encontrar el préstamo.",e);
        }
        return cantidadParaPrestar;
    }

    private void EliminarPrestamo(ConnectionDb connection, String codigoPrestamo) {
        try {
            String deleteQuery = "DELETE FROM Prestamos WHERE CodigoPrestamo = ?";
            try (PreparedStatement deleteStatement = connection.getConnection().prepareStatement(deleteQuery)) {
                deleteStatement.setString(1, codigoPrestamo);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prestamo> selectAllPrestamos(ConnectionDb connection)
    {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_PRESTAMOS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setIdUsuario(resultSet.getInt("idUsuario"));
                setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                setFechaDevolucionReal(resultSet.getDate("FechaDevolucionReal"));
                setMora(resultSet.getFloat("Mora"));
                setCodigoEjemplar(resultSet.getString("CodigoEjemplar"));
                setCodigoPrestamo(resultSet.getString("CodigoPrestamo"));
                Prestamo prestamo = new Prestamo(getId(),getIdUsuario(),getFechaPrestamo(),getFechaDevolucion(),getFechaDevolucionReal(),getMora(),getCodigoEjemplar());
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Prestamos: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamos;

    }
    public List<Prestamo> selectAllPrestamosByUser(ConnectionDb connection)
    {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_PRESTAMOS_BY_USER);
            statement.setInt(1,getIdUsuario());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setIdUsuario(resultSet.getInt("idUsuario"));
                setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                setFechaDevolucionReal(resultSet.getDate("FechaDevolucionReal"));
                setMora(resultSet.getFloat("Mora"));
                setCodigoEjemplar(resultSet.getString("CodigoEjemplar"));
                setCodigoPrestamo(resultSet.getString("CodigoPrestamo"));
                Prestamo prestamo = new Prestamo(getId(),getIdUsuario(),getFechaPrestamo(),getFechaDevolucion(),getFechaDevolucionReal(),getMora(),getCodigoEjemplar());
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Prestamos: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamos;

    }

    public List<Prestamo> selectAllPrestamosById(ConnectionDb connection)
    {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_PRESTAMOS_BY_USER);
            statement.setInt(1,getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setIdUsuario(resultSet.getInt("idUsuario"));
                setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                setFechaDevolucionReal(resultSet.getDate("FechaDevolucionReal"));
                setMora(resultSet.getFloat("Mora"));
                setCodigoEjemplar(resultSet.getString("CodigoEjemplar"));
                setCodigoPrestamo(resultSet.getString("CodigoPrestamo"));
                Prestamo prestamo = new Prestamo(getId(),getIdUsuario(),getFechaPrestamo(),getFechaDevolucion(),getFechaDevolucionReal(),getMora(),getCodigoEjemplar());
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Prestamos: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamos;

    }
    public List<Prestamo> selectAllPrestamosByTransaction(ConnectionDb connection)
    {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_PRESTAMOS_BY_TRANSACTION);
            statement.setString(1,getCodigoPrestamo());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setId(resultSet.getInt("id"));
                setIdUsuario(resultSet.getInt("idUsuario"));
                setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                setFechaDevolucionReal(resultSet.getDate("FechaDevolucionReal"));
                setMora(resultSet.getFloat("Mora"));
                setCodigoEjemplar(resultSet.getString("CodigoEjemplar"));
                setCodigoPrestamo(resultSet.getString("CodigoPrestamo"));
                Prestamo prestamo = new Prestamo(getId(),getIdUsuario(),getFechaPrestamo(),getFechaDevolucion(),getFechaDevolucionReal(),getMora(),getCodigoEjemplar());
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Prestamos: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamos;
    }

}

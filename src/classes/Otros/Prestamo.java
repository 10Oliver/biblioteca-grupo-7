package classes.Otros;

import java.util.Date;

public class Prestamo {
    private int id;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Date fechaDevolucionReal;
    private float mora;
    private String codigoEjemplar;

    private String selectAllPrestamos = "SELECT * FROM Prestamos";

    private String selectPrestamoByID = "SELECT * FROM Prestamos WHERE id = ?";

    private String selectPrestamosByUsuarioID = "SELECT * FROM Prestamos WHERE idUsuario = ?";


    public Prestamo(int id, int idUsuario, Date fechaPrestamo, Date fechaDevolucion, Date fechaDevolucionReal, float mora, String codigoEjemplar) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.mora = mora;
        this.codigoEjemplar = codigoEjemplar;
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
    
    public void procesarDevolucion(ConnectionDb connection, int idPrestamo, String codigoEjemplar) {
        try {
            Date fechaDevolucionReal = obtenerFechaDevolucionReal(connection, idPrestamo, codigoEjemplar);
    
            if (fechaDevolucionReal != null && fechaDevolucionReal.after(new Date())) {
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
    
    private float calcularMora(Date fechaDevolucionReal) {
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
    
    private void procesarVariosPrestamos(List<String> codigosEjemplar)
    {
        ConnectionDb connection = new ConnectionDb();
        for (String t : codigosEjemplar) {
            String codigo = String.valueOf(t.charAt(0)) + String.valueOf(t.charAt(1)) + String.valueOf(t.charAt(2));
            switch (codigo) {
                    case "LIB":
                    changeDbDataLibros(connection);
                    break;
                    case "REV":
                    changeDbDataRevistas(connection);
                    break;
                    case "CDA":
                    changeDbDataCds(connection);
                    break;
                    case "PEL":
                    changeDbDataPeliculas(connection);
                    break;
                    case "EBK":
                    changeDbDataEbooks(connection);
                    break;
                    case "PER":
                    changeDbDataPeriodicos(connection);
                    break;
                    case "TES":
                    changeDbDataTesis(connection);
                    break;
    
                default:
                System.out.println("NO VALID CODE");
                    break;
            }
        }
    }
    
    private void changeDbDataLibros(ConnectionDb connection)
    {
        // try {
        //     int index = 1;
        //     PreparedStatement statement = connection.getConnection().prepareStatement("UPDATE_STATEMENT");
        //     statement.setString(index++, getTitulo());
        //     statement.setString(index++, getAutor());
        //     statement.setInt(index++, getNumeroPaginas());
        //     statement.setInt(index++, getIsbn());
        //     statement.setString(index++, getEditorial());
        //     statement.setString(index++, getPeriodicidad());
        //     statement.setDate(index++, getFechaPublicacion());
        //     statement.setString(index++, getPaisCiudad());
        //     statement.setString(index++, getNotas());
        //     statement.setInt(index++, getStock());
        //     statement.setInt(index++, Integer.parseInt(getNombreEstante()));
        //     statement.setString(index++, getCodigoIdentificacion());
        //     int rowsUpdated = statement.executeUpdate();
        //     if (rowsUpdated > 0) {
        //         System.out.println("Revista was updated successfully!");
        //     }
        // } catch (SQLException e) {
        //     System.out.println("Error occurred while updating Revista: " + e.getMessage());
        //     e.printStackTrace();
        // }
    }
    private void changeDbDataRevistas(ConnectionDb connection)
    {
    
    }
    private void changeDbDataCds(ConnectionDb connection)
    {
    
    }
    private void changeDbDataTesis(ConnectionDb connection)
    {
    
    }
    private void changeDbDataEbooks(ConnectionDb connection)
    {
    
    }
    private void changeDbDataPeriodicos(ConnectionDb connection)
    {
    
    }
    private void changeDbDataPeliculas(ConnectionDb connection)
    {
    
    }
    
        
}

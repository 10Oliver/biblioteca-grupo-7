package classes.Otros;

import java.sql.Connection;

public class ParametroMora {
    private int idParametros;
    private float mora;
    private int maxPrestamo;
    private int idRol;

    private String selectAllParametrosMora = "SELECT * FROM ParametrosMora";

    private String selectParametrosMoraByID = "SELECT * FROM ParametrosMora WHERE idParametros = ?";
    private String insertParametroMora = "INSERT INTO parametro_mora (mora, max_prestamo, id_rol) VALUES (?, ?, ?)";
    private String UPDATE_PARAMETRO_MORA = "UPDATE parametro_mora SET mora = ?, max_prestamo = ? WHERE id_parametros = ?";

    public ParametroMora(int idParametros, float mora, int maxPrestamo, int idRol) {
        this.idParametros = idParametros;
        this.mora = mora;
        this.maxPrestamo = maxPrestamo;
        this.idRol = idRol;
    }

    public int getIdParametros() {
        return idParametros;
    }

    public void setIdParametros(int idParametros) {
        this.idParametros = idParametros;
    }

    public float getMora() {
        return mora;
    }

    public void setMora(float mora) {
        this.mora = mora;
    }

    public int getMaxPrestamo() {
        return maxPrestamo;
    }

    public void setMaxPrestamo(int maxPrestamo) {
        this.maxPrestamo = maxPrestamo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public void insertParametroMora(ParametroMora parametroMora) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setFloat(1, parametroMora.getMora());
            preparedStatement.setInt(2, parametroMora.getMaxPrestamo());
            preparedStatement.setInt(3, parametroMora.getIdRol());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void updateParametroMora(ParametroMora parametroMora) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setFloat(1, parametroMora.getMora());
            preparedStatement.setInt(2, parametroMora.getMaxPrestamo());
            preparedStatement.setInt(3, parametroMora.getIdParametros());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}

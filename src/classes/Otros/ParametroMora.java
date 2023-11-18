package classes.Otros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Conexion.ConnectionDb;

public class ParametroMora {
    private int idParametros;
    private float mora;
    private int maxPrestamo;
    private int idRol;

    private String SELECT_ALL_PARAMS = "SELECT * FROM ParametrosMora";

    private String SELECT_PARAMS_BY_ID = "SELECT * FROM ParametrosMora WHERE idParametros = ?";
    private String SELECT_PARAMS_BY_ROL = "SELECT * FROM ParametrosMora WHERE idRol = ?";
    private String INSERT_QUERY = "INSERT INTO ParametrosMora (mora, maxPrestamo, idRol) VALUES (?, ?, ?)";
    private String UPDATE_QUERY = "UPDATE ParametrosMora SET mora = ?, maxPrestamo = ? WHERE idRol = ?";

    public ParametroMora(int idParametros, float mora, int maxPrestamo, int idRol) {
        this.idParametros = idParametros;
        this.mora = mora;
        this.maxPrestamo = maxPrestamo;
        this.idRol = idRol;
    }
    public ParametroMora(){

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
        try{
            ConnectionDb connection = new ConnectionDb();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setFloat(1, parametroMora.getMora());
            preparedStatement.setInt(2, parametroMora.getMaxPrestamo());
            preparedStatement.setInt(3, parametroMora.getIdRol());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateParametroMora(ParametroMora parametroMora) {
        try{
            ConnectionDb connection = new ConnectionDb();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE_QUERY);
            preparedStatement.setFloat(1, parametroMora.getMora());
            preparedStatement.setInt(2, parametroMora.getMaxPrestamo());
            preparedStatement.setInt(3, parametroMora.getIdRol());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ParametroMora> selectAllParametrosMora(ConnectionDb connection){
        List<ParametroMora> params = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_PARAMS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setIdParametros(resultSet.getInt("idParametros"));
                setMora(resultSet.getFloat("Mora"));
                setMaxPrestamo(resultSet.getInt("MaxPrestamo"));
                setIdRol(resultSet.getInt("idRol"));
                ParametroMora param = new ParametroMora(getIdParametros(),getMora(),getMaxPrestamo(),getIdRol());
                params.add(param);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Params: " + e.getMessage());
            e.printStackTrace();
        }
        return params;
    }
    public ParametroMora selectParametrosById(ConnectionDb connection){
        ParametroMora param = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_PARAMS_BY_ID);
            statement.setInt(1, getIdParametros());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setIdParametros(resultSet.getInt("idParametros"));
                setMora(resultSet.getFloat("Mora"));
                setMaxPrestamo(resultSet.getInt("MaxPrestamo"));
                setIdRol(resultSet.getInt("idRol"));
                param = new ParametroMora(getIdParametros(),getMora(),getMaxPrestamo(),getIdRol());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Params: " + e.getMessage());
            e.printStackTrace();
        }
        return param;
    }
    public ParametroMora selectParametrosByRol(ConnectionDb connection){
        ParametroMora param = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_PARAMS_BY_ROL);
            statement.setInt(1, getIdRol());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                setIdParametros(resultSet.getInt("idParametros"));
                setMora(resultSet.getFloat("Mora"));
                setMaxPrestamo(resultSet.getInt("MaxPrestamo"));
                setIdRol(resultSet.getInt("idRol"));
                param = new ParametroMora(getIdParametros(),getMora(),getMaxPrestamo(),getIdRol());
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Params: " + e.getMessage());
            e.printStackTrace();
        }
        return param;
    }
}

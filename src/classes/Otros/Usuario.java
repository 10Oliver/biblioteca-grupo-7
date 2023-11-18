package classes.Otros;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Conexion.ConnectionDb;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private Date fechaNacimiento;
    private String passTemporal;
    private int telefono;
    private int idRol;

    private String SELECT_ALL_USERS = "SELECT * FROM Usuarios";

    private String SELECT_USER_BY_ID = "SELECT * FROM Usuarios WHERE id = ?";

    private String SELECT_USER_BY_NAME = "SELECT * FROM Usuarios WHERE NombreUsuario = ?";
    private String REGISTRAR_STATEMENT = "INSERT INTO Usuarios (NombreUsuario, Contrasena, Correo, FechaNacimiento, PassTemporal, Telefono, idRol) VALUES(?,?,?,?,?,?,?)";

    public Usuario(int id, String nombreUsuario, String contrasena, String correo, Date fechaNacimiento, String passTemporal, int telefono, int idRol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.passTemporal = passTemporal;
        this.telefono = telefono;
        this.idRol = idRol;
    }

    public Usuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassTemporal() {
        return passTemporal;
    }

    public void setPassTemporal(String passTemporal) {
        this.passTemporal = passTemporal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public List<Usuario> selectAllUsuarios(ConnectionDb connection) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Map ResultSet to Cd
                setId(resultSet.getInt("id"));
                setNombreUsuario(resultSet.getString("NombreUsuario"));
                setContrasena(resultSet.getString("contrasena"));
                setCorreo(resultSet.getString("correo"));
                setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                setPassTemporal(resultSet.getString("passTemporal"));
                setTelefono(resultSet.getInt("telefono"));
                setIdRol(resultSet.getInt("idRol"));

                Usuario usuario = new Usuario(getId(),getNombreUsuario(),getContrasena(),getCorreo(),getFechaNacimiento(),getPassTemporal(),getTelefono(),getIdRol());
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario selectUsuarioByCorreo(ConnectionDb connection) {
        Usuario usuario = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(SELECT_USER_BY_NAME);
            statement.setString(1, getCorreo());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Map ResultSet to Cd
                setId(resultSet.getInt("id"));
                setNombreUsuario(resultSet.getString("NombreUsuario"));
                setContrasena(resultSet.getString("contrasena"));
                setCorreo(resultSet.getString("correo"));
                setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                setPassTemporal(resultSet.getString("passTemporal"));
                setTelefono(resultSet.getInt("telefono"));
                setIdRol(resultSet.getInt("idRol"));
                usuario = new Usuario(getId(),getNombreUsuario(),getContrasena(),getCorreo(),getFechaNacimiento(),getPassTemporal(),getTelefono(),getIdRol());

            } else {
                System.out.println("No Cd found with the provided ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }
    public void Registrar(ConnectionDb connection){
     try {
            int index = 1;
            PreparedStatement statement = connection.getConnection().prepareStatement(REGISTRAR_STATEMENT);
                statement.setString(index++,getNombreUsuario());
                statement.setString(index++,getCorreo());
                statement.setDate(index++,getFechaNacimiento());
                statement.setString(index++,TemporalPwdGenerator());
                statement.setInt(index++,getTelefono());
                statement.setInt(index++,getIdRol());
                int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                setId(generatedKeys.getInt(1));
            }
            
                System.out.println("A new Cd was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selecting all Cds: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean Login(ConnectionDb connection,String correo, String pwd){
        int rowsInserted = 1;
        if(rowsInserted > 0){
            return true;
        }else{
            return false;
        }
    }
    public String TemporalPwdGenerator()
    {
        String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        String[] arrayCaracteres = caracteres.split("");
        int length = caracteres.length();
        String password = "";
        for (int i = 0; i <= 6; i++) {
            int index = (int)Math.random() * length;
            password = password + arrayCaracteres[index];
        }
        return password;
    }

    public void actualizarContrasenia(ConnectionDb connection, String nuevaContrasenia, int usuarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

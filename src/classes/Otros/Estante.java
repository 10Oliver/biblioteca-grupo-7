package classes.Otros;

public class Estante {
    private int id;
    private String nombreEstante;

    private String selectAllEstantes = "SELECT * FROM Estantes";

    private String selectEstanteByID = "SELECT * FROM Estantes WHERE id = ?";

    private String selectEstanteByNombreEstante = "SELECT * FROM Estantes WHERE NombreEstante = ?";


    public Estante(int id, String nombreEstante) {
        this.id = id;
        this.nombreEstante = nombreEstante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEstante() {
        return nombreEstante;
    }

    public void setNombreEstante(String nombreEstante) {
        this.nombreEstante = nombreEstante;
    }
}

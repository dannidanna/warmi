package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by danny on 5/30/18.
 */

public class Ddhh {

    private Long id;
    private String Nombre;
    private String Articulo;
    private String Descripcion;

    public Ddhh() {
    }

    public Ddhh(Long id, String nombre, String articulo, String descripcion) {
        this.id = id;
        Nombre = nombre;
        Articulo = articulo;
        Descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombre() {
        return Nombre;
    }

    public void setnombre(String nombre) {
        Nombre = nombre;
    }

    public String getarticulo() {
        return Articulo;
    }

    public void setarticulo(String articulo) {
        Articulo = articulo;
    }

    public String getdescripcion() {
        return Descripcion;
    }

    public void setdescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}

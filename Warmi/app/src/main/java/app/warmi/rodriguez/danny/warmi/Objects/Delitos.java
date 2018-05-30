package app.warmi.rodriguez.danny.warmi.Objects;

import android.util.Log;

/**
 * Created by danny on 5/30/18.
 */

public class Delitos {

    private Long id;
    private String Nombre;
    private String Articulo;
    private String Descripcion;

    public Delitos() {
    }

    public Delitos(Long id, String nombre, String articulo, String descripcion) {
        this.id = id;
        this.Nombre = nombre;
        this.Articulo = articulo;
        this.Descripcion = descripcion;
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

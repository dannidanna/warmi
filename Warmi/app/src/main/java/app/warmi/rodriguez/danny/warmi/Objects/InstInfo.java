package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by danny on 5/29/18.
 */

public class InstInfo {
    private Long id;
    private String Nombre;
    private String Direccion;
    private String Telefono;
    private String Servicio;
    private String PaginaReferencia;

    public InstInfo(){

    }

    public InstInfo(Long id, String nombre, String direccion, String telefono, String servicio, String paginaReferencia) {
        this.id = id;
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.Telefono = telefono;
        this.Servicio = servicio;
        this.PaginaReferencia = paginaReferencia;
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
        this.Nombre = nombre;
    }

    public String getdireccion() {
        return Direccion;
    }

    public void setdireccion(String direccion) {
        this.Direccion = direccion;
    }

    public String gettelefono() {
        return Telefono;
    }

    public void settelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getservicio() {
        return Servicio;
    }

    public void setservicio(String servicio) {
        this.Servicio = servicio;
    }

    public String getpaginaReferencia() {
        return PaginaReferencia;
    }

    public void setpaginaReferencia(String paginaReferencia) {
        this.PaginaReferencia = paginaReferencia;
    }

}

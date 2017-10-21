package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by Danny on 05-Sep-17.
 */

public class Persona {

    String nombre;
    String ci;
    String telefono;

    public Persona() {
    }

    public Persona(String nombre, String ci, String telefono) {
        this.nombre = nombre;
        this.ci = ci;
        this.telefono = telefono;
    }

    public Persona(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

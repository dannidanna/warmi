package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by Danny on 05-Sep-17.
 */

public class Persona {

    String nombre;
    int ci;
    int telefono;

    public Persona(){
    }

    public Persona(String nombre, int ci, int telefono) {
        this.nombre = nombre;
        this.ci = ci;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}


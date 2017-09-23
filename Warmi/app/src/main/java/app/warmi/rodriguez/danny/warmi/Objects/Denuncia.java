package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by danni on 05-Sep-17.
 */

public class Denuncia {

    Persona personaDenunciante;
    Persona personaVictima;
    String descripcion;
    String fecha;
    String relacion;
    Localizacion localizacion;

    public Denuncia() {
    }

    public Denuncia(Persona personaDenunciante, Persona personaVictima, String descripcion, String fecha, String relacion, Localizacion localizacion) {
        this.personaDenunciante = personaDenunciante;
        this.personaVictima = personaVictima;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.relacion = relacion;
        this.localizacion = localizacion;
    }

    public Denuncia(String descripcion, String fecha, String relacion) {
        //this.personaDenunciante = personaDenunciante;
        //this.personaVictima = personaVictima;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.relacion = relacion;
    }

   public Persona getPersonaDenunciante() {
        return personaDenunciante;
    }

    public void setPersonaDenunciante(Persona personaDenunciante) {
        this.personaDenunciante = personaDenunciante;
    }

    public Persona getPersonaVictima() {
        return personaVictima;
    }

    public void setPersonaVictima(Persona personaVictima) {
        this.personaVictima = personaVictima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }
}

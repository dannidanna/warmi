package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by danni on 05-Sep-17.
 */

public class Denuncia {

    String nombreVictima;
    String numeroVictima;
    String nombreAgresor;
    String descripcion;
    String fecha;
    String relacion;
    LocalizacionClass localizacion;

    public Denuncia() {
    }

    public Denuncia(String nombreVictima, String numeroVictima, String nombreAgresor, String descripcion,
                    String fecha, String relacion, LocalizacionClass localizacion) {
        this.nombreVictima = nombreVictima;
        this.numeroVictima = numeroVictima;
        this.nombreAgresor = nombreAgresor;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.relacion = relacion;
        this.localizacion = localizacion;
    }

    public Denuncia(String descripcion, String fecha, String relacion, LocalizacionClass localizacion) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.relacion = relacion;
        this.localizacion = localizacion;
    }

    public String getNombreVictima() {
        return nombreVictima;
    }

    public void setNombreVictima(String nombreVictima) {
        this.nombreVictima = nombreVictima;
    }

    public String getNumeroVictima() {
        return numeroVictima;
    }

    public void setNumeroVictima(String numeroVictima) {
        this.numeroVictima = numeroVictima;
    }

    public String getNombreAgresor() {
        return nombreAgresor;
    }

    public void setNombreAgresor(String nombreAgresor) {
        this.nombreAgresor = nombreAgresor;
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

    public LocalizacionClass getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(LocalizacionClass localizacion) {
        this.localizacion = localizacion;
    }
}

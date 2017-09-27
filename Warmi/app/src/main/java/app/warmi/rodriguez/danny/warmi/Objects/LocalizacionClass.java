package app.warmi.rodriguez.danny.warmi.Objects;


public class LocalizacionClass  {

    private String latitud;
    private String longitud;
    private String direccion;

    public LocalizacionClass(String latitud, String longitud, String direccion) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.direccion = direccion;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

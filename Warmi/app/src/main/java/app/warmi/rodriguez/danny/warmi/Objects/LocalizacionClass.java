package app.warmi.rodriguez.danny.warmi.Objects;


public class LocalizacionClass  {

    private double latitud;
    private double longitud;
    private String direccion;

    public LocalizacionClass(double latitud, double longitud, String direccion) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.direccion = direccion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

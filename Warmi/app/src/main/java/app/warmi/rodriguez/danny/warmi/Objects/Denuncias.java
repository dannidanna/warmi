package app.warmi.rodriguez.danny.warmi.Objects;

/**
 * Created by danny on 2/27/18.
 */

public class Denuncias {
    private Denuncia denuncia;
    private String nomVic;
    private String numVic;
    private String nomAgre;
    private String descrip;
    private String fecha;
    private String relacion;
    private String dir;
    private String url;
    private String nombreUsuario;
    private String numeroUsuario;

    /*public Denuncias(Denuncia denuncia, String nombreUsuario, String numeroUsuario) {
        this.denuncia = denuncia;
        this.nombreUsuario = nombreUsuario;
        this.numeroUsuario = numeroUsuario;
    }*/

    public String getNomVic() {
        return nomVic;
    }

    public void setNomVic(String nomVic) {
        this.nomVic = nomVic;
    }

    public String getNumVic() {
        return numVic;
    }

    public void setNumVic(String numVic) {
        this.numVic = numVic;
    }

    public String getNomAgre() {
        return nomAgre;
    }

    public void setNomAgre(String nomAgre) {
        this.nomAgre = nomAgre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Denuncias(String nomVic, String numVic, String nomAgre, String descrip,
                     String fecha, String relacion, String dir, String url, String nombreUsuario, String numeroUsuario) {
        this.nomVic = nomVic;
        this.numVic = numVic;
        this.nomAgre = nomAgre;
        this.descrip = descrip;
        this.fecha = fecha;
        this.relacion = relacion;
        this.dir = dir;
        this.url = url;

        this.nombreUsuario = nombreUsuario;
        this.numeroUsuario = numeroUsuario;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }
}

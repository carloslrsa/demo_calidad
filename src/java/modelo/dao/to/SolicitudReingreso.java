package modelo.dao.to;

public class SolicitudReingreso {
    private int idSolicitud;
    private Docente docente;
    private String motivo;

    public SolicitudReingreso(int idSolicitud, Docente docente, String motivo) {
        this.idSolicitud = idSolicitud;
        this.docente = docente;
        this.motivo = motivo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
}

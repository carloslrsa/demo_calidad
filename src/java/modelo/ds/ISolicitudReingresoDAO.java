package modelo.ds;

import java.util.List;
import modelo.dao.to.SolicitudReingreso;

public interface ISolicitudReingresoDAO {
    public List<SolicitudReingreso> obtenerPendientes();
    public boolean guardar(SolicitudReingreso solicitud);
    public boolean actualizar(int idSolicitud, String idAdministrador);
}

package modelo.ds;

import modelo.dao.to.DisponibilidadDocente;


public interface IDisponibilidadDocenteDAO {
    public DisponibilidadDocente obtener(String idDocente);
    public int guardar(DisponibilidadDocente disponibilidad);
}

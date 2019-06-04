package modelo.ds;

import modelo.dao.to.Administrador;

public interface IAdministradorDAO {
    public Administrador obtener(String DNI);
}

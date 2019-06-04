package modelo.ds;

import java.util.List;
import java.util.Map;
import modelo.dao.to.Curso;

public interface ICursoDAO {
    public List<Curso> obtenerTodos();
    public Map<String, List<Curso>> obtenerPorEscuelaPorId(String[] ids);
    public Map<String, List<Curso>> obtenerPorEscuela();
}

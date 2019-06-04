package modelo.dao.to;

import java.util.List;

public class Docente extends Persona{

    private Categoria categoria;
    private List<String> cursosCicloAnterior;

    public Docente(String id, String nombres, String apellidos, String DNI, String telefono, String email) {
        super(id, nombres, apellidos, DNI, telefono, email);
    }
    
    public Docente(String id, String nombres, String apellidos, String DNI, String telefono, String email, Categoria categoria, List<String> cursosCicloAnterior) {
        super(id, nombres, apellidos, DNI, telefono, email);
        this.categoria = categoria;
        this.cursosCicloAnterior = cursosCicloAnterior;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<String> getCursosCicloAnterior() {
        return cursosCicloAnterior;
    }

    public void setCursosCicloAnterior(List<String> cursosCicloAnterior) {
        this.cursosCicloAnterior = cursosCicloAnterior;
    }
  
    
    
}



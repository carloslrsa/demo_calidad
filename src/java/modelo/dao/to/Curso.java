package modelo.dao.to;

public class Curso {
    private String curso;
    private String nombre;
    private int creditos;
    private int horas;

    public Curso(String curso, String nombre, int creditos, int horas) {
        this.curso = curso;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas = horas;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    
}

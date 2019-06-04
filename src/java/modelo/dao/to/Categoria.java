package modelo.dao.to;

public class Categoria{
    private String tipo;
    private int horasLecMax;
    private int horasLecMin;
    private int horasLleMax;
    private int horasLleMin;
    private int cursosCan;

    public Categoria(String tipo, int horasLecMax, int horasLecMin, int horasLleMax, int horasLleMin, int cursosCan) {
        this.tipo = tipo;
        this.horasLecMax = horasLecMax;
        this.horasLecMin = horasLecMin;
        this.horasLleMax = horasLleMax;
        this.horasLleMin = horasLleMin;
        this.cursosCan = cursosCan;
    }

    public int getCursosCan() {
        return cursosCan;
    }

    public void setCursosCan(int cursosCan) {
        this.cursosCan = cursosCan;
    }

    
    public int getHorasLleMin() {
        return horasLleMin;
    }

    public void setHorasLleMin(int horasLleMin) {
        this.horasLleMin = horasLleMin;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHorasLecMax() {
        return horasLecMax;
    }

    public void setHorasLecMax(int horasLecMax) {
        this.horasLecMax = horasLecMax;
    }

    public int getHorasLecMin() {
        return horasLecMin;
    }

    public void setHorasLecMin(int horasLecMin) {
        this.horasLecMin = horasLecMin;
    }

    public int getHorasLleMax() {
        return horasLleMax;
    }

    public void setHorasLleMax(int horasLleMax) {
        this.horasLleMax = horasLleMax;
    }
    
    
}
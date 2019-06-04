package sesion;

public class Usuario {
    private String DNI;
    private int tipo;

    public Usuario(String DNI, int tipo) {
        this.DNI = DNI;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    
}

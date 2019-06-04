package validadores;

import modelo.dao.to.Categoria;
import modelo.dao.to.Docente;

public class ValidadorHorario {
    public static boolean validar(String[] lunes, String[] martes, String[] miercoles, String[] jueves, String[] viernes, String[] sabado, Docente docente){
        
        int cantidadHoras = obtenerHorasDia(lunes) + obtenerHorasDia(martes) +
                obtenerHorasDia(miercoles) + obtenerHorasDia(jueves) +
                obtenerHorasDia(viernes) + obtenerHorasDia(sabado);
        Categoria categoria = docente.getCategoria();
        if(cantidadHoras > categoria.getHorasLleMax() || cantidadHoras < categoria.getHorasLleMin())
            return false;
        
        return true;
    }
    
    private static int obtenerHorasDia(String[] dia){
        int retorno = 0;
        if(dia != null)
            for(int i=0; i<dia.length; i++)
                retorno ++;
        return retorno;
    }
}

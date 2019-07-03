package validadores;

import modelo.dao.to.Categoria;
import modelo.dao.to.Docente;

public class ValidadorHorario {
    public static boolean validar(String[] lunes, String[] martes, String[] miercoles, String[] jueves, String[] viernes, String[] sabado, Docente docente){
        
        int cantidadHorasLunes = obtenerHorasDia(lunes);
        if(cantidadHorasLunes == -1)    return false;
        int cantidadHorasMartes = obtenerHorasDia(martes);
        if(cantidadHorasMartes == -1)    return false;
        int cantidadHorasMiercoles = obtenerHorasDia(miercoles);
        if(cantidadHorasMiercoles == -1)    return false;
        int cantidadHorasJueves = obtenerHorasDia(jueves);
        if(cantidadHorasJueves == -1)    return false;
        int cantidadHorasViernes = obtenerHorasDia(viernes);
        if(cantidadHorasViernes == -1)    return false;
        int cantidadHorasSabado = obtenerHorasDia(sabado);
        if(cantidadHorasSabado == -1)    return false;
        
        int cantidadHoras = cantidadHorasLunes + cantidadHorasMartes + cantidadHorasMiercoles + cantidadHorasJueves + cantidadHorasViernes + cantidadHorasSabado;
        
        Categoria categoria = docente.getCategoria();
        if(cantidadHoras > categoria.getHorasLleMax() || cantidadHoras < categoria.getHorasLleMin())
            return false;
        
        return true;
    }
    
    private static int obtenerHorasDia(String[] dia){
        int retorno = 0;
        int horaAnterior = 0;
        int horaActual = 0;
        
        int horasContiguas = 0;
        if(dia != null)
            for(int i=0; i<dia.length; i++){
                if(horaAnterior == 0){
                    horaAnterior = Integer.parseInt(dia[i].substring(0,2));
                    horasContiguas ++;
                }
                else{
                    horaActual = Integer.parseInt(dia[i].substring(0,2));
                    if(horaActual == horaAnterior + 1){
                        horaAnterior = horaActual;
                        horasContiguas ++;
                    }else{
                        if(horasContiguas > 1){
                            horaAnterior = horaActual;
                            horasContiguas = 1;
                        }else{
                            return -1;
                        }
                    }                 
                }
                retorno ++;
            }
        if(horasContiguas == 1)
            retorno = -1;
        return retorno;
    }
}

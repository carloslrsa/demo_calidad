package validadores;

import java.util.HashMap;
import java.util.Map;
import modelo.dao.to.Docente;

public class ValidadorCursos {
    public static boolean validar(String[] cursosSeleccionados, Docente docente){
        Map<String, Integer> contador = new HashMap();
        for(String curso : cursosSeleccionados){
            if(!contador.containsKey(curso))
                contador.put(curso, 0);
            int n = contador.get(curso);
            n++;
            contador.replace(curso, n);
            //Se repiten los cursos
            if(n > 1)
                return false;
        }
        //Verificar cursos anteriormente dictados
        int coincidencias = 0;
        for(String cursoDictado : docente.getCursosCicloAnterior()){
            for(String curso : cursosSeleccionados){
                if(cursoDictado.equals(curso)){
                    coincidencias ++;
                    break;
                }
            }
        }
        return coincidencias > 1;
    }
}

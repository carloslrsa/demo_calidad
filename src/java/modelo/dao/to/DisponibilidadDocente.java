
package modelo.dao.to;

import java.util.ArrayList;
import java.util.List;
import utilitarios.DisponibilidadDocenteAuxiliar;

public class DisponibilidadDocente {
    
    private HorarioFila[] horario;
    private String[] cursos;
    private String idDocente;
    
    public DisponibilidadDocente(){
        horario = new HorarioFila[14];
        for(int i=0; i<14; i++){
            int hora = i+8;
            String horaString = hora>9?hora+":00":"0"+hora+":00";
            horario[i] = new HorarioFila(horaString,"","","","","","");
        }
    }
    
    public DisponibilidadDocente(long lunes, long martes, long miercoles, long jueves, long viernes, long sabado, List<String> cursos){
        horario = new HorarioFila[14];
        for(int i=0; i<14; i++){
            int hora = i+8;
            String horaString = hora>9?hora+":00":"0"+hora+":00";
            
            String lunesStr = (String.valueOf(lunes)).substring(i, i+1);
            if(lunesStr.equals("1")){
                lunesStr = "";
            }else{
                lunesStr = "1";
            }
            
            String martesStr = (String.valueOf(martes)).substring(i, i+1);
            if(martesStr.equals("1")){
                martesStr = "";
            }else{
                martesStr = "1";
            }
            
            String miercolesStr = (String.valueOf(miercoles)).substring(i, i+1);
            if(miercolesStr.equals("1")){
                miercolesStr = "";
            }else{
                miercolesStr = "1";
            }
            
            String juevesStr = (String.valueOf(jueves)).substring(i, i+1);
            if(juevesStr.equals("1")){
                juevesStr = "";
            }else{
                juevesStr = "1";
            }
            
            String viernesStr = (String.valueOf(viernes)).substring(i, i+1);
            if(viernesStr.equals("1")){
                viernesStr = "";
            }else{
                viernesStr = "1";
            }
            
            String sabadoStr = (String.valueOf(sabado)).substring(i, i+1);
            if(sabadoStr.equals("1")){
                sabadoStr = "";
            }else{
                sabadoStr = "1";
            }
            
            horario[i] = new HorarioFila(horaString,lunesStr,martesStr,miercolesStr,juevesStr,viernesStr,sabadoStr);
        }
        
        this.cursos = cursos.toArray(new String[cursos.size()]);
    }
    
    public DisponibilidadDocente(DisponibilidadDocenteAuxiliar disponibilidadDocente, String idDocente){
        this.horario = new HorarioFila[14];
        for(int i=0; i<14; i++){
            int hora = i+8;
            String horaString = hora>9?hora+":00":"0"+hora+":00";
            String lunes = "";
            if(disponibilidadDocente.getLunes() != null)
                for(String l : disponibilidadDocente.getLunes()){
                    if(horaString.equals(l))
                        lunes = "1";
                }
            String martes = "";
            if(disponibilidadDocente.getMartes() != null)
                for(String l : disponibilidadDocente.getMartes()){
                    if(horaString.equals(l))
                        martes = "1";
                }
            String miercoles = "";
            if(disponibilidadDocente.getMiercoles() != null)
                for(String l : disponibilidadDocente.getMiercoles()){
                    if(horaString.equals(l))
                        miercoles = "1";
                }
            String jueves = "";
            if(disponibilidadDocente.getJueves() != null)
                for(String l : disponibilidadDocente.getJueves()){
                    if(horaString.equals(l))
                        jueves = "1";
                }
            String viernes = "";
            if(disponibilidadDocente.getViernes() != null)
                for(String l : disponibilidadDocente.getViernes()){
                    if(horaString.equals(l))
                        viernes = "1";
                }
            String sabado = "";
            if(disponibilidadDocente.getSabado() != null)
                for(String l : disponibilidadDocente.getSabado()){
                    if(horaString.equals(l))
                        sabado = "1";
                }
            this.horario[i] = new HorarioFila(horaString,lunes,martes,miercoles,jueves,viernes,sabado);        
        }
        this.cursos = disponibilidadDocente.getCursosSeleccionados();
        this.idDocente = idDocente;
    }

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    
    public HorarioFila[] getHorario() {
        return horario;
    }

    public void setHorario(HorarioFila[] horario) {
        this.horario = horario;
    }

    public String[] getCursos() {
        return cursos;
    }

    public void setCursos(String[] cursos) {
        this.cursos = cursos;
    }
    
    
    
    public class HorarioFila{
        private String hora;
        private String lunes;
        private String martes;
        private String miercoles;
        private String jueves;
        private String viernes;
        private String sabado;

        public HorarioFila(String hora, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado) {
            this.hora = hora;
            this.lunes = lunes;
            this.martes = martes;
            this.miercoles = miercoles;
            this.jueves = jueves;
            this.viernes = viernes;
            this.sabado = sabado;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getLunes() {
            return lunes;
        }

        public void setLunes(String lunes) {
            this.lunes = lunes;
        }

        public String getMartes() {
            return martes;
        }

        public void setMartes(String martes) {
            this.martes = martes;
        }

        public String getMiercoles() {
            return miercoles;
        }

        public void setMiercoles(String miercoles) {
            this.miercoles = miercoles;
        }

        public String getJueves() {
            return jueves;
        }

        public void setJueves(String jueves) {
            this.jueves = jueves;
        }

        public String getViernes() {
            return viernes;
        }

        public void setViernes(String viernes) {
            this.viernes = viernes;
        }

        public String getSabado() {
            return sabado;
        }

        public void setSabado(String sabado) {
            this.sabado = sabado;
        }
        
        
    }
}

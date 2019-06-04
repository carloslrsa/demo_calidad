package modelo.dao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.servicios.ConexionMysql;
import modelo.dao.to.DisponibilidadDocente;
import modelo.dao.to.DisponibilidadDocente.HorarioFila;
import modelo.ds.IDisponibilidadDocenteDAO;

public class DisponibilidadDocenteDAO implements IDisponibilidadDocenteDAO{

    public ConexionMysql conexion;
    private final long min = 10000000000000L;
    
    public DisponibilidadDocenteDAO(){
        conexion = ConexionMysql.obtenerInstancia();
    }
    
    @Override
    public DisponibilidadDocente obtener(String idDocente) {
        DisponibilidadDocente retorno = null;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            
            Statement statement = miCon.createStatement();
            
            ResultSet resHorario = statement.executeQuery("CALL ObtenerDisponibilidadHorario('"+idDocente+"');");
            
            long lunes = 0,martes = 0,miercoles = 0,jueves = 0,viernes = 0,sabado = 0;
            List<String> cursos = new ArrayList();
            
            while(resHorario.next()){
                lunes = resHorario.getLong("lunes");
                martes = resHorario.getLong("martes");
                miercoles = resHorario.getLong("miercoles");
                jueves = resHorario.getLong("jueves");
                viernes = resHorario.getLong("viernes");
                sabado = resHorario.getLong("sabado");
            }
            
            ResultSet resCursos = statement.executeQuery("CALL ObtenerDisponibilidadCurso('"+idDocente+"');");
            while(resCursos.next()){
                cursos.add(resCursos.getString("idCurso"));
            }
            
            if(lunes <= min || martes <= min || miercoles <= min || jueves <= min || viernes <= min || sabado <= min || cursos.size() == 0)
                return null;
            
            retorno = new DisponibilidadDocente(lunes,martes,miercoles,jueves,viernes,sabado,cursos);
            
            conexion.desconectar();
        }catch(SQLException e){
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
        return retorno;
    }

    @Override
    public int guardar(DisponibilidadDocente disponibilidad) {
        int retorno = 0;
        try{
            
            //Se prepara el horario
            long lunes = 0;
            long martes = 0;
            long miercoles = 0;
            long jueves = 0;
            long viernes = 0;
            long sabado = 0;
            
            for(HorarioFila dia : disponibilidad.getHorario()){
                if("1".equals(dia.getLunes())){
                    lunes += 2;
                }else{
                    lunes += 1;
                }
                lunes *= 10;
                if("1".equals(dia.getMartes())){
                    martes += 2;
                }else{
                    martes +=1;
                }
                martes *= 10;
                if("1".equals(dia.getMiercoles())){
                    miercoles += 2;
                }else{
                    miercoles +=1;
                }
                miercoles *= 10;
                if("1".equals(dia.getJueves())){
                    jueves += 2;
                }else{
                    jueves += 1; 
                }
                jueves *= 10;
                if("1".equals(dia.getViernes())){
                    viernes += 2;
                }else{
                    viernes += 1;
                }
                viernes *= 10;
                if("1".equals(dia.getSabado())){
                    sabado += 2;
                }else{
                    sabado += 1;
                }
                sabado *= 10;
            }
            lunes /= 10;
            martes /= 10;
            miercoles /= 10;
            jueves /= 10;
            viernes /= 10;
            sabado /= 10;
            //-------------------------
            
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            Statement qry = miCon.createStatement();
            
            String qryHorario = "CALL LlenarDisponibilidadHorario('"+disponibilidad.getIdDocente()+"',"+lunes+","+martes+","+miercoles+","+jueves+","+viernes+","+sabado+");\n";
            
            retorno += qry.executeUpdate(qryHorario);
            
            String qryCursoBase = "CALL LlenarDisponibilidadCurso('"+disponibilidad.getIdDocente()+"','%s');\n";
            String qryCursos = "";
            
            for(String idCurso : disponibilidad.getCursos()){
                //qryCursos += String.format(qryCursoBase, idCurso);
                retorno += qry.executeUpdate(String.format(qryCursoBase, idCurso));
            }
            //qryHorario += qryCursos;
            //retorno = qry.executeUpdate(qryHorario);
            
        }catch(SQLException e){
            retorno = -1;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisponibilidadDocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = -1;
        }
        return retorno;
    }
    
}

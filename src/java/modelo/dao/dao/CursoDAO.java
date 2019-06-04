package modelo.dao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.dao.servicios.ConexionMysql;
import modelo.dao.to.Curso;
import modelo.ds.ICursoDAO;

public class CursoDAO implements ICursoDAO{
    ConexionMysql conexion;
    
    public CursoDAO(){
        conexion = ConexionMysql.obtenerInstancia();
    }
    
    @Override
    public List<Curso> obtenerTodos() {
        List<Curso> retorno = null;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            
            Statement statement = miCon.createStatement();
            
            ResultSet res = statement.executeQuery("CALL ObtenerCursos();");

            retorno = new ArrayList();
            
            while(res.next()){
                retorno.add(new Curso(res.getString("idCurso"), res.getString("nombre"), res.getInt("creditos"), res.getInt("horas")));
            }
            conexion.desconectar();
        }catch(SQLException e){
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
        return retorno;
    }

    @Override
    public Map<String, List<Curso>> obtenerPorEscuela() {
        Map<String, List<Curso>> retorno = null;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            
            Statement statement = miCon.createStatement();
            
            ResultSet res = statement.executeQuery("CALL OBtenerCursosPorEscuela();");

            retorno = new HashMap();
            
            while(res.next()){
                String escuela = res.getString("escuela");
                if(!retorno.containsKey(escuela)){
                    //Se agrega nueva escuela
                    retorno.put(escuela, new ArrayList());
                }
                retorno.get(escuela).add(new Curso(res.getString("idCurso"), res.getString("curso"), res.getInt("creditos"), res.getInt("horas")));
            }
            conexion.desconectar();
        }catch(SQLException e){
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
        return retorno;    
    }

    @Override
    public Map<String, List<Curso>> obtenerPorEscuelaPorId(String[] ids) {
        Map<String, List<Curso>> todos = obtenerPorEscuela();
        Map<String, List<Curso>> retorno = new HashMap();
        
        for (Map.Entry<String, List<Curso>> entry : todos.entrySet()) {
            for(Curso curso: entry.getValue()){
                for(String id: ids){
                    if(curso.getCurso().equals(id)){
                        if(!retorno.containsKey(entry.getKey()))
                            retorno.put(entry.getKey(), new ArrayList());
                        retorno.get(entry.getKey()).add(curso);
                        break;
                    }
                }
            }
        }
        return retorno;
    }

}

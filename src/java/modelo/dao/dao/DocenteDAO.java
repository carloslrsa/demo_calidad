package modelo.dao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.dao.servicios.ConexionMysql;
import modelo.dao.to.Docente;
import modelo.dao.to.Categoria;
import modelo.ds.IDocenteDAO;

public class DocenteDAO implements IDocenteDAO{
    private ConexionMysql conexion;

    public DocenteDAO(){
        conexion = ConexionMysql.obtenerInstancia();
    }
    
    @Override
    public Docente obtener(String arg) {
        Docente retorno = null;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            
            Statement statement = miCon.createStatement();
            
            ResultSet res = statement.executeQuery("CALL ObtenerDocente('"+arg+"');");
            
            while(res.next()){
                if(retorno == null){
                    retorno = new Docente(
                            res.getString("idDocente"),
                            res.getString("nombre"),
                            res.getString("apellidos"),
                            res.getString("DNI"),
                            res.getString("telefono"),
                            res.getString("email"),
                            new Categoria(res.getString("tipoCategoria"), res.getInt("horasLecMax"), res.getInt("horasLecMin"), res.getInt("horasLleMax"), res.getInt("horasLleMin"), res.getInt("cursosCan")),
                            new ArrayList());
                }else{
                    retorno.getCursosCicloAnterior().add(res.getString("cursoCicloAnterior"));
                }
            }
            conexion.desconectar();
        }catch(SQLException e){
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
        return retorno;
    }
}

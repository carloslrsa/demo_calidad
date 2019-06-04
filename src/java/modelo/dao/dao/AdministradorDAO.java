package modelo.dao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.dao.servicios.ConexionMysql;
import modelo.dao.to.Administrador;
import modelo.ds.IAdministradorDAO;

public class AdministradorDAO implements IAdministradorDAO{

    public ConexionMysql conexion;
    
    public AdministradorDAO(){
        conexion = ConexionMysql.obtenerInstancia();
    }
    
    @Override
    public Administrador obtener(String DNI) {
        Administrador retorno = null;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            
            Statement statement = miCon.createStatement();
            
            ResultSet res = statement.executeQuery("SELECT * FROM persona WHERE DNI = '"+DNI+"';");
            
            while(res.next()){
                retorno = new Administrador(
                        res.getString("idPersona"),
                        res.getString("nombre"),
                        res.getString("apellidos"),
                        res.getString("DNI"),
                        res.getString("telefono"),
                        res.getString("email"));
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

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
import modelo.dao.to.Docente;
import modelo.dao.to.SolicitudReingreso;
import modelo.ds.ISolicitudReingresoDAO;

public class SolicitudReingresoDAO implements ISolicitudReingresoDAO{

    ConexionMysql conexion;
    
    public SolicitudReingresoDAO(){
        conexion = ConexionMysql.obtenerInstancia();
    }
    
    @Override
    public List<SolicitudReingreso> obtenerPendientes() {
        List<SolicitudReingreso> retorno = null;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            Statement qry = miCon.createStatement();
            
            ResultSet res = qry.executeQuery("CALL ObtenerSolicitudesPendientes()");
            
            retorno = new ArrayList();
            
            while(res.next()){
                retorno.add(new SolicitudReingreso(res.getInt("idSolicitud"),new Docente(res.getString("idDocente"),res.getString("nombre"),res.getString("apellidos"),res.getString("DNI"),null,null,null,null),res.getString("motivo")));
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
    public boolean guardar(SolicitudReingreso solicitud) {
        boolean retorno = false;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            Statement qry = miCon.createStatement();
            
            ResultSet res = qry.executeQuery("SELECT SolicitarReingreso('"+solicitud.getDocente().getId()+"','"+solicitud.getMotivo()+"')");
            
            while(res.next()){
                retorno = res.getString(1).equals("1");
            }
            conexion.desconectar();
        }catch(SQLException e){
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }
        return retorno;
    }

    @Override
    public boolean actualizar(int idSolicitud, String idAdministrador) {
        boolean retorno = false;
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            Statement qry = miCon.createStatement();
            
            ResultSet res = qry.executeQuery("SELECT AtenderSolicitudPendiente("+idSolicitud+",'"+idAdministrador+"')");
            
            while(res.next()){
                retorno = res.getString(1).equals("1");
            }
            conexion.desconectar();
        }catch(SQLException e){
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }
        return retorno;
    }
    
}

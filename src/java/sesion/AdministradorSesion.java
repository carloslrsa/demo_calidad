package sesion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.dao.servicios.ConexionMysql;


public class AdministradorSesion {
    
    //Singleton
    /*private static AdministradorSesion instancia = null;
    private AdministradorSesion(){
        conexion = ConexionMysql.obtenerInstancia();
    }
    public static AdministradorSesion obtenerInstancia(){
        if(instancia == null)
            instancia = new AdministradorSesion();
        return instancia;
    }
    //-----------
    
    private ConexionMysql conexion;*/
    
    public static int Iniciar(String DNI, String password, HttpServletRequest request){
        int retorno = 0;
        ConexionMysql conexion = ConexionMysql.obtenerInstancia();
        try{
            conexion.conectar();
            Connection miCon = conexion.getJdbcConnection();
            
            Statement statement = miCon.createStatement();
            
            ResultSet res = statement.executeQuery("SELECT VerificarInicioSesion('"+DNI+"','"+password+"');");
            int tipo = 0;
            while(res.next()){
                tipo = res.getInt(1);
            }
            if(tipo > 0){
                HttpSession session = request.getSession();
                session.setAttribute("DNI", DNI);
                session.setAttribute("tipo", tipo);
                retorno = tipo;
            }
                
            conexion.desconectar();
        }catch(SQLException e){
            return 0;
        } catch (ClassNotFoundException ex) {
            return 0;
        }
        return retorno;
    }
        
    public static boolean enSesion(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        return session.getAttribute("DNI")!=null;
    }
    
    public static Usuario ObtenerUsuarioActivo(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        return new Usuario((String)session.getAttribute("DNI"),(int)session.getAttribute("tipo"));
    }
    
    public static void Cerrar(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        session.removeAttribute("DNI");
        session.removeAttribute("tipo");
        //session.invalidate();
    }
}

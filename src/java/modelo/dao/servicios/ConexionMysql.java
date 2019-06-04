package modelo.dao.servicios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {
    /*
    *   Región Singleton
    */
    //private static ConexionMysql instancia = null;
    private ConexionMysql(){}
    public static ConexionMysql obtenerInstancia(){
        //if(instancia == null)
        ConexionMysql instancia = new ConexionMysql();
        return instancia;
    }
    /*
    *   Fin Región
    */
    
    private Connection jdbcConnection;
    private String user = "root";
    private String pass = "root";
    private boolean conectado = false;

    public void conectar() throws SQLException, ClassNotFoundException {
        if(!conectado){
            Class.forName("com.mysql.jdbc.Driver");
            jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calidad?useSSL=false",user,pass);
            conectado = true;
        }
    }
     
    public void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
            conectado = false;
        }
    }

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }  
}

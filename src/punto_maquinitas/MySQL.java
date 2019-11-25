
package punto_maquinitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class MySQL {
 
    Connection conexion = null;
    
 
    public Connection MySQLConnect(String clase) {
 
        try {
            //Driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión MySQL
            //panamahitek_text es el nombre que le dimos a la base de datos
            String servidor = "jdbc:mysql://192.168.0.104/maquinas?serverTimezone=UTC";//  String servidor = "jdbc:mysql://localhost/local?serverTimezone=UTC","root","";
            //El root es el nombre de usuario por default. No hay contraseña
            String usuario = "ubuntu";
            String pass = "2408*12";
            //Se inicia la conexión
            conexion = DriverManager.getConnection(servidor, usuario, pass);
 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            JOptionPane.showMessageDialog(null, "Conexión Exitosa   "+clase);
            return conexion;
        }
    }
      public Connection MySQLmostar() {
 
        try {
            //Driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión MySQL
            //panamahitek_text es el nombre que le dimos a la base de datos
            String servidor = "jdbc:mysql://192.168.0.104/maquinas?serverTimezone=UTC";//  String servidor = "jdbc:mysql://localhost/local?serverTimezone=UTC","root","";
            //El root es el nombre de usuario por default. No hay contraseña
            String usuario = "ubuntu";
            String pass = "2408*12";
            //Se inicia la conexión
            conexion = DriverManager.getConnection(servidor, usuario, pass);
 
        } catch (ClassNotFoundException ex) {
            conexion = null;
        } catch (SQLException ex) {
            conexion = null;
        } catch (Exception ex) {
            conexion = null;
        } finally {
            return conexion;
        }
    }
}



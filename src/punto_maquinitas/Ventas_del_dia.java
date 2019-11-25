
package punto_maquinitas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ventas_del_dia extends JFrame{
    MySQL db_ventas =new MySQL();
    Connection conexion = null;
        Statement s; 
    ResultSet rs,fechas_dia;
     DefaultTableModel modelo= new DefaultTableModel();
     JTable tabla_ventas = new JTable(modelo);
    public Ventas_del_dia(){
        conexion=db_ventas.MySQLmostar();
       modelo.addColumn("FECHA");
        modelo.addColumn("TIPO");
        modelo.addColumn("PRECIO");
        setTitle("ventas del dia");
        setSize(400, 600);
        
        add(new JScrollPane(tabla_ventas));
         try {          
                    LocalDateTime locaDate = LocalDateTime.now();
                    int ano=locaDate.getYear();
                    int mes =locaDate.getMonthValue();
                    int dia =locaDate.getDayOfMonth();
                    s = conexion.createStatement();
                    System.out.println(Integer.toString(ano)+"-"+Integer.toString(mes)+"-"+Integer.toString(dia));
                   // rs = s.executeQuery ("select * from ventas where fecaha="+Integer.toString(ano)+"-"+Integer.toString(mes)+"-"+Integer.toString(dia));
                    
                    rs = s.executeQuery ("select * from ventas");
                    while(rs.next()){
                     modelo.addRow(new Object[]{rs.getString("fecaha"), rs.getString("tipo_producto"), rs.getString("monto")});
                       
                    
                    
                     
                    }
                    } catch (SQLException ex) {
                    Logger.getLogger(Cuadro.class.getName()).log(Level.SEVERE, null, ex);
                }  
    }
}

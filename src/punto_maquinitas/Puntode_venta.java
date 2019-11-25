
package punto_maquinitas;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class Puntode_venta {
    public static void main(String[] Jesus_Arenas){
    Cuadro C1 = new Cuadro();
    C1.setVisible(true);
    C1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Cuadro extends JFrame implements ActionListener{
MySQL db_ventas =new MySQL();

Connection conexion = null;
int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
Lamina_cobro_ventas Cobro_v =new Lamina_cobro_ventas();
Lamina_cobro_xbox  Cobro_x =  new Lamina_cobro_xbox();
JMenuBar herramientas = new JMenuBar();
JMenu ventasS =new JMenu("ventas del dia");
JMenuItem dia_vebtas =new JMenuItem("mostrar");
public Cuadro(){ 
 
    setTitle("Maquinitas San Bartolo");
    setBounds(0, 0,ancho,alto);
    setLayout(new GridLayout(1, 2, 5, 0));
    add(Cobro_v);
    add(Cobro_x);
    ventasS.add(dia_vebtas);
    herramientas.add(ventasS);
    dia_vebtas.addActionListener(this);
    setJMenuBar(herramientas);
    
}

    @Override
    public void actionPerformed(ActionEvent e){
        Ventas_del_dia V1=new Ventas_del_dia();
        V1.setVisible(true);
        //V1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

//sub clase cobr_ventas

class Lamina_cobro_ventas extends JPanel implements KeyListener{
    int total=0;
    int remover=0;
      int idelete=0;
class Cobro implements ActionListener{
Statement s1; 
    ResultSet rs1;
    
        @Override
        public void actionPerformed(ActionEvent e) {
           Object tipo = e.getSource();
        if(tipo==bt_PAGAR){   
            Cantida.getText();
          
           try {
      
        for (int i = 0; i <modelo.getRowCount(); i++) {
          s1 = conexion.createStatement();
            s1.executeUpdate("INSERT INTO ventas values(NULL,NOW(),'"+modelo.getValueAt(i,3 ).toString()+"',"+modelo.getValueAt(i, 2).toString()+")"); 
            
            s1.executeUpdate("update productos set cantidad=cantidad-1 where codigo="+modelo.getValueAt(i,1));
            System.out.println(modelo.getValueAt(i, 1));
        }
        total=0;
        Cantida.setText(null);
        idelete=0;
        remover=modelo.getRowCount();
               
        while ( idelete <remover) {
                   modelo.removeRow(0);
                   idelete++;
               }
   JOptionPane.showMessageDialog(null, "Venta  Realizada con exito", "Ventas ",1);

         

    } catch (SQLException ex) {
        Logger.getLogger(Cuadro.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
   if(tipo==Cancelar){
       System.out.println("cancelar");
   total=0;
        Cantida.setText(null);
        idelete=0;
        remover=modelo.getRowCount();
               
        while ( idelete <remover) {
                   modelo.removeRow(0);
                   idelete++;
               }
   
    }     
        }
}
    Statement s; 
    ResultSet rs;
     
    DefaultTableModel modelo= new DefaultTableModel();
    JButton bt_PAGAR =new JButton("PAGAR");
    JButton Cancelar =new JButton("Cancelar");
    JLabel ventas = new JLabel("Ventas"); 
    JLabel Total=new JLabel("Total$");
    JPanel Pagar = new JPanel();
    JPanel Buscar =new JPanel();
    JTextField Cantida = new JTextField(10);
    JTextField buscar=new JTextField();
    TextPrompt search =new TextPrompt("Codigo del Producto", buscar);
    JTable tabla_ventas = new JTable(modelo);
    Cobro cobro =new Cobro();
public Lamina_cobro_ventas(){
    conexion =db_ventas.MySQLConnect("VENTAS");
    buscar.addKeyListener(this);  
    modelo.addColumn("PRODUCTO");
    modelo.addColumn("CODIGO");
    modelo.addColumn("PRECIO");
    modelo.addColumn("TIPO");
    modelo.addColumn("STOCK");
    bt_PAGAR.addActionListener(cobro);
    Cancelar.addActionListener(cobro);
    ventas.setHorizontalAlignment(JLabel.CENTER);
    ventas.setBorder(BorderFactory.createMatteBorder(  1, 5, 1, 1, Color.red));
    ventas.setFont(new Font("Verdana", Font.BOLD, 25));
    ventas.setForeground(Color.ORANGE);
    Total.setFont(new Font("Verdana", Font.BOLD, 25));
    bt_PAGAR.setFont(new Font("Verdana", Font.BOLD, 25));
    Cancelar.setFont(new Font("Verdana", Font.BOLD, 25));
    setLayout(new BorderLayout());
    Buscar.setLayout(new GridLayout(2, 1, 5, 5));
    Buscar.add(ventas);
    Buscar.add(buscar);
    add(Buscar,BorderLayout.NORTH);
    add(new JScrollPane(tabla_ventas),BorderLayout.CENTER);
    Pagar.add(Total);
    Pagar.add(Cantida);
    Pagar.add(bt_PAGAR);
    Pagar.add(Cancelar);
    add(Pagar,BorderLayout.SOUTH);
 
    
 
}

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("He presionado enter");
           
                try {
                    s = conexion.createStatement();
                    rs = s.executeQuery ("select * from productos WHERE codigo="+buscar.getText());
                    while(rs.next()){
                     modelo.addRow(new Object[]{rs.getString("nombre"), rs.getString("codigo"), rs.getString("precio"),rs.getString("tipo_producto"), rs.getString("CANTIDAD")});
                     buscar.setText(null);
                     int suma_p = Integer.parseInt(rs.getString("precio"));
                    
                     total=total+suma_p;
                     Cantida.setText(Integer.toString(total));
                    }
                    } catch (SQLException ex) {
                    Logger.getLogger(Cuadro.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           
            }
	
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

}
// Lamina Cobro Xbox
class Lamina_cobro_xbox extends JPanel implements ActionListener{
       JLabel xbox = new JLabel("Xbox");
       //__titulo-----
       JPanel titulocol = new JPanel();
       JLabel Num=new JLabel("NO.xbox");
       JLabel inicio=new JLabel("Hora inicio");
       JLabel time_pedido=new JLabel("tiempo pedido");
       JLabel consumo =new JLabel("Consumo en $");
       JLabel Tempo=new JLabel("Tempori");
       JLabel start =new JLabel("Start");
       JLabel stop =new JLabel("Stop");
       
       Connection conexxion_xbox = null;
       //------------------xbox 1----------//
       JPanel cosola1 =new JPanel();
       JLabel XBOX1= new  JLabel("xbox1");
       JLabel T1=new JLabel("00:00");
       JTextField M1 = new JTextField();
       TextPrompt tiempo1 =new TextPrompt("Tiempo pedido ",M1);
       JLabel tempo1=new JLabel("");
       JLabel pesos1=new JLabel("");
       JButton x1 =new JButton("Inicio");
       JButton parar1 =new JButton("Parar");
        //------------------xbox 2----------//
       JPanel cosola2 =new JPanel();
       JLabel XBOX2= new  JLabel("xbox2");
       JLabel T2=new JLabel("00:00");
       JTextField M2 = new JTextField();
       TextPrompt tiempo2 =new TextPrompt("Tiempo pedido ",M2);
       JLabel tempo2=new JLabel("");
       JLabel pesos2=new JLabel();
       JButton x2 =new JButton("Inicio");
       JButton parar2 =new JButton("Parar");
        //------------------xbox 3----------//
       JPanel cosola3 =new JPanel();
       JLabel XBOX3= new  JLabel("xbox3");
       JLabel T3=new JLabel("00:00");
       JTextField M3 = new JTextField();
       TextPrompt tiempo3 =new TextPrompt("Tiempo pedido ",M3);
       JLabel tempo3=new JLabel("");
       JLabel pesos3=new JLabel();
       JButton x3 =new JButton("Inicio");
       JButton parar3 =new JButton("Parar");
        //------------------xbox 4----------//
       JPanel cosola4 =new JPanel();
       JLabel XBOX4= new  JLabel("xbox4");
       JLabel T4=new JLabel("00:00");
       JTextField M4 = new JTextField();
       TextPrompt tiempo4 =new TextPrompt("Tiempo pedido ",M4);
       JLabel tempo4=new JLabel("");
       JLabel pesos4=new JLabel();
       JButton x4 =new JButton("Inicio");
       JButton parar4 =new JButton("Parar");
        //------------------xbox 5----------//
       JPanel cosola5 =new JPanel();
       JLabel XBOX5= new  JLabel("xbox5");
       JLabel T5=new JLabel("00:00");
       JTextField M5 = new JTextField();
       TextPrompt tiempo5 =new TextPrompt("Tiempo pedido ",M5);
       JLabel tempo5=new JLabel("");
       JLabel pesos5=new JLabel();
       JButton x5 =new JButton("Inicio");
       JButton parar5 =new JButton("Parar");
    public Lamina_cobro_xbox(){
         conexxion_xbox =db_ventas.MySQLConnect("XBOX");
        xbox.setFont(new Font("Verdana", Font.BOLD, 25));
        T1.setFont(new Font("Verdana", Font.BOLD, 25));
        T2.setFont(new Font("Verdana", Font.BOLD, 25));
        T3.setFont(new Font("Verdana", Font.BOLD, 25));
        T4.setFont(new Font("Verdana", Font.BOLD, 25));
        T5.setFont(new Font("Verdana", Font.BOLD, 25));
        XBOX1.setFont(new Font("Verdana", Font.BOLD, 25));
        XBOX2.setFont(new Font("Verdana", Font.BOLD, 25));
        XBOX3.setFont(new Font("Verdana", Font.BOLD, 25));
        XBOX4.setFont(new Font("Verdana", Font.BOLD, 25));
        XBOX5.setFont(new Font("Verdana", Font.BOLD, 25));
        x1.setFont(new Font("Verdana", Font.BOLD, 12));
        x2.setFont(new Font("Verdana", Font.BOLD, 12));
        x3.setFont(new Font("Verdana", Font.BOLD, 12));
        x4.setFont(new Font("Verdana", Font.BOLD, 12));
        x5.setFont(new Font("Verdana", Font.BOLD, 12));
        parar1.setFont(new Font("Verdana", Font.BOLD, 12));
        parar2.setFont(new Font("Verdana", Font.BOLD, 12));
        parar3.setFont(new Font("Verdana", Font.BOLD, 12));
        parar4.setFont(new Font("Verdana", Font.BOLD, 12));
        parar5.setFont(new Font("Verdana", Font.BOLD, 12));
        Num.setFont(new Font("Verdana", Font.BOLD, 17));
        inicio.setFont(new Font("Verdana", Font.BOLD, 17));
        time_pedido.setFont(new Font("Verdana", Font.BOLD, 17));
        consumo.setFont(new Font("Verdana", Font.BOLD, 17));
        start.setFont(new Font("Verdana", Font.BOLD, 17));
        stop.setFont(new Font("Verdana", Font.BOLD, 17));
        xbox.setHorizontalAlignment(JLabel.CENTER);
        xbox.setForeground(Color.GREEN);
        xbox.setBackground(Color.BLACK);
        xbox.setBorder(BorderFactory.createMatteBorder(  1, 5, 1, 1, Color.blue));
        setLayout(new GridLayout(7, 1, 5, 5));
        
        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);
        x4.addActionListener(this);
        x5.addActionListener(this);
        titulocol.setLayout(new GridLayout(1,7,8,8));
        titulocol.add(Num);
        titulocol.add(inicio);
        titulocol.add(time_pedido);
        titulocol.add(consumo);
        titulocol.add(Tempo);
        titulocol.add(start);
        titulocol.add(stop);
        cosola1.setLayout(new GridLayout(1, 7,8,8));
        cosola1.add(XBOX1);
        cosola1.add(T1);
        cosola1.add(M1);
        cosola1.add(pesos1);
        cosola1.add(tempo1);
        cosola1.add(x1);
        cosola1.add(parar1);
        cosola2.setLayout(new GridLayout(1, 7,8,8));
        cosola2.add(XBOX2);
        cosola2.add(T2);
        cosola2.add(M2);
        cosola2.add(pesos2);
        cosola2.add(tempo2);
        cosola2.add(x2);
        cosola2.add(parar2);
        cosola3.setLayout(new GridLayout(1, 7,8,8));
        cosola3.add(XBOX3);
        cosola3.add(T3);
        cosola3.add(M3);
        cosola3.add(pesos3);
        cosola3.add(tempo3);
        cosola3.add(x3);
        cosola3.add(parar3);
        cosola4.setLayout(new GridLayout(1, 6,8,8));
        cosola4.add(XBOX4);
        cosola4.add(T4);
        cosola4.add(M4);
        cosola4.add(pesos4);
        cosola4.add(tempo4);
        cosola4.add(x4);
        cosola4.add(parar4);
        cosola5.setLayout(new GridLayout(1, 7,8,8));
        cosola5.add(XBOX5);
        cosola5.add(T5);
        cosola5.add(M5);
        cosola5.add(pesos5);
        cosola5.add(tempo5);
        cosola5.add(x5);
        cosola5.add(parar5);

        add(xbox);
        add(titulocol);
        add(cosola1);
        add(cosola2);
        add(cosola3);
        add(cosola4);
        add(cosola5);

    }

        @Override
        public void actionPerformed(ActionEvent e) {
            
             LocalDateTime locaDate = LocalDateTime.now();
            int hours  = locaDate.getHour();
            int minutes = locaDate.getMinute();
            int seconds = locaDate.getSecond();
            Object N_xbox=e.getSource();
       if(N_xbox==x1){
           int Tiempo =0;
           double importe=0;
           int importe1=0;
           T1.setText(Integer.toString(hours)+":"+Integer.toString(minutes));
                  try {
               Tiempo =Integer.parseInt(M1.getText()) ;
           importe=0.1666666666666667*Tiempo;
           importe1=(int) importe;
           pesos1.setText(Integer.toString(importe1));
           } catch (NumberFormatException e1) {
           }
                  
                 
                 Cronometro c =new Cronometro(tempo1,Tiempo,x1,M1);
                  c.start();
                  
       }
        if(N_xbox==x2){
           
             int Tiempo =0;
           double importe=0;
           int importe1=0;
                   T2.setText(Integer.toString(hours)+":"+Integer.toString(minutes));
                   try {
                Tiempo =Integer.parseInt(M2.getText()) ;
                importe=0.1666666666666667*Tiempo;
           importe1=(int) importe;
           pesos2.setText(Integer.toString(importe1));
            } catch (NumberFormatException e1) {
            }
                    
                   Cronometro c =new Cronometro(tempo2,Tiempo,x2,M2);
                  c.start();

       }
         
         if(N_xbox==x3){
             int Tiempo =0;
              double importe=0;
           int importe1=0;
                   T3.setText(Integer.toString(hours)+":"+Integer.toString(minutes));
                   try {
                 Tiempo =Integer.parseInt(M3.getText()) ;
                  importe=0.1666666666666667*Tiempo;
                   importe1=(int) importe;
                   pesos3.setText(Integer.toString(importe1));
             } catch (NumberFormatException e1) {
             }
                    
                   Cronometro c =new Cronometro(tempo3,Tiempo,x3,M3);
                  c.start();

       }
         
          if(N_xbox==x4){
              int Tiempo =0;
              double importe=0;
               int importe1=0;
                   T4.setText(Integer.toString(hours)+":"+Integer.toString(minutes));
                   try {
                  Tiempo =Integer.parseInt(M4.getText()) ;
                   importe=0.1666666666666667*Tiempo;
                   importe1=(int) importe;
                   pesos4.setText(Integer.toString(importe1));
              } catch (NumberFormatException e1) {
              }
 
                   
                   Cronometro c =new Cronometro(tempo4,Tiempo,x4,M4);
                 
                  c.start();

       }
         
           if(N_xbox==x5){
               int Tiempo =0;
                double importe=0;
                int importe1=0;
                   T5.setText(Integer.toString(hours)+":"+Integer.toString(minutes));
                   try {
                   Tiempo =Integer.parseInt(M5.getText()) ;
                    importe=0.1666666666666667*Tiempo;
                   importe1=(int) importe;
                   pesos5.setText(Integer.toString(importe1));
               } catch (NumberFormatException e1) {
               }
                   
                   Cronometro c =new Cronometro(tempo5,Tiempo,x5,M5);
                  c.start();

       }
         
           
        }
        class Cronometro extends Thread{
            JLabel caja;
            int tiempo_ped;
            JButton activo;
            JTextField desactivo;
            public Cronometro(JLabel caja, int tiempo_ped,JButton activo,JTextField desactivo){
                this.caja=caja;
                this.tiempo_ped=tiempo_ped;
                this.activo=activo;
                this.desactivo=desactivo;
            }
        public void run(){
        Integer minutos = 0 , segundos = 0, milesimas = 0;
        int mins=0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        String min="", seg="", mil="";
        try
        {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
           while( mins<=(tiempo_ped*15000))
            {
                activo.setEnabled(false);
                desactivo.setEnabled(false);
                mins++;
                Thread.sleep( 4 );
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;

                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if( milesimas == 1000 )
                {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                    }
                }

                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if( minutos < 10 ) min = "0" + minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();

                if( milesimas < 10 ) mil = "00" + milesimas;
                else if( milesimas < 100 ) mil = "0" + milesimas;
                else mil = milesimas.toString();

                //Colocamos en la etiqueta la informacion
                caja.setText( min + ":" + seg + ":" + mil );
            }
        }catch(Exception e){}
        //Cuando se reincie se coloca nuevamente en 00:00:000
        caja.setText( "00:00:000" );
        activo.setEnabled(true);
        desactivo.setEnabled(true);
    }
        boolean cronometroActivo=true;

        }
        
    }




}



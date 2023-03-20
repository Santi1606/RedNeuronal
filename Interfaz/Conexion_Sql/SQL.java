package red;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SQL {
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> pas = new ArrayList<>();
    
    String id_historial = "9";
    String fecha = "14/02/2023";
    String nom_usuario;
    String ambientes;
    String bano;
    String sp_total;
    String sp_cubierta;
    String amenities;
    String cochera;
    String precio;
    
    String pass = null;
    String nom = null;
    
    String desc_asc;
    String fecha1;
    String fecha2;
    String fecha_br;
    
    String resultado;
    int val;
    
    int vales;
    int dato;
    
    int fecha3;
    String nom_usuario2;
    int ambientes2;
    int baño2;
    int sp_total2;
    int sp_cubierta2;
    int amenities2;
    int cochera2;
    int precio2;
    
    static String user = "root";
    static String pwd = "12341234";
    static Connection cn = null;
    public static boolean status = false;
    
    public static Connection conexion(){
        status = false;
        String bd = "red_neuronal";
        String servidor = "localhost:3306";
        String url = "jdbc:mysql://" +servidor +"/" +bd;
        try{
            cn = DriverManager.getConnection(url,user,pwd);
            status = true;
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),
            "Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return cn;
    }
    
    public boolean entrada_usuario(){ 
        try{
            CallableStatement cst = conexion().prepareCall("{call sp_registrar_usuario(?,?) }");

            cst.setString(1,pass);
            cst.setString(2,nom);
            cst.executeUpdate();
            return true;

        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
            return false;
        }
    }
    
     public boolean agregar_registro(){
         boolean x = false;
        try{
            CallableStatement cst = conexion().prepareCall("{call sp_agregar_registro(?,?,?,?,?,?,?,?,?,?) }");
            
            cst.setString(1,id_historial);
            cst.setString(2,fecha);
            cst.setString(3,nom_usuario);
            cst.setString(4,ambientes);
            cst.setString(5,bano);
            cst.setString(6,sp_total);
            cst.setString(7,sp_cubierta);
            cst.setString(8,amenities);
            cst.setString(9,cochera);
            cst.setString(10,precio);
            cst.executeUpdate();
            x = true;
        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
        }
        return x;
    }
     
    public  void id_password(){
        try{
            String sql = "{call sp_ver_usuarios}";
            
            CallableStatement cst = conexion().prepareCall(sql);
            
            ResultSet consulta = cst.executeQuery(sql);
            
            while(consulta.next()){
                String idd = consulta.getString("nickname");
                String par = consulta.getString("contraseña");
                ids.add(idd);
                pas.add(par);
            }
                     
        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
        }

    }
    
    public boolean modificar_param_fecha(){
        try{
            CallableStatement cst = conexion().prepareCall("{call sp_modif_fech(?,?)}");
            
            cst.setString(1, fecha1);
            cst.setString(2, fecha2);
            cst.executeUpdate();
            return true;
        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
            return false;
        }

    }
    
    public boolean modificar_param_desc_asc(){
        try{
            CallableStatement cst = conexion().prepareCall("{call sp_modif_fech_asc(?)}");
            
            cst.setString(1, desc_asc);
            cst.executeUpdate();
            return true;
        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
            return false;
        }

    }
    
    public boolean modificar_param_br(){
        try{
            CallableStatement cst = conexion().prepareCall("{call sp_modif_fech_br(?)}");
            
            cst.setString(1, fecha_br);
            cst.executeUpdate();
            return true;
        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
            return false;
        }

    }
    
    public  String leer(){
        try{
            String sql = "{call sp_order_fecha}";
            
            CallableStatement cst = conexion().prepareCall(sql);
            
            ResultSet consulta = cst.executeQuery(sql);
            
            while(consulta.next()){
                //fecha3 = consulta.getDate("fecha");
                nom_usuario2 = consulta.getString("nom_usuario");
                ambientes2 = consulta.getInt("ambientes");
                baño2 = consulta.getInt("ambientes");
                sp_total2 = consulta.getInt("sp_total");
                sp_cubierta2 = consulta.getInt("sp_cubierta");
                amenities2 = consulta.getInt("amenities");
                cochera2 = consulta.getInt("cochera");
                precio2 = consulta.getInt("precio");
                
                
            }        
        }
        catch(java.sql.SQLException exep){
            System.out.println(exep.getErrorCode());
        }
     return resultado;  
    }
}
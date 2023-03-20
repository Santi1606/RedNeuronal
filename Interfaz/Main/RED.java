package red;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JTextArea;

public class RED {
    public static void main(String[] args) {
    Perceptron per = new Perceptron();
    SQL sql = new SQL();
    Usuario usu = new Usuario();
        
    JFrame ventana = new JFrame("Inicio"); 
    ventana.setSize(500,500); //Establece el tamaño de la ventana
    ventana.setLocationRelativeTo(null); //Localiza la ventana en el centro de la pantalla
    ventana.getContentPane().setBackground(Color.BLUE); //Establece el color de la ventana
    ventana.setLayout(null);
    
    JButton btn1 = new JButton("Iniciar sesion");
    btn1.setBounds(200, 50, 130 , 30);
        
    JButton btn2 = new JButton("Registrar");
    btn2.setBounds(200, 100, 130 , 30);
        
    JButton sal = new JButton("Salir");
    sal.setBounds(400, 230,70, 20);
    
    JButton btn6 = new JButton("Mostrar");
    btn6.setBounds(150, 200, 200 , 30);

    JButton btn7 = new JButton("Cambiar Fechas");
    btn7.setBounds(150, 150, 200 , 30);
    
    JButton btn8 = new JButton("Mostrar");
    btn8.setBounds(150, 20, 200 , 30);
    
    JButton btn9 = new JButton("Cambiar Fecha");
    btn9.setBounds(150, 150, 200 , 30);
    
    JButton btn10 = new JButton("Mostrar");
    btn10.setBounds(150, 20, 200 , 30);
    
    JButton btn11 = new JButton("Cambiar Fecha");
    btn11.setBounds(150, 150, 200 , 30);
    
    
    ventana.add(btn1);
    ventana.add(btn2);
    ventana.add(sal);


    ventana.setVisible(true);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    btn1.addActionListener((ActionEvent e) -> {
            ventana.dispose();
            JFrame v3 = new JFrame("Inicio de sesion"); //Ventana Inicio de sesion
            v3.setSize(500,500);
            v3.setLocationRelativeTo(null);
            v3.getContentPane().setBackground(Color.ORANGE);
            v3.setLayout(null);
            
            JLabel nu = new JLabel("Ingrese nombre de usuario");
            nu.setBounds(100,50, 160, 30);
            
            JTextField id = new JTextField();
            id.setBounds(100,75, 160, 20);
            
            JLabel con = new JLabel("Ingrese contraseña");
            con.setBounds(100,100, 160, 30);
            
            JTextField co = new JTextField();
            co.setBounds(100,125, 160, 20);
            
            JButton reg = new JButton("Iniciar sesion");
            reg.setBounds(200, 200, 130 , 30);
            
            v3.add(nu);
            v3.add(id);
            v3.add(con);
            v3.add(co);
            v3.add(reg);
            
            v3.setVisible(true);
            v3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reg.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {                    
                    
                    usu.password = co.getText();
                    usu.sid = id.getText();
                    if(usu.Validar_id() == true && usu.validar_usuario() == true){
                        if(usu.Buscar_id_password()== true){
                            JFrame ac = new JFrame("bienvenido"); // VENTANA DE COMIENZO
                            ac.setSize(700,600);
                            ac.setLocationRelativeTo(null);
                            ac.getContentPane().setBackground(Color.GREEN);
                            ac.setLayout(null);
                            
                            JButton btn = new JButton("Muestra de los registros ordenado de may o men");
                            btn.setBounds(200, 100, 400 , 30);
                            
                            JButton btn3 = new JButton("Muestra registros en un rango de fechas");
                            btn3.setBounds(200, 150, 300 , 30);
                            
                            JButton btn4 = new JButton("borra los registros pasado una fecha");
                            btn4.setBounds(200, 200, 350 , 30);
                            
                            JButton btn5 = new JButton("Calcular valor de vivienda");
                            btn5.setBounds(200, 250, 350 , 30);
                            
                            ac.add(btn);
                            ac.add(btn3);
                            ac.add(btn4);
                            ac.add(btn5);
                            
                            btn.addActionListener( new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {                    
                                    JFrame geu = new JFrame("Mayor - Menor"); //Ventana de may-men
                                    geu.setSize(500,500);
                                    geu.setLocationRelativeTo(null);
                                    geu.getContentPane().setBackground(Color.GREEN);
                                    geu.setLayout(null);
                                    
                                    geu.add(btn8);
                                    geu.add(btn9);
                                    
                                    geu.setVisible(true);
                                    geu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                };
                            });
                            
                            btn3.addActionListener( new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {                    
                                    JFrame rf = new JFrame("rango fechas"); 
                                    rf.setSize(500,500);
                                    rf.setLocationRelativeTo(null);
                                    rf.getContentPane().setBackground(Color.GREEN);
                                    rf.setLayout(null);
                                    
                                    rf.add(btn6);
                                    rf.add(btn7);
                                    
                                    rf.setVisible(true);
                                    rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                };
                            });
                            
                           
                            ac.setVisible(true);
                            ac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            
                            btn4.addActionListener( new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {                    
                                    JFrame br = new JFrame("borra registro"); //Ventana de may-men
                                    br.setSize(500,500);
                                    br.setLocationRelativeTo(null);
                                    br.getContentPane().setBackground(Color.GREEN);
                                    br.setLayout(null);
                                    
                                    br.add(btn10);
                                    br.add(btn11);
                                    
                                    br.setVisible(true);
                                    br.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                };
                            });
                            
                            btn5.addActionListener( new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {                    
                                    JFrame form = new JFrame("Formulario"); //Ventana de may-men
                                    form.setSize(500,700);
                                    form.setLocationRelativeTo(null);
                                    form.getContentPane().setBackground(Color.GREEN);
                                    form.setLayout(null);
                                  
                                    JLabel lbl1 = new JLabel("ingrese el barrio:");
                                    lbl1.setBounds(100,100,130,40);

                                    JTextField variable1 = new JTextField();
                                    variable1.setBounds(240,110,130,20);

                                    JLabel lbl2 = new JLabel("ingrese cant.ambientes: ");
                                    lbl2.setBounds(55,110,200,100);

                                    JTextField variable2 = new JTextField();
                                    variable2.setBounds(240,152,130,20);

                                    JLabel lbl3=new JLabel("ingrese cant.baños:");
                                    lbl3.setBounds(80, 150, 160,100);

                                    JTextField variable3=new JTextField();
                                    variable3.setBounds(240,190,130,20);

                                    JLabel lbl4=new JLabel("Ingrese SP Total:");
                                    lbl4.setBounds(100,220,130,40);

                                    JTextField variable4=new JTextField();
                                    variable4.setBounds(240,230,130,20);

                                    JLabel lbl5=new JLabel("Ingrese SP Cubierta:");
                                    lbl5.setBounds(80,250,150,60);

                                    JTextField variable5=new JTextField();
                                    variable5.setBounds(240,270,130,20);

                                    JLabel lbl6=new JLabel("1 si tiene amenities o 2 sino:");
                                    lbl6.setBounds(20,285,300,60);    

                                    JTextField variable6=new JTextField();
                                    variable6.setBounds(240,305,100,20);

                                    JLabel lbl7=new JLabel("1 si tiene cochera o 2 sino:");
                                    lbl7.setBounds(30,320,300,60);

                                    JTextField variable7=new JTextField();
                                    variable7.setBounds(240,340,100,20);


                                    JButton cal = new JButton("Calcular");
                                    cal.setBounds(170, 500, 190 , 20);
                                    
                                    form.add(lbl1);
                                    form.add(variable1);
                                    form.add(lbl2);
                                    form.add(variable2);
                                    form.add(lbl3);
                                    form.add(variable3);
                                    form.add(lbl4);
                                    form.add(variable4);
                                    form.add(lbl5);
                                    form.add(variable5);
                                    form.add(lbl6);
                                    form.add(variable6);
                                    form.add(lbl7);
                                    form.add(variable7);
                                    form.add(cal);
                                    
                                    cal.addActionListener( new ActionListener() {
                                        public void actionPerformed(ActionEvent evt) {                    
                                            per.entrada1=Double.parseDouble(variable1.getText());   
                                            per.entrada2=Double.parseDouble(variable2.getText());
                                            per.entrada3=Double.parseDouble(variable3.getText());
                                            per.entrada4=Double.parseDouble(variable4.getText());
                                            per.entrada5=Double.parseDouble(variable5.getText());
                                            per.entrada6=Double.parseDouble(variable6.getText());
                                            per.entrada7=Double.parseDouble(variable7.getText());
                                            if(sql.agregar_registro() == true){
                                                JOptionPane.showMessageDialog(null,"Su valor esta entre: " + per.ingresar_entradas());
                                            }
                                            per.entradas.clear();

                                        };
                                    });
                                    
                                    form.setVisible(true);
                                    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                };
                            });
                        }
                    }
                    if(usu.Buscar_id_password() == false){
                        JOptionPane.showMessageDialog(null,"Ingreso los datos incorrectos"); 
                    }
                    v3.dispose();
                };
            });
        });
        
        btn2.addActionListener((ActionEvent e) -> {
            JFrame v2 = new JFrame("Registro");
            v2.setSize(500,500);
            v2.setLocationRelativeTo(null);
            v2.getContentPane().setBackground(Color.yellow);
            v2.setLayout(null);

            JLabel lbl1 = new JLabel("Nickname");
            lbl1.setBounds(100,100,180,40);
            
            JTextField contra=new JTextField();
            contra.setBounds(100,125,130,20);
            
            JLabel lbl2=new JLabel(" Contraseña:");
            lbl2.setBounds(200,150,180,40);
            
            JTextField contra2=new JTextField();
            contra2.setBounds(100,200,130,20);
            

            JButton reg = new JButton("Registrar");
            reg.setBounds(250, 300, 190 , 20);
            
            v2.add(lbl1);
            v2.add(contra);
            v2.add(lbl2);
            v2.add(contra2);
            v2.add(reg);
            
            v2.setVisible(true);
            v2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reg.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {                    
                    
                    sql.pass = contra.getText();   
                    sql.nom = contra2.getText();
                    String texto;
                    if (sql.entrada_usuario()==true)
                            texto="se guardo el usuario";
                    else
                        texto="error";
                    JOptionPane.showMessageDialog(null,texto);                    
                    sql.agregar_registro();
                    
                    v2.dispose();
                };
            });
            
            
        });
        btn7.addActionListener((ActionEvent e) -> {
            JFrame cam_fech = new JFrame("Cambio de fechas"); // NUEVA VENTANA DE CAMBIO DE FECHAS
            cam_fech.setSize(500,500);
            cam_fech.setLocationRelativeTo(null);
            cam_fech.getContentPane().setBackground(Color.yellow);
            cam_fech.setLayout(null);
            
            JLabel fecha1 = new JLabel("Ingrese la fecha de inicio");
            fecha1.setBounds(100,100, 160, 30);
            
            JTextField fech1 = new JTextField();
            fech1.setBounds(100,125, 160, 20);
            
            JLabel fecha2 = new JLabel("Ingrese la fecha de fin");
            fecha2.setBounds(200,150,300,40);
            
            JTextField fech2 = new JTextField();
            fech2.setBounds(100,200, 160, 20);
            
            JButton con = new JButton("Continuar");
            con.setBounds(170, 230, 100 , 40);
           
            cam_fech.add(fecha1);
            cam_fech.add(fech1);
            cam_fech.add(fecha2);
            cam_fech.add(fech2);
            cam_fech.add(con);
            
            cam_fech.setVisible(true);
            cam_fech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            con.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {                    
                    sql.fecha1 = fech1.getText();   
                    sql.fecha2 = fech2.getText(); 
                    String texto;
                    
                    if (sql.modificar_param_fecha() == true)
                            texto = "se guardaron los cambios";
                    else
                        texto = "error";
                    JOptionPane.showMessageDialog(null,texto);                    
                    sql.agregar_registro();
                    
                    cam_fech.dispose();
                };
            });
        });
        // FALTA TERMINAR
        btn6.addActionListener((ActionEvent e) -> {
            JFrame mrf = new JFrame("Muestra en un rango de fechas"); // VENTANA DE RANGO DE FECHAS
            mrf.setSize(500,500);
            mrf.setLocationRelativeTo(null);
            mrf.getContentPane().setBackground(Color.PINK);
            mrf.setLayout(null);
            
            JTextArea areatexto = new JTextArea();
            areatexto.setBounds(25, 25, 450, 450);
            areatexto.setEditable(false);
            
            //sql.leer();
            
            mrf.add(areatexto);
            
            mrf.setVisible(true);
            mrf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //mrf.dispose();
        });
        
        btn9.addActionListener((ActionEvent e) -> {
            JFrame cam_fech = new JFrame("Cambio de asc o desc"); // NUEVA VENTANA DE CAMBIO DE FECHAS
            cam_fech.setSize(500,500);
            cam_fech.setLocationRelativeTo(null);
            cam_fech.getContentPane().setBackground(Color.yellow);
            cam_fech.setLayout(null);
            
            JLabel fecha1 = new JLabel("Ingrese 1 para desc o 2 para asc");
            fecha1.setBounds(100,100, 200, 30);
            
            JTextField fech1 = new JTextField();
            fech1.setBounds(100,125, 160, 20);
            
            JButton con = new JButton("Continuar");
            con.setBounds(170, 230, 100 , 40);
           
            cam_fech.add(fecha1);
            cam_fech.add(fech1);
            cam_fech.add(con);
            
            cam_fech.setVisible(true);
            cam_fech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            con.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    //sql.desc_asc = Integer.parseInt(fech1.getText());
                    
                    sql.desc_asc = fech1.getText();
                    
                    String texto;
                    
                    if (sql.modificar_param_desc_asc() == true)
                            texto = "se guardaron los cambios";
                    else
                        texto = "error";
                    JOptionPane.showMessageDialog(null,texto);                    
                    sql.agregar_registro();
                    
                    cam_fech.dispose();
                };
            });
        });
        
         btn11.addActionListener((ActionEvent e) -> {
            JFrame cam_fech = new JFrame("Cambio de fecha"); // NUEVA VENTANA DE CAMBIO DE FECHAS
            cam_fech.setSize(500,500);
            cam_fech.setLocationRelativeTo(null);
            cam_fech.getContentPane().setBackground(Color.yellow);
            cam_fech.setLayout(null);
            
            JLabel fecha1 = new JLabel("Ingrese la fecha");
            fecha1.setBounds(100,100, 160, 30);
            
            JTextField fech1 = new JTextField();
            fech1.setBounds(100,125, 160, 20);
            
            JButton con = new JButton("Continuar");
            con.setBounds(170, 230, 100 , 40);
           
            cam_fech.add(fecha1);
            cam_fech.add(fech1);
            cam_fech.add(con);
            
            cam_fech.setVisible(true);
            cam_fech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            con.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {                    
                    sql.fecha_br = fech1.getText();    
                    String texto;
                    
                    if (sql.modificar_param_br() == true)
                            texto = "se guardaron los cambios";
                    else
                        texto = "error";
                    JOptionPane.showMessageDialog(null,texto);                    
                    sql.agregar_registro();
                    
                    cam_fech.dispose();
                };
            });
        });
         
        sal.addActionListener((ActionEvent e) -> {
            ventana.dispose();

        });
    }
}
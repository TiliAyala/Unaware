/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unaware;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.shuffle;
import static java.util.Collections.shuffle;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author TiliAyala
 */
public class Unaware {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //INICIALIZACIÓN DE LA VENTANA
        JFrame ventana = new JFrame();
        ventana.setBounds(0, 0, 800, 500);
        ventana.setLocationRelativeTo(null); //Esto hará que el juego siempre aparezca en el centro de la pantalla
        ventana.setResizable(false);
        ventana.setTitle("Unaware");
        ventana.setDefaultCloseOperation(3);
        ventana.setEnabled(true);
        ventana.setVisible(true);
        ventana.setIconImage(new ImageIcon(Unaware.class.getResource("/Resources/logor.png")).getImage());
        
        
        //ventana.setIconImage(new ImageIcon(Unaware.class.getClassLoader().getResource("\\Resources\\1Vida.png")).getImage());
        
        
        ejecutarMenu(ventana); //Esto creará el panel con los elementos del menú
    }
    
    public static void ejecutarMenu(JFrame ventana){ //El argumento JFrame permite manipular la ventana inicial
        
        
        
       //INICIALIZACIÓN DEL PANEL
        
       JLayeredPane menu = new JLayeredPane(); // Un JLayeredPane es un JPanel que permite la acoplación de varias capas de objetos.
                                                                       // Nos servirá para poder poner elementos como botones encima de otro elementos como imágenes.
       menu.setBounds(0, 0, 800, 500);
       menu.setVisible(true);
       ventana.add(menu);
       
       //FONDO DE PANTALLA DEL MENÚ
       
       JLabel fondo1 = new JLabel(); //Dado que el fondo es animado, dividir la animación en cuatro partes en lugar de un solo
       JLabel fondo2 = new JLabel();//fondo grande, de alguna manera optimiza recursos.
       JLabel fondo3 = new JLabel();
       JLabel fondo4 = new JLabel();
       
       
       ImageIcon fondoreal = new ImageIcon(Unaware.class.getResource("/Resources/Fondo_menu.gif"));
       ImageIcon fondoreal2 = new ImageIcon(Unaware.class.getResource("/Resources/Fondo_menu2.png")); //Imagen del fondo 2
       
       fondo1.setBounds(0, 0, 400, 250); //Cada división del fondo ocupa 1/4 de la pantalla, así que sus dimensiones deben ser
       fondo2.setBounds(400, 0, 400, 250);//ubicadas acorde a su tamaño y localización.
       fondo3.setBounds(0, 250, 400, 250);
       fondo4.setBounds(400, 249, 400, 250);
       
       fondo1.setIcon(fondoreal); //Asignación de la imagen
       fondo2.setIcon(fondoreal2);
       fondo3.setIcon(fondoreal2);
       fondo4.setIcon(fondoreal);
       
       menu.add(fondo1, JLayeredPane.DEFAULT_LAYER); //Ubicación del fondo en el panel. El segundo atributo se refiere a la capa que ocupa el objeto.
       menu.add(fondo2, JLayeredPane.DEFAULT_LAYER);// La capa DEFAULT es la más baja.
       menu.add(fondo3, JLayeredPane.DEFAULT_LAYER);
       menu.add(fondo4, JLayeredPane.DEFAULT_LAYER);
       
       //IMAGEN DE TITULO
        JLabel title = new JLabel();
        title.setBounds(214, 100, 373,68);
        
        ImageIcon Im_title = new ImageIcon(Unaware.class.getResource("/Resources/Titulo.png")); //Imagen del fondo
        title.setIcon(Im_title);
        
        menu.add(title, JLayeredPane.POPUP_LAYER); //La capa POPUP es la segunda más alta del JLayeredPane
        
        //BOTONES DEL MENÚ
        
        // >> BOTÓN DE JUGAR
        Boton Btn_Jugar = new Boton(); //Esta es una clase creada en este programa llamada Boton. Un Boton tiene su propio
                                                         //JLabel para ponerle icono y una string llamada file que nos permitirá añadir animaciones
                                                         //al botón.
        Btn_Jugar.file.append("00_Btn_Jugar.png"); //El atributo .file del botón es un StringBuilder, que es un String que puede ser
                                                                       //modificado en el programa. Nos servirá para cambiar el nombre del archivo de
                                                                       //la textura del programa, y eso a su vez cambiará la textura dentro del Juego.
                                                                       
        ImageIcon Im_Jugar = new ImageIcon(Unaware.class.getResource("/Botones/"+Btn_Jugar.file));
        
        Btn_Jugar.cuerpo.setBounds(200, 240, 80, 80); //Propiedades del cuerpo del Botón
        Btn_Jugar.cuerpo.setIcon(Im_Jugar);
        Btn_Jugar.cuerpo.setVisible(true);
        menu.add(Btn_Jugar.cuerpo, JLayeredPane.POPUP_LAYER); 
        
        interaccionBoton(Btn_Jugar, ventana, menu); //Esta función es SUPREMAMENTE IMPORTANTE, pues le da utilidad al botón. Sin ella, el botón
                                                //sería únicamente una imagen estática. Además, es global, por lo que puede ser aplicada a todos
                                                //los botones que necesite el juego.
        
        // >> BOTÓN DE INFORMACIÓN
        Boton Btn_Info = new Boton(); //Esta es una clase creada en este programa llamada Boton. Un Boton tiene su propio
                                                         //JLabel para ponerle icono y una string llamada file que nos permitirá añadir animaciones
                                                         //al botón.
        Btn_Info.file.append("00_Btn_Info.png"); //El atributo .file del botón es un StringBuilder, que es un String que puede ser
                                                                       //modificado en el programa. Nos servirá para cambiar el nombre del archivo de
                                                                       //la textura del programa, y eso a su vez cambiará la textura dentro del Juego.
                                                                       
        ImageIcon Im_Info = new ImageIcon(Unaware.class.getResource("/Botones/"+Btn_Info.file));
        
        Btn_Info.cuerpo.setBounds(360, 240, 80, 80); //Propiedades del cuerpo del Botón
        Btn_Info.cuerpo.setIcon(Im_Info);
        Btn_Info.cuerpo.setVisible(true);
        menu.add(Btn_Info.cuerpo, JLayeredPane.POPUP_LAYER); 
        
        interaccionBoton(Btn_Info, ventana, menu); //Esta función es SUPREMAMENTE IMPORTANTE, pues le da utilidad al botón. Sin ella, el botón
                                                //sería únicamente una imagen estática. Además, es global, por lo que puede ser aplicada a todos
                                                //los botones que necesite el juego.
                                                
        // >> BOTÓN DE APAGAR
        Boton Btn_Apagar = new Boton(); //Esta es una clase creada en este programa llamada Boton. Un Boton tiene su propio
                                                         //JLabel para ponerle icono y una string llamada file que nos permitirá añadir animaciones
                                                         //al botón.
        Btn_Apagar.file.append("00_Btn_Apagar.png"); //El atributo .file del botón es un StringBuilder, que es un String que puede ser
                                                                       //modificado en el programa. Nos servirá para cambiar el nombre del archivo de
                                                                       //la textura del programa, y eso a su vez cambiará la textura dentro del Juego.
                                                                       
        ImageIcon Im_Apagar = new ImageIcon(Unaware.class.getResource("/Botones/"+Btn_Apagar.file));
        
        Btn_Apagar.cuerpo.setBounds(520, 240, 80, 80); //Propiedades del cuerpo del Botón
        Btn_Apagar.cuerpo.setIcon(Im_Apagar);
        Btn_Apagar.cuerpo.setVisible(true);
        menu.add(Btn_Apagar.cuerpo, JLayeredPane.POPUP_LAYER); 
        
        interaccionBoton(Btn_Apagar, ventana, menu); //Esta función es SUPREMAMENTE IMPORTANTE, pues le da utilidad al botón. Sin ella, el botón
                                                //sería únicamente una imagen estática. Además, es global, por lo que puede ser aplicada a todos
                                                //los botones que necesite el juego.
        
    }
    
    private static void interaccionBoton(Boton boton, JFrame ventana, JLayeredPane contenido){
        
          
        
        MouseListener mouse = new MouseListener(){
            
            //Se crea con este método un MouseListener, que le otorga acciones a los objetos que estén asociados al método
            //a través del Mouse. Es un método abstracto, así que debe implementar todas sus funciones al código así no se usen.
            
            boolean oprimido= false;
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
                //EXPLICACIÓN: Los archivos .png de todos los botones empiezan con un código:
                //Si el archivo empieza con 00 es porque es la imagen de cuando el botón está quieto.
                //Si el archivo empieza con 01 es porque es la imagen de cuando el cursor pasa por encima del botón.
                //Si el archivo empieza con 02 es porque es la imagen de cuando se oprime el botón.
                //
                //Al saber esto, podemos manipular ese número y asignarle al botón una nueva imagen dependiendo de lo que
                //esté sucediendo. Como todas las imagenes de todos los botones funcionan con este código, esta función es
                //aplicable a todos los botones y no hay necesidad de hacer un MouseListener por cada uno de los botones que haya.
                
                
                
                
                //Para darle a cada Botón una función individual, inspeccionaremos su nombre de archivo y usaremos un if para
                //separar sus funciones.
                
                oprimido=true; //Hace que el botón pueda cambiar a su apariencia de cuando se oprime
                
                if (boton.file.charAt(7)=='A'){ //Si el nombre del archivo tiene una A en el indice 7, es porque el botón es el de Apagar, 
                                                            //asi que lo que haya en este if solo lo ejecutará el botón Apagar.
                   
                   boton.file.setCharAt(1, '2');
                
                   ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+boton.file));
                   boton.cuerpo.setIcon(nuevo); //Cambiar el botón a la imagen de presionado.
                    
                   
                   ventana.dispose(); //Cerrar el programa
                    
                }
                
                
                
                if (boton.file.charAt(7)=='I'){ //Si el nombre del archivo tiene una I en el indice 7, es porque el botón es el de Info, 
                                                            //asi que lo que haya en este if solo lo ejecutará el botón Info.
                   
                   boton.file.setCharAt(1, '2');
                
                   ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+boton.file));
                   boton.cuerpo.setIcon(nuevo); //Cambiar el botón a la imagen de presionado.
                   
                   JLayeredPane popup = new JLayeredPane();
                    
                   
                   infoPopup(contenido, popup);
                   
                  
                    
                }
                
                if (boton.file.charAt(7)=='J'){ try {
                    //Si el nombre del archivo tiene una J en el indice 7, es porque el botón es el de Jugar,
                    //asi que lo que haya en este if solo lo ejecutará el botón Jugar.

                    playMenu(ventana, contenido);
                    } catch (FileNotFoundException ex) {
                        
                    }
                       
                }
                
                if (boton.file.charAt(7)=='R'){ //Si el nombre del archivo tiene una R en el indice 7, es porque el botón es el de Regresar, 
                                                            //asi que lo que haya en este if solo lo ejecutará el botón Regresar.
                       
                       contenido.setVisible(false);
                       borrarContenido(ventana, contenido);
                       ejecutarMenu(ventana);
                       
                }
                
                
                
               
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                oprimido=true; //Hace que el botón pueda cambiar a su apariencia de cuando se oprime
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              oprimido=false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
                if(oprimido==false){
                    boton.file.setCharAt(1, '1');
                
                    ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+boton.file));
                    boton.cuerpo.setIcon(nuevo);
                }
                
                
                //EXPLICACIÓN: Los archivos .png de todos los botones empiezan con un código:
                //Si el archivo empieza con 00 es porque es la imagen de cuando el botón está quieto.
                //Si el archivo empieza con 01 es porque es la imagen de cuando el cursor pasa por encima del botón.
                //Si el archivo empieza con 02 es porque es la imagen de cuando se oprime el botón.
                //
                //Al saber esto, podemos manipular ese número y asignarle al botón una nueva imagen dependiendo de lo que
                //esté sucediendo. Como todas las imagenes de todos los botones funcionan con este código, esta función es
                //aplicable a todos los botones y no hay necesidad de hacer un MouseListener por cada uno de los botones que haya.
                
            }

            @Override
            
            
            
            public void mouseExited(MouseEvent e) {
                
                oprimido=false; 
                
               boton.file.setCharAt(1, '0');
                
                ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+boton.file));
                boton.cuerpo.setIcon(nuevo);
                
                //EXPLICACIÓN: Los archivos .png de todos los botones empiezan con un código:
                //Si el archivo empieza con 00 es porque es la imagen de cuando el botón está quieto.
                //Si el archivo empieza con 01 es porque es la imagen de cuando el cursor pasa por encima del botón.
                //Si el archivo empieza con 02 es porque es la imagen de cuando se oprime el botón.
                //
                //Al saber esto, podemos manipular ese número y asignarle al botón una nueva imagen dependiendo de lo que
                //esté sucediendo. Como todas las imagenes de todos los botones funcionan con este código, esta función es
                //aplicable a todos los botones y no hay necesidad de hacer un MouseListener por cada uno de los botones que haya.
            }
            
         
            
        };
        
        boton.cuerpo.addMouseListener(mouse); //Esto permite que el botón que entre a la función adquiera sus propiedades.
    }
    
    private static void infoPopup(JLayeredPane menu, JLayeredPane popup){
        popup.setBounds(0, 0, 800, 500);
        menu.add(popup, JLayeredPane.DRAG_LAYER);//La capa DRAG es la más alta posible
        popup.setVisible(true);
        
        JLabel papel = new JLabel (); //Este es el anuncio de la información
        
        ImageIcon Im_papel = new ImageIcon(Unaware.class.getResource("/Resources/Fondo_info.png"));
        papel.setIcon(Im_papel);
        
        papel.setBounds(0, 0, 800, 500);
        popup.add(papel, JLayeredPane.POPUP_LAYER);
        
         // >> BOTÓN DE REGRESAR
        Boton Btn_Regresar = new Boton(); //Esta es una clase creada en este programa llamada Boton. Un Boton tiene su propio
                                                         //JLabel para ponerle icono y una string llamada file que nos permitirá añadir animaciones
                                                         //al botón.
        Btn_Regresar.file.append("00_Btn_Regresar.png"); //El atributo .file del botón es un StringBuilder, que es un String que puede ser
                                                                       //modificado en el programa. Nos servirá para cambiar el nombre del archivo de
                                                                       //la textura del programa, y eso a su vez cambiará la textura dentro del Juego.
                                                                       
        ImageIcon Im_Regresar = new ImageIcon(Unaware.class.getResource("/Botones/"+Btn_Regresar.file));
        
        Btn_Regresar.cuerpo.setBounds(600, 330, 80, 80); //Propiedades del cuerpo del Botón
        Btn_Regresar.cuerpo.setIcon(Im_Regresar);
        Btn_Regresar.cuerpo.setVisible(true);
        popup.add(Btn_Regresar.cuerpo, JLayeredPane.DRAG_LAYER);
        
        // PARA ESTA VENTANA ESPECIFICAMENTE SE HARÁ UN NUEVOS LISTENER
        //Uno de mouse que escuche al botón y nos devuelva al menú principal.
        
        MouseListener salir = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                popup.setVisible(false);
                menu.remove(popup);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //Función vacía
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //Función vacía
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               Btn_Regresar.file.setCharAt(1, '1');
                
                ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+Btn_Regresar.file));
                Btn_Regresar.cuerpo.setIcon(nuevo);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Btn_Regresar.file.setCharAt(1, '0');
                
                ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+Btn_Regresar.file));
                Btn_Regresar.cuerpo.setIcon(nuevo);
            }
            
        };
        
        Btn_Regresar.cuerpo.addMouseListener(salir);
        
    }
    
    private static void playMenu(JFrame ventana, JLayeredPane contenido) throws FileNotFoundException{ //Esto nos lleva al menú de jugar

        
        //AQUÍ SE GUARDAN LAS PREGUNTAS, PARA QUE SÓLAMENTE SE GENEREN UNA VEZ.
        
        Vector <Pregunta> PaquetePreguntas = new Vector <Pregunta>();
        
        Pregunta p1 = new Pregunta();
        p1.id = 1;
        p1.pregunta = "¿Cuál de las siguientes funciones no devuelve un resultado?";
        p1.respuestacorrecta = "void";
        p1.respuestab = "int";
        p1.respuestac = "null";
        p1.respuestad = "private";
        PaquetePreguntas.add(p1);
        
        Pregunta p2 = new Pregunta();
        p2.id = 2;
        p2.pregunta = "¿Cuánto tamaño ocupa un int en bytes?";
        p2.respuestacorrecta = "4";
        p2.respuestab = "2";
        p2.respuestac = "8";
        p2.respuestad = "1";
        PaquetePreguntas.add(p2);
        
        Pregunta p3 = new Pregunta();
        p3.id = 3;
        p3.pregunta = "¿Cuál de las siguientes adopta los atributos de su clase?";
        p3.respuestacorrecta = "Objeto";
        p3.respuestab = "Clase";
        p3.respuestac = "Función";
        p3.respuestad = "Método";
        PaquetePreguntas.add(p3);
        
        Pregunta p4 = new Pregunta();
        p4.id = 4;
        p4.pregunta = "Almacena dos tipos de datos posibles:";
        p4.respuestacorrecta = "boolean";
        p4.respuestab = "int";
        p4.respuestac = "long";
        p4.respuestad = "char";
        PaquetePreguntas.add(p4);
        
        Pregunta p5 = new Pregunta();
        p5.id = 5;
        p5.pregunta = "Popular Marco de Desarrollo Ágil de Software:";
        p5.respuestacorrecta = "SCRUM";
        p5.respuestab = "SPOON";
        p5.respuestac = "TDHA";
        p5.respuestad = "SPRINT";
        PaquetePreguntas.add(p5);
        
        Pregunta p6 = new Pregunta();
p6.id = 6;
p6.pregunta = "Clase para crear espacios de texto pequeños:";
p6.respuestacorrecta = "JTextField";
p6.respuestab = "JPanel";
p6.respuestac = "JButton";
p6.respuestad = "JFrame";
PaquetePreguntas.add(p6);

Pregunta p7 = new Pregunta();
p7.id = 7;
p7.pregunta = "Método para obtener lo escrito en un área de texto:";
p7.respuestacorrecta = ".getText";
p7.respuestab = ".getId";
p7.respuestac = ".getTxt";
p7.respuestad = ".add";
PaquetePreguntas.add(p7);


Pregunta p8 = new Pregunta();
p8.id = 8;
p8.pregunta = "Librería que sirve para manejar JFrame y JPanel entre otros:";
p8.respuestacorrecta = "swing";
p8.respuestab = "awd";
p8.respuestac = "util";
p8.respuestad = "Json";
PaquetePreguntas.add(p8);


Pregunta p9 = new Pregunta();
p9.id = 9;
p9.pregunta = "Símbolo con el que se cierra cada línea de código:";
p9.respuestacorrecta = ";";
p9.respuestab = ":";
p9.respuestac = "()";
p9.respuestad = ":)";
PaquetePreguntas.add(p9);


Pregunta p10 = new Pregunta();
p10.id = 10;
p10.pregunta = "Método que establece dimensiones";
p10.respuestacorrecta = ".setBounds()";
p10.respuestab = ".getText";
p10.respuestac = ".add";
p10.respuestad = ".setView()";
PaquetePreguntas.add(p10);

Pregunta p11 = new Pregunta();
        p11.id = 11;
        p11.pregunta = "Método que devuelve el índice:";
        p11.respuestacorrecta = "indexof";
        p11.respuestab = "Size";
        p11.respuestac = "whereis";
        p11.respuestad = "contains";
        PaquetePreguntas.add(p11);

Pregunta p12 = new Pregunta();
        p12.id = 12;
        p12.pregunta = "Método que crea instancias de una clase:";
        p12.respuestacorrecta = "Constructor";
        p12.respuestab = "Modificador";
        p12.respuestac = "Interfaz";
        p12.respuestad = "Ninguna";
        PaquetePreguntas.add(p12);

Pregunta p13 = new Pregunta();
        p13.id = 13;
        p13.pregunta = "Resolución con secuencias de instrucciones y subrutinas:";
        p13.respuestacorrecta = "Estructurada";
        p13.respuestab = "POO";
        p13.respuestac = "Avanzada";
        p13.respuestad = "Alta";
        PaquetePreguntas.add(p13);

Pregunta p14 = new Pregunta();
        p14.id = 14;
        p14.pregunta = "Construcción mediante clases y objetos:";
        p14.respuestacorrecta = "POO";
        p14.respuestab = "Estructurada";
        p14.respuestac = "Avanzada";
        p14.respuestad = "Alta";
        PaquetePreguntas.add(p14);
        
        //AQUÍ, DEL MISMO MODO, SE GUARDARÁN LOS NIVELES
        
        Vector <Nivel> paqueteNiveles = new Vector <Nivel>();
        
        Nivel n0 = new Nivel();
        n0.id = 0;
        n0.name = "Debug";
        paqueteNiveles.add(n0);
        
        Nivel n1 = new Nivel();
        n1.id = 1;
        n1.name = "Mario";
        paqueteNiveles.add(n1);
        
        Nivel n2 = new Nivel();
        n2.id = 2;
        n1.name = "Cracken";
        paqueteNiveles.add(n2);
        
        Nivel n3 = new Nivel();
        n3.id = 3;
        n3.name = "Botón";
        paqueteNiveles.add(n3);
        
        Nivel n4 = new Nivel();
        n4.id = 4;
        n4.name = "Tablero";
        paqueteNiveles.add(n4);
        
        borrarContenido(ventana, contenido);
        
        Jugador player = new Jugador(); //Crear al jugador
        player.gender.append("ML.gif"); //Determina que el jugador es hombre
        
        player.cuerpo.setBounds(600, 270, 150, 150);
        ImageIcon exhibit = new ImageIcon(Unaware.class.getResource("/Players/"+player.gender));
        player.cuerpo.setIcon(exhibit);

        //INSTANCIA DE LA NUEVA VENTANA
        JLayeredPane menuJugar = new JLayeredPane();
        menuJugar.setBounds(0,0,800,500);
        ventana.add(menuJugar);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,800,500);
        ImageIcon Im_fondo = new ImageIcon(Unaware.class.getResource("/Resources/Fondo_Jugar.gif"));
        fondo.setIcon(Im_fondo);
        
        menuJugar.add(fondo, JLayeredPane.DEFAULT_LAYER);
        menuJugar.add(player.cuerpo, JLayeredPane.DRAG_LAYER);
        
        //PUNTAJE
        
        File puntajes = scores(); //Esto genera las hojas de los puntajes en caso de que no existan
        
        Scanner lector = new Scanner(puntajes);
        
        
        
        JLabel cuadro = new JLabel();
        cuadro.setBounds(360, 20, 360, 240);
        cuadro.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/scoreboard.png")));
        menuJugar.add(cuadro, JLayeredPane.POPUP_LAYER);
        
        JLabel titulo = new JLabel("HIGH SCORES");
        titulo.setBounds(470,20,360,50);
        titulo.setFont(new Font("Futura", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        menuJugar.add(titulo, JLayeredPane.DRAG_LAYER);
        
        
        JLabel puntaje1 = new JLabel();
        puntaje1.setText(lector.nextLine()+"    "+lector.nextLine());
        puntaje1.setBounds(390,70,360,50);
        puntaje1.setFont(new Font("Futura", Font.BOLD, 20));
        puntaje1.setForeground(Color.WHITE);
        menuJugar.add(puntaje1, JLayeredPane.DRAG_LAYER);
        
        JLabel puntaje2 = new JLabel();
        puntaje2.setText(lector.nextLine()+"    "+lector.nextLine());
        puntaje2.setBounds(390,100,360,50);
        puntaje2.setFont(new Font("Futura", Font.BOLD, 20));
        puntaje2.setForeground(Color.WHITE);
        menuJugar.add(puntaje2, JLayeredPane.DRAG_LAYER);
        
        JLabel puntaje3 = new JLabel();
        puntaje3.setText(lector.nextLine()+"    "+lector.nextLine());
        puntaje3.setBounds(390,130,360,50);
        puntaje3.setFont(new Font("Futura", Font.BOLD, 20));
        puntaje3.setForeground(Color.WHITE);
        menuJugar.add(puntaje3, JLayeredPane.DRAG_LAYER);
        
        JLabel puntaje4 = new JLabel();
        puntaje4.setText(lector.nextLine()+"    "+lector.nextLine());
        puntaje4.setBounds(390,160,360,50);
        puntaje4.setFont(new Font("Futura", Font.BOLD, 20));
        puntaje4.setForeground(Color.WHITE);
        menuJugar.add(puntaje4, JLayeredPane.DRAG_LAYER);
        
        JLabel puntaje5 = new JLabel();
        puntaje5.setText(lector.nextLine()+"    "+lector.nextLine());
        puntaje5.setBounds(390,190,360,50);
        puntaje5.setFont(new Font("Futura", Font.BOLD, 20));
        puntaje5.setForeground(Color.WHITE);
        menuJugar.add(puntaje5, JLayeredPane.DRAG_LAYER);
        
        
        
        //BOTONES DE LA VENTANA
        
        //Botón para empezar a jugar
        
        Boton play = new Boton();
        play.cuerpo.setBounds(60, 20, 240, 240);
        play.file.append("00_Btn_Empezar.png");
        ImageIcon Im_play = new ImageIcon(Unaware.class.getResource("/Botones/"+play.file));
        play.cuerpo.setIcon(Im_play);
        
        menuJugar.add(play.cuerpo, JLayeredPane.DRAG_LAYER);
        
        
        //Boton para regresar al menú principal
        
        Boton regresar = new Boton();
        regresar.cuerpo.setBounds(60, 360, 80, 80);
        regresar.file.append("00_Btn_Regresar.png");
        ImageIcon Im_regresar = new ImageIcon(Unaware.class.getResource("/Botones/"+regresar.file));
        regresar.cuerpo.setIcon(Im_regresar);
        
        Boton genero = new Boton();
        genero.cuerpo.setBounds(220, 360, 80, 80);
        genero.file.append("00_Btn_M.png");
        ImageIcon Im_genero = new ImageIcon(Unaware.class.getResource("/Botones/"+genero.file));
        genero.cuerpo.setIcon(Im_genero);
        
        menuJugar.add(regresar.cuerpo, JLayeredPane.DRAG_LAYER);
        menuJugar.add(genero.cuerpo, JLayeredPane.DRAG_LAYER);
        
        interaccionBoton(regresar, ventana, menuJugar);
        interaccionBoton(genero, ventana, menuJugar);
        
        //Listener para el botón de cambio de personaje
        
        MouseListener cambiarPersonaje = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(genero.file.charAt(7)=='M'){
                    genero.file.setCharAt(7, 'F');
                    genero.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Botones/"+genero.file)));
                    player.gender.setCharAt(0, 'F');
                    player.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Players/"+player.gender)));
                    
                }
                else{
                    genero.file.setCharAt(7, 'M');
                    genero.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Botones/"+genero.file)));
                    player.gender.setCharAt(0, 'M');
                    player.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Players/"+player.gender)));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //No se usa
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //No se usa
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //No se usa
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //No se usa
            }
            
        };
        
        //Listener para el botón de empezar a Jugar
        
        MouseListener empezarInt = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                       player.vidas=3; //Hace que cada vez que inicie el jugador tenga tres vidas
                       inBetween(ventana,menuJugar,player, 0, 0, 0, PaquetePreguntas, paqueteNiveles, false);
                       
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //No usado
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //No usado
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                play.file.setCharAt(1, '1');
                
                 ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+play.file));
                 play.cuerpo.setIcon(nuevo);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                play.file.setCharAt(1, '0');
                
                 ImageIcon nuevo = new ImageIcon (Unaware.class.getResource("/Botones/"+play.file));
                 play.cuerpo.setIcon(nuevo);
            }
            
        };
        
        play.cuerpo.addMouseListener(empezarInt);
        genero.cuerpo.addMouseListener(cambiarPersonaje);
        
        
        
        
        
        
        
    }
    
    
    private static void borrarContenido(JFrame ventana, JLayeredPane residuo){
        residuo.setVisible(false);
        ventana.remove(residuo); //Hay que asegurarnos de que cuando el menú se ejecute, la ventana esté completamente vacía.
                                       //la función ejecutarMenu() no solo se instancia al inicio del programa, también puede ser invocada
                                       //cuando el jugador sale de los minijuegos al menú principal, por ejemplo. Por lo tanto, es importante
                                       //que la función borre cualquier rastro de minijuego que pueda quedar antes de volver al menú para
                                       //que funcione correctamente.
                                       
        //IMPORTANTE, ESTA FUNCIÓN SOLO SE EJECUTARÁ CUANDO SE CAMBIE ENTRE PANTALLAS.
    }
    
    
    // A PARTIR DE ESTE PUNTO EN EL CÓDIGO SE GESTIONARÁN LOS NIVELES
    
    private static void inBetween (JFrame ventana, JLayeredPane contenido, Jugador player, int puntaje, int idNivel, int idPregunta, Vector <Pregunta>Preguntas, Vector<Nivel> Niveles, boolean gameOver){
        //Esta función es la más importante del juego. randomiza las preguntas y los niveles. Hay que entrar a esta función después de cada nivel
        
        borrarContenido(ventana, contenido);
        if (gameOver==true){
            player.vidas=player.vidas-1;
        }

        
        if(player.vidas>0){
             JLayeredPane inbetween = new JLayeredPane();
        inbetween.setBounds(0, 0, 800, 500);
        ventana.add(inbetween);
        JLabel fondocambio = new JLabel();
        fondocambio.setBounds(0, 0, 800, 500);
        inbetween.add(fondocambio, JLayeredPane.DEFAULT_LAYER);
        
        JLabel vidas = new JLabel();//ESTE CONDICIONAL DETERMINARÁ LA CANTIDAD DE VIDAS
        
        if(player.vidas==3){
            vidas.setBounds(250, 80, 300, 100);
            vidas.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/3Vidas.png")));
        }
        else if(player.vidas==2){
            vidas.setBounds(300, 80, 200, 100);
            vidas.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/2Vidas.png")));
        }
        else if(player.vidas==1){
            vidas.setBounds(350, 80, 100, 100);
            vidas.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/1Vida.png")));
        }

        
        inbetween.add(vidas, JLayeredPane.DRAG_LAYER);
        
        
        ImageIcon Im_fondocambio = new ImageIcon(Unaware.class.getResource("/Fondos/Fondo_inbetween.gif"));
        fondocambio.setIcon(Im_fondocambio);
        
        Random picker = new Random(); //Nos dará una pregunta aleatoria.
        
        int preguntaRandom = picker.nextInt(Preguntas.size()); //Genera un número aleatorio entre 5 y 0
        
        int nivelRandom = (int)(Math.random()*(5-1+1)+1);  //Genera un número aleatorio entre 4 y 1
        
        JLabel pregunta = new JLabel(Preguntas.elementAt(preguntaRandom).pregunta, SwingConstants.CENTER);
        pregunta.setBounds(0,0,800,450);
        pregunta.setFont(new Font("Futura", Font.PLAIN, 28));
        pregunta.setForeground(Color.WHITE);
        inbetween.add(pregunta, JLayeredPane.POPUP_LAYER);
        
        
        JLabel puntos = new JLabel(String.valueOf(puntaje), SwingConstants.CENTER);
        puntos.setBounds(345,100,100,450);
        puntos.setFont(new Font("Futura", Font.PLAIN, 28));
        puntos.setForeground(Color.WHITE);
        inbetween.add(puntos, JLayeredPane.DRAG_LAYER);
        
        
        ActionListener cambio = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fondocambio.getIcon()==Im_fondocambio){
                    
                    niveles( ventana,  inbetween, player, puntaje, nivelRandom, preguntaRandom, Preguntas, Niveles );
                    
                    
                }
            }
            
        };
        
        Timer contador = new Timer(3000, cambio);
        contador.start();
        contador.setRepeats(false);
        }
        else{
            try {
                actualizarPuntaje(puntaje, ventana);
            } catch (FileNotFoundException ex) {
            }
        }
        
        
        
    }
    
    private static void debug(JFrame ventana, JLayeredPane contenido, Jugador player){
        //El nivel debug es un nivel que no existirá en el juego, únicamente sirve para hacer pruebas.
        //Pueden experimentar aquí lo que deseen, pero no implementen esta función en el juego real.
        
        //Valores importantes que determinan el movimiento del personaje
        
        borrarContenido(ventana,contenido);
        
        int xVelocity = 1;
        int yVelocity = 1;

        
        JLayeredPane nivelDebug = new JLayeredPane();
        nivelDebug.setBounds(0,0,800,500);
        ventana.add(nivelDebug);
        
        Nivel debug = new Nivel();
        debug.id=0;
        
        nivelDebug.add(player.cuerpo, JLayeredPane.DRAG_LAYER);
        
        
        //MOVIMIENTO ANIMADO DEL PERSONAJE
        
        ActionListener movimiento = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 int newX = player.cuerpo.getX() - xVelocity;

                  if(player.cuerpo.getX()>100){
                    player.cuerpo.setBounds(newX, 270, 150, 150);
                    }
                  else{
                      player.cuerpo.setBounds(100 ,270,150,150);
                  }
                
                
            }
            
        };
        
        Timer tiempoMovimiento = new Timer(10, movimiento);
        tiempoMovimiento.start();
        
       
       
    }
    
    private static void niveles(JFrame ventana, JLayeredPane contenido, Jugador player, int puntaje, int idNivel, int idPregunta, Vector <Pregunta>Preguntas, Vector <Nivel> Niveles){
        borrarContenido(ventana, contenido);
        
        
        
        JLabel tiempo = new JLabel();
        tiempo.setBounds(710, 400, 50, 50);
        tiempo.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Timer.gif")));
        
        Timer contadortiempo = new Timer(10000, null);
        contadortiempo.setRepeats(false);
        
        contadortiempo.start();
        
        ActionListener perdistetiempo = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tiempo.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/timeover.png")));
                    tiempo.setBounds(0,0,800,500);
                   ActionListener muerte = new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                JFrame nuevaventana = new JFrame();
                                nuevaventana.setBounds(0, 0, 800, 500);
                                nuevaventana.setLocationRelativeTo(null); //Esto hará que el juego siempre aparezca en el centro de la pantalla
                                nuevaventana.setResizable(false);
                                nuevaventana.setTitle("Unaware");
                                nuevaventana.setDefaultCloseOperation(3);
                                nuevaventana.setEnabled(true);
                                nuevaventana.setVisible(true);
                                nuevaventana.setIconImage(new ImageIcon(Unaware.class.getResource("/Resources/logor.png")).getImage());
                                ventana.dispose();
                                actualizarPuntaje(puntaje, nuevaventana);
                            } catch (FileNotFoundException ex) {
                            }
                        }
                       
                   };
                   
                   Timer pantallamuerte = new Timer(3000, muerte);
                   pantallamuerte.setRepeats(false);
                   pantallamuerte.start();
                   
                   
                }
            };
        
        contadortiempo.addActionListener(perdistetiempo);
       
        
        
        
        Vector <String>respuestasRandom = new Vector<String>();
        respuestasRandom.add(Preguntas.elementAt(idPregunta).respuestacorrecta);
        respuestasRandom.add(Preguntas.elementAt(idPregunta).respuestab);
        respuestasRandom.add(Preguntas.elementAt(idPregunta).respuestac);
        respuestasRandom.add(Preguntas.elementAt(idPregunta).respuestad);
        
        //Aquí se randomizará la posición de las respuestas
        
        
        shuffle(respuestasRandom);
        
        //RANDOMIZA LAS RESPUESTAS DE TODOS LOS NIVLES
        
       
        
        if(idNivel==1){ //Aquí se ejecutará todo lo que pase en el nivel "Mario"
            
            
            
            
            
            
            int detfondo=0;
            
            JLayeredPane nivel = new JLayeredPane();
            nivel.setBounds(0,0,800,500);
            ventana.add(nivel);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,800,500);
        int randomBackground = (int)(Math.random()*(5-1+1)+1);
        
        nivel.add(tiempo, JLayeredPane.DRAG_LAYER);
        
        JLabel escenario = new JLabel();
        escenario.setBounds(0, 0, 800, 500);
        ImageIcon Im_escenario = new ImageIcon(Unaware.class.getResource("/Fondos/EscenarioNvl1.png"));
        escenario.setIcon(Im_escenario);
        nivel.add(escenario, JLayeredPane.PALETTE_LAYER); //La capa PALETTE es la segunda más baja.
        
        ImageIcon Im_fondo = new ImageIcon(Unaware.class.getResource("/Fondos/"+randomBackground+"Fondo.png")); //Esto hace que el fondo de este nivel sea también aleatorio.
        
        JLabel pregunta = new JLabel(Preguntas.elementAt(idPregunta).pregunta, SwingConstants.CENTER);
        pregunta.setBounds(0,0,800,80);
        pregunta.setFont(new Font("Courier", Font.BOLD, 28));
        pregunta.setForeground(Color.WHITE);
        nivel.add(pregunta);
        
        fondo.setIcon(Im_fondo);
        nivel.add(fondo, JLayeredPane.DEFAULT_LAYER);
        
        
        
        
        JLabel primerarespuesta = new JLabel(respuestasRandom.elementAt(0), SwingConstants.CENTER);
        primerarespuesta.setBounds(103,135,46,15);
        nivel.add(primerarespuesta, JLayeredPane.DRAG_LAYER);
        
        JLabel segundarespuesta = new JLabel(respuestasRandom.elementAt(1), SwingConstants.CENTER);
        segundarespuesta.setBounds(290,135,46,15);
        nivel.add(segundarespuesta, JLayeredPane.DRAG_LAYER);
        
        JLabel tercerarespuesta = new JLabel(respuestasRandom.elementAt(2), SwingConstants.CENTER);
        tercerarespuesta.setBounds(455,135,46,15);
        nivel.add(tercerarespuesta, JLayeredPane.DRAG_LAYER);
        
        JLabel cuartarespuesta = new JLabel(respuestasRandom.elementAt(3), SwingConstants.CENTER);
        cuartarespuesta.setBounds(637,135,46,15);
        nivel.add(cuartarespuesta, JLayeredPane.DRAG_LAYER);
        
        nivel.add(player.cuerpo, JLayeredPane.DRAG_LAYER);
        player.gender.setCharAt(1, 'R');
        player.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Players/"+player.gender)));
        player.cuerpo.setLocation(50, 230);
        
       Vector <Timer> borrador = new Vector<Timer>();
        
        KeyListener moverse = new KeyListener(){ //MOVIMIENTO DEL JUGADOR
                @Override
                public void keyTyped(KeyEvent e) {
                    //No usado
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    
                    movimientoBloques(ventana, this, borrador, e, player);
                    
                }

                @Override
                public void keyReleased(KeyEvent e) {
                   //No usado
                }
            
        };
        
        
        ActionListener ganaste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivel.remove(tiempo);
                   borrador.removeAllElements();
                   inBetween(ventana, nivel, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,false);
                }
            
        };
        
        ActionListener perdiste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivel.remove(tiempo);
                   borrador.removeAllElements();
                   inBetween(ventana, nivel, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,true);
                }
            
        };
        
        ventana.addKeyListener(moverse);
        
        //BOTÓN PARA SALTAR
        
        KeyListener saltar = new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e) {
                  //No se usa
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==32){
                        
                        contadortiempo.stop();
                        nivel.remove(tiempo);
                        
                        Timer saltox = new Timer(1, null);
                        int inicialy = player.cuerpo.getY();
                        
                        ActionListener saltarin = new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (player.cuerpo.getY()>170){
                                    player.cuerpo.setLocation(player.cuerpo.getX(), player.cuerpo.getY()-10);

                                }
                                if(player.cuerpo.getY()==170){
                                    saltox.stop();
                                    player.cuerpo.setLocation(player.cuerpo.getX(), inicialy);
                                    
                                    
                                }
                                
                                
                            }
                            
                        };
                        
                        saltox.start();
                        saltox.addActionListener(saltarin);
                        
                        if(primerarespuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                            if(player.cuerpo.getX()==50){
                                primerarespuesta.setText("");
                                primerarespuesta.setBounds(primerarespuesta.getX(), primerarespuesta.getY()-15,50,50);
                                primerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                Timer ganador = new Timer(3000, ganaste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                ganador.start();
                                ganador.setRepeats(false);
                                
                                
                                
                            }
                            if(player.cuerpo.getX()!=50){
                                primerarespuesta.setText("");
                                primerarespuesta.setBounds(primerarespuesta.getX(), primerarespuesta.getY()-15,50,50);
                                primerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                
                                segundarespuesta.setBounds(segundarespuesta.getX(), segundarespuesta.getY()-15,50,50);
                                tercerarespuesta.setBounds(tercerarespuesta.getX(), tercerarespuesta.getY()-15,50,50);
                                cuartarespuesta.setBounds(cuartarespuesta.getX(), cuartarespuesta.getY()-15,50,50);
                                tercerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                segundarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                cuartarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                
                                Timer perdedor = new Timer(3000, perdiste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                perdedor.start();
                                perdedor.setRepeats(false);
                            }
                        }
                        if(segundarespuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                            if(player.cuerpo.getX()==237){
                                segundarespuesta.setText("");
                                segundarespuesta.setBounds(segundarespuesta.getX(), segundarespuesta.getY()-15,50,50);
                                segundarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                Timer ganador = new Timer(3000, ganaste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                ganador.start();
                                ganador.setRepeats(false);
                            }
                            if(player.cuerpo.getX()!=237){
                                segundarespuesta.setText("");
                                segundarespuesta.setBounds(segundarespuesta.getX(), segundarespuesta.getY()-15,50,50);
                                segundarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                primerarespuesta.setBounds(primerarespuesta.getX(), primerarespuesta.getY()-15,50,50);
                                tercerarespuesta.setBounds(tercerarespuesta.getX(), tercerarespuesta.getY()-15,50,50);
                                cuartarespuesta.setBounds(cuartarespuesta.getX(), cuartarespuesta.getY()-15,50,50);
                                
                                primerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                tercerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                cuartarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                
                                Timer perdedor = new Timer(3000, perdiste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                perdedor.start();
                                perdedor.setRepeats(false);
                            }
                        }
                        if(tercerarespuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                            if(player.cuerpo.getX()==424){
                                tercerarespuesta.setText("");
                                tercerarespuesta.setBounds(tercerarespuesta.getX(), tercerarespuesta.getY()-15,50,50);
                                tercerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                Timer ganador = new Timer(3000, ganaste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                ganador.start();
                                ganador.setRepeats(false);
                            }
                           if(player.cuerpo.getX()!=424){
                                tercerarespuesta.setText("");
                                tercerarespuesta.setBounds(tercerarespuesta.getX(), tercerarespuesta.getY()-15,50,50);
                                tercerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                primerarespuesta.setBounds(primerarespuesta.getX(), primerarespuesta.getY()-15,50,50);
                                segundarespuesta.setBounds(segundarespuesta.getX(), segundarespuesta.getY()-15,50,50);
                                cuartarespuesta.setBounds(cuartarespuesta.getX(), cuartarespuesta.getY()-15,50,50);
                                
                                
                                
                                primerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                segundarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                cuartarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                
                                Timer perdedor = new Timer(3000, perdiste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                perdedor.start();
                                perdedor.setRepeats(false);
                            }
                        }
                        if(cuartarespuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                            if(player.cuerpo.getX()==611){
                                cuartarespuesta.setText("");
                                cuartarespuesta.setBounds(cuartarespuesta.getX(), cuartarespuesta.getY()-15,50,50);
                                cuartarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                Timer ganador = new Timer(3000, ganaste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                ganador.start();
                                ganador.setRepeats(false);
                                
                            }
                            if(player.cuerpo.getX()!=611){
                                cuartarespuesta.setText("");
                                cuartarespuesta.setBounds(cuartarespuesta.getX(), cuartarespuesta.getY()-15,50,50);
                                cuartarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                
                                primerarespuesta.setBounds(primerarespuesta.getX(), primerarespuesta.getY()-15,50,50);
                                segundarespuesta.setBounds(segundarespuesta.getX(), segundarespuesta.getY()-15,50,50);
                                tercerarespuesta.setBounds(tercerarespuesta.getX(), tercerarespuesta.getY()-15,50,50);
                                
                                primerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                segundarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                tercerarespuesta.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                
                                Timer perdedor = new Timer(3000, perdiste);
                                
                                ventana.removeKeyListener(this);
                                ventana.removeKeyListener(moverse);
                                
                                perdedor.start();
                                perdedor.setRepeats(false);
                            }
                        }
                        
                    }
                    
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    
                }
            
        };
        
        ventana.addKeyListener(saltar);
        
        }
        
        
        //AQUÍ ACABA TODO EL NIVEL MARIO
        
        if(idNivel==2){//Aquí empieza todo el nivel de botones.
            
            JLayeredPane nivelBotones = new JLayeredPane();
            nivelBotones.setBounds(0, 0, 800, 500);
            ventana.add(nivelBotones);
            
            int aleatorio=0;
            
            nivelBotones.add(tiempo, JLayeredPane.DRAG_LAYER);
            
            Vector <Timer> borrador = new Vector <Timer>();
            
            StringBuilder filebutton = new StringBuilder();
            filebutton.append("01Boton.png");
            
            JLabel fondo = new JLabel();
            fondo.setBounds(0,0,800,500);
            fondo.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/1Fondo.png")));
            nivelBotones.add(fondo, JLayeredPane.DEFAULT_LAYER);
            
            JLabel boton = new JLabel();
            boton.setBounds(50,70,300,300);
            boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
            nivelBotones.add(boton, JLayeredPane.POPUP_LAYER);
            
            JLabel respuesta = new JLabel(respuestasRandom.elementAt(aleatorio), SwingConstants.CENTER);
            respuesta.setBounds(125, 175, 150, 40);
            respuesta.setFont(new Font("Arial", Font.PLAIN, 38));
            respuesta.setForeground(Color.WHITE);
            nivelBotones.add(respuesta, JLayeredPane.DRAG_LAYER);
            
            JLabel pregunta = new JLabel(Preguntas.elementAt(idPregunta).pregunta, SwingConstants.CENTER);
            pregunta.setBounds(0,0,800,80);
            pregunta.setFont(new Font("Courier", Font.BOLD, 28));
            pregunta.setForeground(Color.WHITE);
            nivelBotones.add(pregunta, JLayeredPane.POPUP_LAYER);
            
            Timer cambioBotones = new Timer(2000, null);
            borrador.add(cambioBotones);
            
            Timer terminarnivel = new Timer(2000, null);
            borrador.add(terminarnivel);
            
            ActionListener ganaste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelBotones.remove(tiempo);
                   borrador.removeAllElements();
                   inBetween(ventana, nivelBotones, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,false);
                }
            };
            
            ActionListener perdiste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelBotones.remove(tiempo);
                   borrador.removeAllElements();
                   inBetween(ventana, nivelBotones, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,true);
                }
            };
            
            ActionListener cambiar = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(respuesta.getText()==respuestasRandom.firstElement()){
                        respuesta.setText(respuestasRandom.elementAt(1));
                        filebutton.setCharAt(0, '1');
                        boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
                    }
                    else if(respuesta.getText()==respuestasRandom.elementAt(1)){
                        respuesta.setText(respuestasRandom.elementAt(2));
                        filebutton.setCharAt(0, '2');
                        boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
                    }
                    else if(respuesta.getText()==respuestasRandom.elementAt(2)){
                        respuesta.setText(respuestasRandom.elementAt(3));
                        filebutton.setCharAt(0, '3');
                        boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
                    }
                    else if(respuesta.getText()==respuestasRandom.elementAt(3)){
                        respuesta.setText(respuestasRandom.elementAt(0));
                        filebutton.setCharAt(0, '0');
                        boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
                    }
                    
                   
                }
                
            };
            
            KeyListener presionar2 = new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e) {
                    //No usada
                }

                @Override
                public void keyPressed(KeyEvent e) {
                   if(e.getKeyCode()==10||e.getKeyCode()==32){
                       contadortiempo.stop();
                    nivelBotones.remove(tiempo);
                       filebutton.setCharAt(1, '2');
                    boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
                    respuesta.setBounds(125, 185, 150, 40);
                    
                    cambioBotones.stop();
                    
                    terminarnivel.start();
                    terminarnivel.setRepeats(false);
                    
                    if(respuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                        fondo.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/2Fondo.png")));
                        terminarnivel.addActionListener(ganaste);
                        terminarnivel.start();
                        terminarnivel.setRepeats(false);
                        ventana.removeKeyListener(this);
                    }
                    else{
                        JLabel explosion = new JLabel();
                        explosion.setBounds(0,0,800,500);
                        explosion.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/explosion.gif")));
                        
                        respuesta.setVisible(false);
                        nivelBotones.add(explosion, JLayeredPane.DRAG_LAYER);
                        
                        terminarnivel.addActionListener(perdiste);
                        terminarnivel.start();
                        terminarnivel.setRepeats(false);
                        ventana.removeKeyListener(this);
                    }
                   }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                   //No usada
                }
                
            };
            
            MouseListener presionar1 = new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    contadortiempo.stop();
                    nivelBotones.remove(tiempo);
                    filebutton.setCharAt(1, '2');
                    boton.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/"+filebutton)));
                    respuesta.setBounds(125, 185, 150, 40);
                    
                    cambioBotones.stop();
                    
                    terminarnivel.start();
                    terminarnivel.setRepeats(false);
                    ventana.removeKeyListener(presionar2);
                    
                    if(respuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                        fondo.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/2Fondo.png")));
                        terminarnivel.addActionListener(ganaste);
                        terminarnivel.start();
                        terminarnivel.setRepeats(false);
                    }
                    else{
                        JLabel explosion = new JLabel();
                        explosion.setBounds(0,0,800,500);
                        explosion.setIcon(new ImageIcon(Unaware.class.getResource("/NivelBotones/explosion.gif")));
                        
                        respuesta.setVisible(false);
                        nivelBotones.add(explosion, JLayeredPane.DRAG_LAYER);
                        
                        terminarnivel.addActionListener(perdiste);
                        terminarnivel.start();
                        terminarnivel.setRepeats(false);
                        ventana.removeKeyListener(presionar2);
                    }
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //No usada
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //No usada
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //No usada
                }
                
            };
            
            
            
            cambioBotones.addActionListener(cambiar);
            cambioBotones.start();
            boton.addMouseListener(presionar1);
            ventana.addKeyListener(presionar2);
            
            
            
            

            
        }
        
        //AQUÍ TERMINA TODO LO DEL NIVEL BOTONES
        
        if(idNivel==3){//Aquí empieza todo lo del Nivel "Cartas";
            
            JLayeredPane nivelCartas = new JLayeredPane();
            nivelCartas.setBounds(0,0,800,500);
            ventana.add(nivelCartas);
            
            nivelCartas.add(tiempo, JLayeredPane.DRAG_LAYER);
            
            Vector <Timer>borrador = new Vector<Timer>();
            
            JLabel fondo = new JLabel();
            fondo.setBounds(0,0,800,500);
            fondo.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/00_FondoCartas.png")));
            nivelCartas.add(fondo, JLayeredPane.DEFAULT_LAYER);
            
            JLabel selector = new JLabel();
            selector.setBounds(22,116,183,267);
            selector.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Selector.png")));
            nivelCartas.add(selector, JLayeredPane.DRAG_LAYER);
            selector.setVisible(false);
            
            
            JLabel pregunta = new JLabel("¡MEMORÍZALAS!", SwingConstants.CENTER);
            pregunta.setBounds(0,0,800,80);
            pregunta.setFont(new Font("Courier", Font.BOLD, 28));
            pregunta.setForeground(Color.WHITE);
            nivelCartas.add(pregunta, JLayeredPane.POPUP_LAYER);
            
            JLabel respuesta1 = new JLabel(respuestasRandom.elementAt(0), SwingConstants.CENTER);
            respuesta1.setBounds(55, 200, 120, 40);
            respuesta1.setFont(new Font("Courier", Font.BOLD, 20));
            nivelCartas.add(respuesta1, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta2 = new JLabel(respuestasRandom.elementAt(1), SwingConstants.CENTER);
            respuesta2.setBounds(255, 200, 120, 40);
            respuesta2.setFont(new Font("Courier", Font.BOLD, 20));
            nivelCartas.add(respuesta2, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta3 = new JLabel(respuestasRandom.elementAt(2), SwingConstants.CENTER);
            respuesta3.setBounds(445, 200, 120, 40);
            respuesta3.setFont(new Font("Courier", Font.BOLD, 20));
            nivelCartas.add(respuesta3, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta4 = new JLabel(respuestasRandom.elementAt(3), SwingConstants.CENTER);
            respuesta4.setBounds(635, 200, 120, 40);
            respuesta4.setFont(new Font("Courier", Font.BOLD, 20));
            nivelCartas.add(respuesta4, JLayeredPane.DRAG_LAYER);
            
            Timer revision = new Timer(2000, null);
            revision.start();
            revision.setRepeats(false);
            borrador.add(revision);
            
            Timer terminarnivel = new Timer(2000, null);
            terminarnivel.setRepeats(false);
            borrador.add(terminarnivel);
            
            ActionListener ganaste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                   borrador.removeAllElements();
                   inBetween(ventana, nivelCartas, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,false);
                }
            };
            
            ActionListener perdiste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                   borrador.removeAllElements();
                   inBetween(ventana, nivelCartas, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,true);
                }
            };
            
            //Chulito o X
            
            JLabel iconofinal = new JLabel();
            iconofinal.setBounds(350,150,50,50);
            nivelCartas.add(iconofinal,JLayeredPane.DRAG_LAYER);
            iconofinal.setVisible(false);
            
            
            
            KeyListener mover = new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e) {
                    //No se usa
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==39){
                        if(selector.getX()!=592){
                            selector.setLocation(selector.getX()+190, selector.getY());
                        }
                    }
                    if(e.getKeyCode()==37){
                        if(selector.getX()!=22){
                            selector.setLocation(selector.getX()-190, selector.getY());
                        }
                    }
                    
                    if(e.getKeyCode()==32||e.getKeyCode()==10){
                        
                        ventana.removeKeyListener(this);
                        
                        if(selector.getX()==22){
                            if(respuesta1.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(ganaste);
                                terminarnivel.start();
                            }
                            else{
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(perdiste);
                                terminarnivel.start();
                            }
                        }
                        else if(selector.getX()==212){
                        if(respuesta2.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(ganaste);
                                terminarnivel.start();
                            }
                            else{
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(perdiste);
                                terminarnivel.start();
                            }
                        }
                        else if(selector.getX()==402){
                            if(respuesta3.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(ganaste);
                                terminarnivel.start();
                            }
                            else{
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(perdiste);
                                terminarnivel.start();
                            }
                            
                        }
                        else{
                            if(respuesta4.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Chulito.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(ganaste);
                                terminarnivel.start();
                            }
                            else{
                                selector.setVisible(false);
                                iconofinal.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/X.png")));
                                iconofinal.setVisible(true);
                                contadortiempo.stop();
                    nivelCartas.remove(tiempo);
                                terminarnivel.addActionListener(perdiste);
                                terminarnivel.start();
                            }
                            
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //No se usa
                }
                
            };
            
            ActionListener cambiar = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    respuesta1.setVisible(false);
                    respuesta2.setVisible(false);
                    respuesta3.setVisible(false);
                    respuesta4.setVisible(false);
                    pregunta.setText(Preguntas.elementAt(idPregunta).pregunta);
                    fondo.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/01_FondoCartas.png")));
                    selector.setVisible(true);
                    ventana.addKeyListener(mover);
                }
                
            };
            
            revision.addActionListener(cambiar);
            
        }
        
        if (idNivel==4){ // Aquí empieza todo lo del nivel DuckHunt
            
            Timer pajarovolando = new Timer(5, null); //RECORDAR BORRAR
            pajarovolando.start();
            
            Timer terminarnivel = new Timer(2000, null);
            terminarnivel.setRepeats(false);
            
            JLayeredPane nivelHunt = new JLayeredPane();
            nivelHunt.setBounds(0, 0, 800, 500);
            ventana.add(nivelHunt);
            
            nivelHunt.add(tiempo, JLayeredPane.DRAG_LAYER);
            
            ActionListener ganaste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelHunt.remove(tiempo);
                   inBetween(ventana, nivelHunt, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,false);
                }
            };
            
            ActionListener perdiste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelHunt.remove(tiempo);
                   inBetween(ventana, nivelHunt, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,true);
                }
            };
            
            JLabel fondo = new JLabel();
            fondo.setBounds(0, 0, 800, 500);
            fondo.setIcon(new ImageIcon(Unaware.class.getResource("/Fondos/1Fondo.png")));
            nivelHunt.add(fondo, JLayeredPane.DEFAULT_LAYER);
            
            JLabel mira = new JLabel();
            mira.setBounds(0, 0, 800, 500);
            mira.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/01Mira.png")));
            nivelHunt.add(mira, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta = new JLabel(respuestasRandom.elementAt(0));
            respuesta.setBounds(10, 120, 100, 40);
            respuesta.setFont(new Font("Courier", Font.BOLD, 24));
            nivelHunt.add(respuesta, JLayeredPane.POPUP_LAYER);
            
            JLabel pajaro = new JLabel();
            pajaro.setBounds(-50, 100, 175, 225);
            pajaro.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Pajaro_1.gif")));
            nivelHunt.add(pajaro, JLayeredPane.MODAL_LAYER);
            
            JLabel pregunta = new JLabel(Preguntas.elementAt(idPregunta).pregunta, SwingConstants.CENTER);
            pregunta.setBounds(0,0,800,80);
            pregunta.setFont(new Font("Courier", Font.BOLD, 28));
            pregunta.setForeground(Color.WHITE);
            nivelHunt.add(pregunta, JLayeredPane.POPUP_LAYER);
            
            
            KeyListener disparar = new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e) {
                    //No se usa
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    
                    ventana.removeKeyListener(this);
                    
                    contadortiempo.stop();
                    nivelHunt.remove(tiempo);
                    
                    if (e.getKeyCode()==10||e.getKeyCode()==32){
                        mira.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/02Mira.png")));
                        pajarovolando.stop();
                        if(pajaro.getX()<=153||pajaro.getX()>=646){
                            mira.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/fallaste.png")));
                            terminarnivel.addActionListener(perdiste);
                            terminarnivel.start();
                        }
                        else{
                            if(respuesta.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                mira.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/ganaste.png")));
                                terminarnivel.addActionListener(ganaste);
                                terminarnivel.start();
                            }
                            else{
                                mira.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/perdiste.png")));
                                terminarnivel.addActionListener(perdiste);
                                terminarnivel.start();
                            }
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //No se usa
                }
                
            };
            
            ventana.addKeyListener(disparar);
            
            ActionListener movimiento = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pajaro.getX()<=900){
                        pajaro.setLocation(pajaro.getX()+10, pajaro.getY());
                        respuesta.setLocation(respuesta.getX()+10, respuesta.getY());
                    }
                    else{ 
                        pajaro.setLocation(-50, pajaro.getY());
                        respuesta.setLocation(10, respuesta.getY());
                        if(respuesta.getText()==respuestasRandom.elementAt(0)){
                            respuesta.setText(respuestasRandom.elementAt(1));
                        }
                        else if(respuesta.getText()==respuestasRandom.elementAt(1)){
                            respuesta.setText(respuestasRandom.elementAt(2));
                        }
                        else if(respuesta.getText()==respuestasRandom.elementAt(2)){
                            respuesta.setText(respuestasRandom.elementAt(3));
                        }
                        else if(respuesta.getText()==respuestasRandom.elementAt(3)){
                            respuesta.setText(respuestasRandom.elementAt(0));
                        }
                        
                    }
                    

                }
                
            };
            
            pajarovolando.addActionListener(movimiento);
            
            
        }
        
        if(idNivel==5){ //Nivel Cables
            
            JLayeredPane nivelCables = new JLayeredPane();
            nivelCables.setBounds(0, 0, 800, 500);
            ventana.add(nivelCables);
            
            JLabel fondo = new JLabel();
            fondo.setBounds(0,0,800,500);
            fondo.setIcon(new ImageIcon(Unaware.class.getResource("/Fondos/FondoCuerdas.png")));
            nivelCables.add(fondo, JLayeredPane.DEFAULT_LAYER);
            
            nivelCables.add(tiempo, JLayeredPane.DRAG_LAYER);
            
            JLabel Barril1 = new JLabel();
            Barril1.setBounds(50,-30,120,450);
            Barril1.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barril.png")));
            nivelCables.add(Barril1, JLayeredPane.POPUP_LAYER);
            
            JLabel Barril2 = new JLabel();
            Barril2.setBounds(220,-30,120,450);
            Barril2.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barril.png")));
            nivelCables.add(Barril2, JLayeredPane.POPUP_LAYER);
            
            JLabel Barril3 = new JLabel();
            Barril3.setBounds(390,-30,120,450);
            Barril3.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barril.png")));
            nivelCables.add(Barril3, JLayeredPane.POPUP_LAYER);
            
            JLabel Barril4 = new JLabel();
            Barril4.setBounds(560,-30,120,450);
            Barril4.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barril.png")));
            nivelCables.add(Barril4, JLayeredPane.POPUP_LAYER);
            
            JLabel pregunta = new JLabel(Preguntas.elementAt(idPregunta).pregunta, SwingConstants.CENTER);
            pregunta.setBounds(0,0,800,80);
            pregunta.setFont(new Font("Courier", Font.BOLD, 28));
            pregunta.setForeground(Color.WHITE);
            nivelCables.add(pregunta, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta1 = new JLabel(respuestasRandom.elementAt(0), SwingConstants.CENTER);
            respuesta1.setBounds(55, 320, 100, 40);
            respuesta1.setFont(new Font("Courier", Font.BOLD, 24));
            respuesta1.setForeground(Color.YELLOW);
            nivelCables.add(respuesta1, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta2 = new JLabel(respuestasRandom.elementAt(1), SwingConstants.CENTER);
            respuesta2.setBounds(225, 320, 100, 40);
            respuesta2.setForeground(Color.YELLOW);
            respuesta2.setFont(new Font("Courier", Font.BOLD, 24));
            nivelCables.add(respuesta2, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta3 = new JLabel(respuestasRandom.elementAt(2), SwingConstants.CENTER);
            respuesta3.setBounds(400, 320, 100, 40);
            respuesta3.setForeground(Color.YELLOW);
            respuesta3.setFont(new Font("Courier", Font.BOLD, 24));
            nivelCables.add(respuesta3, JLayeredPane.DRAG_LAYER);
            
            JLabel respuesta4 = new JLabel(respuestasRandom.elementAt(3), SwingConstants.CENTER);
            respuesta4.setBounds(575, 320, 100, 40);
            respuesta4.setForeground(Color.YELLOW);
            respuesta4.setFont(new Font("Courier", Font.BOLD, 24));
            nivelCables.add(respuesta4, JLayeredPane.DRAG_LAYER);
            
            JLabel selector = new JLabel();
            selector.setBounds(50,-30,120,450);
            selector.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/selectorbarril.png")));
            nivelCables.add(selector, JLayeredPane.DRAG_LAYER);
            
            Timer terminarnivel = new Timer(1110, null);
            
            ActionListener ganaste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelCables.remove(tiempo);
                   inBetween(ventana, nivelCables, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,false);
                }
            };
            
            ActionListener perdiste = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    contadortiempo.stop();
                    nivelCables.remove(tiempo);
                   inBetween(ventana, nivelCables, player, puntaje+1, idNivel, idPregunta, Preguntas, Niveles,true);
                }
            };
            
            KeyListener mover = new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e) {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println(selector.getX());
                    if(e.getKeyCode()==37||e.getKeyCode()==39){
                        if(e.getKeyCode()==37&&selector.getX()!=50){
                            selector.setLocation(selector.getX()-170, selector.getY());
                        }
                        if(e.getKeyCode()==39&&selector.getX()!=560){
                            selector.setLocation(selector.getX()+170, selector.getY());
                        }
                    }
                    if(e.getKeyCode()==10||e.getKeyCode()==32){
                        contadortiempo.stop();
                        ventana.removeKeyListener(this);
                        selector.setVisible(false);
                        terminarnivel.setRepeats(false);
                        terminarnivel.start();
                        if(selector.getX()==50){
                            
                            
                        respuesta1.setVisible(false);

                            Barril1.setBounds(Barril1.getX(), Barril1.getY()-30, 120, 550);
                            if(respuesta1.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                
                                terminarnivel.addActionListener(ganaste);
                                Barril1.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilgood.gif")));
                                
                            }
                            else{
                                terminarnivel.addActionListener(perdiste);
                                Barril1.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilwrong.gif")));
                                
                            }
                        }
                        if(selector.getX()==220){
                        respuesta2.setVisible(false);
                            Barril2.setBounds(Barril2.getX(), Barril2.getY()-30, 120, 550);
                            if(respuesta2.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                
                                terminarnivel.addActionListener(ganaste);
                                Barril2.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilgood.gif")));
                                
                            }
                            else{
                                terminarnivel.addActionListener(perdiste);
                                Barril2.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilwrong.gif")));
                                
                            }
                        }
                        if(selector.getX()==390){
                        respuesta3.setVisible(false);
                            Barril3.setBounds(Barril3.getX(), Barril3.getY()-30, 120, 550);
                            if(respuesta3.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                
                                terminarnivel.addActionListener(ganaste);
                                Barril3.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilgood.gif")));
                                
                            }
                            else{
                                terminarnivel.addActionListener(perdiste);
                                Barril3.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilwrong.gif")));
                                
                            }
                            
                        }
                        if(selector.getX()==560){
                            respuesta4.setVisible(false);
                            Barril4.setBounds(Barril4.getX(), Barril4.getY()-30, 120, 550);
                            if(respuesta4.getText()==Preguntas.elementAt(idPregunta).respuestacorrecta){
                                
                                terminarnivel.addActionListener(ganaste);
                                Barril4.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilgood.gif")));
                                
                            }
                            else{
                                
                                terminarnivel.addActionListener(perdiste);
                                Barril4.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Barrilwrong.gif")));
                                
                            }
                        }
                        
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    
                }
                
            };
            
            ventana.addKeyListener(mover);
            
        }
        
        

        

    }
    
    private static void movimientoBloques(JFrame ventana, KeyListener teclas, Vector borrador, KeyEvent e, Jugador player){
        Timer moversez = new Timer(1, null);
                    Timer moversed = new Timer(1, null);
                    
                    moversez.stop();
                    moversed.stop();
                    
                    int xinicial = player.cuerpo.getX();
                    
                    if(e.getKeyCode()==39||e.getKeyCode()==37){ //Moverse a la derecha o a la izquierda
                        
                        
                        
                        if(e.getKeyCode()==39){
                            
                            moversez.stop();
                            moversed.stop();
                            
                            
                            player.gender.setCharAt(1, 'R');
                            player.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Players/"+player.gender)));
                            
                            ActionListener sumarMovimiento = new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                           int  newX = player.cuerpo.getX()+4;

                           if (player.cuerpo.getX()<611){
                               if(player.cuerpo.getX()<xinicial+187){
                                    player.cuerpo.setLocation(newX, 230);
                                    ventana.removeKeyListener(teclas);
                                 }
                               else{
                                    moversed.stop();
                                    player.cuerpo.setLocation(xinicial+187, 230);
                                    ventana.addKeyListener(teclas);
                                    borrador.add(moversed);
                                    borrador.add(moversez);
                                }
                             }
                           else{
                               player.cuerpo.setLocation(611, 230);
                               moversed.stop();
                               ventana.addKeyListener(teclas);
                               borrador.add(moversed);
                               borrador.add(moversez);
                           }
                           
                            
                           
                        }
                        
                        
                    };
                           moversed.addActionListener(sumarMovimiento);
                           moversed.start();
                           moversez.stop();
                           borrador.add(moversed);
                           borrador.add(moversez);
                          }
                        
                        if(e.getKeyCode()==37){
                            
                            
                            moversed.stop();
                            moversez.stop();
                            
                            player.gender.setCharAt(1, 'L');
                            player.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("src/Players/"+player.gender)));
                            
                            ActionListener restarMovimiento = new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                           int  newX = player.cuerpo.getX()-4;
                           
                           
                           if (player.cuerpo.getX()>50){
                               if(player.cuerpo.getX()>=xinicial-187){
                                    player.cuerpo.setLocation(newX, 230);
                                    ventana.removeKeyListener(teclas);
                                 }
                               else{
                                   moversez.stop();
                                   player.cuerpo.setLocation(xinicial-187, 230);
                                   ventana.addKeyListener(teclas);
                                   borrador.add(moversed);
                                   borrador.add(moversez);

                                    
                                }
                             }
                           else{
                               player.cuerpo.setLocation(50, 230);
                               moversez.stop();
                               ventana.addKeyListener(teclas);
                               borrador.add(moversed);
                               borrador.add(moversez);
                           }
                           
                            
                           
                        }
                        
                        
                    };
                           moversez.addActionListener(restarMovimiento);
                           moversez.start();
                           borrador.add(moversed);
                           borrador.add(moversez);
                           
                          }
                   
                    }
                   
                }
    
    private static File scores(){
        File scoreboards = new File(System.getProperty("user.home")+"/AppData/Roaming/unaware.txt");
        if (scoreboards.exists()!=true){
            try {
                //Voy a crear un archivo con 10 lineas. Las lineas impares tendrán el puntaje y las pares tendrán el nombre.
                //Recordar que si el puntaje es 0 y el nombre es "EMPTY", entonces no se debe mostrar en la tabla.
                scoreboards.createNewFile();
                
                FileWriter escritor = new FileWriter(System.getProperty("user.home")+"/AppData/Roaming/unaware.txt");
                escritor.write("000"+"\n"+"NONE"+"\n"+"000"+"\n"+"NONE"+"\n"+"000"+"\n"+"NONE"+"\n"+"000"+"\n"+"NONE"+"\n"+"000"+"\n"+"NONE"+"\n"+"000"+"\n"+"EMPTe"+"\n");
                escritor.close();
                
            } catch (IOException ex) {
               
            }
         
        }
        else{
            
        }
        
        return(scoreboards);
        
    }
    
    private static void actualizarPuntaje(int puntaje, JFrame ventana) throws FileNotFoundException{
        StringBuilder copiaArchivo = new StringBuilder("");
        File archivoPuntajes = new File(System.getProperty("user.home")+"/AppData/Roaming/unaware.txt");
        Scanner lector = new Scanner(archivoPuntajes);
        
        while(lector.hasNextLine()){
            copiaArchivo = copiaArchivo.append(lector.nextLine()+"\n");
        }
        
        
        
        String puntaje1S = Character.toString(copiaArchivo.charAt(0))+Character.toString(copiaArchivo.charAt(1))+Character.toString(copiaArchivo.charAt(2));
        String puntaje2S = Character.toString(copiaArchivo.charAt(9))+Character.toString(copiaArchivo.charAt(10))+Character.toString(copiaArchivo.charAt(11));
        String puntaje3S = Character.toString(copiaArchivo.charAt(18))+Character.toString(copiaArchivo.charAt(19))+Character.toString(copiaArchivo.charAt(20));
        String puntaje4S = Character.toString(copiaArchivo.charAt(27))+Character.toString(copiaArchivo.charAt(28))+Character.toString(copiaArchivo.charAt(29));
        String puntaje5S = Character.toString(copiaArchivo.charAt(36))+Character.toString(copiaArchivo.charAt(37))+Character.toString(copiaArchivo.charAt(38));
        
        //System.out.println(Character.toString(copiaArchivo.charAt(0))+Character.toString(copiaArchivo.charAt(1))+Character.toString(copiaArchivo.charAt(2)));
        //System.out.println(Character.toString(copiaArchivo.charAt(9))+Character.toString(copiaArchivo.charAt(10))+Character.toString(copiaArchivo.charAt(11)));
        //System.out.println(Character.toString(copiaArchivo.charAt(18))+Character.toString(copiaArchivo.charAt(19))+Character.toString(copiaArchivo.charAt(20)));
        
        int puntaje1 = Integer.parseInt(puntaje1S);
        int puntaje2 = Integer.parseInt(puntaje2S);
        int puntaje3 = Integer.parseInt(puntaje3S);
        int puntaje4 = Integer.parseInt(puntaje4S);
        int puntaje5 = Integer.parseInt(puntaje5S);
        
        String nuevopuntaje = "999";
        if (puntaje<10){
            nuevopuntaje = "00"+ String.valueOf(puntaje);
        }
        else if(puntaje>=10&&puntaje<100){
            nuevopuntaje = "0"+ String.valueOf(puntaje);
        }
        else{
            nuevopuntaje = String.valueOf(puntaje);
        }
        
        //System.out.println(nuevopuntaje);
        
        
        
        if(puntaje>=puntaje5){
            
            JLayeredPane ventanarecord = new JLayeredPane();
            ventanarecord.setBounds(0,0,800,500);
            ventana.add(ventanarecord);
            
            JLabel fondo = new JLabel();
            fondo.setBounds(0,0,800,500);
            fondo.setIcon(new ImageIcon(Unaware.class.getResource("/Resources/Nuevorecord.png")));
            ventanarecord.add(fondo, JLayeredPane.DEFAULT_LAYER);
            
            Boton retorno = new Boton();
            retorno.cuerpo.setBounds(360,360,80,80);
            retorno.file.append("00_Btn_Retorno.png");
            retorno.cuerpo.setIcon(new ImageIcon(Unaware.class.getResource("/Botones/00_Btn_Retorno.png")));
            ventanarecord.add(retorno.cuerpo, JLayeredPane.DRAG_LAYER);
            
            JTextField nombre = new JTextField("NONE");
            nombre.setBounds(373,298,100,50);
            nombre.setFont(new Font("Futura", Font.BOLD, 20));
            nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            nombre.setForeground(Color.WHITE);
            nombre.setBackground(new Color(118, 115, 116));
            ventanarecord.add(nombre, JLayeredPane.DRAG_LAYER);
            
            MouseListener mouse = new MouseListener(){
            
            
            @Override
            public void mouseClicked(MouseEvent e) {
                       
                       ventanarecord.setVisible(false);
                       borrarContenido(ventana, ventanarecord);
                try {
                    actualizarNombres(nombre, puntaje, puntaje1, puntaje2, puntaje3, puntaje4, puntaje5);
                } catch (FileNotFoundException ex) {
                    
                }
                       ejecutarMenu(ventana);
                       
                       
                   
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
                    retorno.file.setCharAt(1, '1');
                
                    ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+retorno.file));
                    retorno.cuerpo.setIcon(nuevo);
                
                
            }

            @Override
            
            
            
            public void mouseExited(MouseEvent e) {
                
                
               retorno.file.setCharAt(1, '0');
                
                ImageIcon nuevo = new ImageIcon(Unaware.class.getResource("/Botones/"+retorno.file));
                retorno.cuerpo.setIcon(nuevo);
                

            }
            
         
            
        };
            
            
            
            
            
            
            if(puntaje>=puntaje1){
                copiaArchivo.replace(36, 39, puntaje4S);
                copiaArchivo.replace(27, 30, puntaje3S);
                copiaArchivo.replace(18, 21, puntaje2S);
                copiaArchivo.replace(9, 12, puntaje1S);
                copiaArchivo.replace(0, 3, nuevopuntaje);
            }
            else if(puntaje>=puntaje2&&puntaje<=puntaje1){
                copiaArchivo.replace(36, 39, puntaje4S);
                copiaArchivo.replace(27, 30, puntaje3S);
                copiaArchivo.replace(18, 21, puntaje2S);
                copiaArchivo.replace(9,12, nuevopuntaje);
            }
            else if(puntaje>=puntaje3&&puntaje<=puntaje2){
                copiaArchivo.replace(36, 39, puntaje4S);
                copiaArchivo.replace(27, 30, puntaje3S);
                copiaArchivo.replace(18, 21, nuevopuntaje);
            }
            else if(puntaje>=puntaje4&&puntaje<=puntaje3){
                copiaArchivo.replace(36, 39, puntaje4S);
                copiaArchivo.replace(27, 30, nuevopuntaje);
            }
            else if(puntaje>=puntaje5&&puntaje<=puntaje4){
                copiaArchivo.replace(36,39, nuevopuntaje);
            }

            
            archivoPuntajes.delete();
            
            try {
                FileWriter escritor = new FileWriter(archivoPuntajes);
                escritor.write(copiaArchivo.toString());
                escritor.close();
            } catch (IOException ex) {
                
            }
            
            retorno.cuerpo.addMouseListener(mouse);
            
            
        }
        else{
            ejecutarMenu(ventana);
        }
        
        
    }
    
    private static void actualizarNombres(JTextField nombre, int puntaje, int puntaje1, int puntaje2, int puntaje3, int puntaje4, int puntaje5) throws FileNotFoundException{
        StringBuilder copiaArchivo = new StringBuilder("");
        File archivoPuntajes = new File(System.getProperty("user.home")+"/AppData/Roaming/unaware.txt");
        Scanner lector = new Scanner(archivoPuntajes);
        
        while(lector.hasNextLine()){
            copiaArchivo = copiaArchivo.append(lector.nextLine()+"\n");
        }
        
        String nombre1S = Character.toString(copiaArchivo.charAt(4))+Character.toString(copiaArchivo.charAt(5))+Character.toString(copiaArchivo.charAt(6))+Character.toString(copiaArchivo.charAt(7));
        String nombre2S = Character.toString(copiaArchivo.charAt(13))+Character.toString(copiaArchivo.charAt(14))+Character.toString(copiaArchivo.charAt(15))+Character.toString(copiaArchivo.charAt(16));
        String nombre3S = Character.toString(copiaArchivo.charAt(22))+Character.toString(copiaArchivo.charAt(23))+Character.toString(copiaArchivo.charAt(24))+Character.toString(copiaArchivo.charAt(25));
        String nombre4S = Character.toString(copiaArchivo.charAt(31))+Character.toString(copiaArchivo.charAt(32))+Character.toString(copiaArchivo.charAt(33))+Character.toString(copiaArchivo.charAt(34));
        String nombre5S = Character.toString(copiaArchivo.charAt(40))+Character.toString(copiaArchivo.charAt(41))+Character.toString(copiaArchivo.charAt(42))+Character.toString(copiaArchivo.charAt(43));
        
        String nombrereal = "";
        
        if(nombre.getText().length()>4){
            nombrereal = Character.toString(nombre.getText().charAt(0))+Character.toString(nombre.getText().charAt(1))+Character.toString(nombre.getText().charAt(2))+Character.toString(nombre.getText().charAt(3));
            nombrereal = nombrereal.toUpperCase();
        }
        else if(nombre.getText().length()==3){
            nombrereal = nombre.getText()+"-";
            nombrereal = nombrereal.toUpperCase();
        }
        else if(nombre.getText().length()==2){
            nombrereal = nombre.getText()+"--";
            nombrereal = nombrereal.toUpperCase();
        }
        else if(nombre.getText().length()==1){
            nombrereal = nombre.getText()+"---";
            nombrereal = nombrereal.toUpperCase();
        }
        else if(nombre.getText().length()==0){
            nombrereal = "----";
        }
        else{
            nombrereal=nombre.getText();
            nombrereal = nombrereal.toUpperCase();
        }
        
        if(puntaje>=puntaje1){
                copiaArchivo.replace(40, 44, nombre4S);
                copiaArchivo.replace(31, 35, nombre3S);
                copiaArchivo.replace(22, 26, nombre2S);
                copiaArchivo.replace(13, 17, nombre1S);
                copiaArchivo.replace(4, 8, nombrereal);
            }
            else if(puntaje>=puntaje2&&puntaje<=puntaje1){
                copiaArchivo.replace(40, 44, nombre4S);
                copiaArchivo.replace(31, 35, nombre3S);
                copiaArchivo.replace(22, 26, nombre2S);
                copiaArchivo.replace(13, 17, nombrereal);
            }
            else if(puntaje>=puntaje3&&puntaje<=puntaje2){
                copiaArchivo.replace(40, 44, nombre4S);
                copiaArchivo.replace(31, 35, nombre3S);
                copiaArchivo.replace(22, 26, nombrereal);
            }
            else if(puntaje>=puntaje4&&puntaje<=puntaje3){
                copiaArchivo.replace(40, 44, nombre4S);
                copiaArchivo.replace(31, 35, nombrereal);
            }
            else if(puntaje>=puntaje5&&puntaje<=puntaje4){
                copiaArchivo.replace(40, 44, nombrereal);
            }

            
            archivoPuntajes.delete();
            
            try {
                FileWriter escritor = new FileWriter(archivoPuntajes);
                escritor.write(copiaArchivo.toString());
                escritor.close();
            } catch (IOException ex) {
                
            }
        
        
        
        
        
    }
    
    


}
  

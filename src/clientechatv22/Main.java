package clientechatv22;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Borjaro2000
 */
public class Main {

    public static void main(String[] args) {
        //Seleccionamos el tema de visualización del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        //Variables
        Socket conexionConServidor = null; //A través de este sockect nos comunicaremos con el servidor.
        DataInputStream mensajesServidor = null; //Aquí escribimos los mensajes al sevirdor.
        DefaultTableModel panelLectura = null; //Aquí escribimos los mensajes que se mostrarán en pantalla.
        String[] mensajesTemp = {"", ""}; //Lo utilizaremos para escribir mensajes y errores por pantalla.
        String servidor = "localhost"; //Ip o dirección del servidor
        int puerto = 65000; //Puerto de escucha

        //Iniciamos el panel de lectura
        panelLectura = new DefaultTableModel();
        panelLectura.addColumn("Usuario");
        panelLectura.addColumn("Mensaje");

        //Leemos los datos de configuración de un archivo
        try {
            FileReader fr = new FileReader("configuracion.dat");
            BufferedReader entrada = new BufferedReader(fr);
            servidor = entrada.readLine(); //Lee la primera linea del archivo
            puerto = Integer.parseInt(entrada.readLine()); //Lee la siguiente línea del archivo
        } catch (FileNotFoundException ex) {
            mensajesTemp[0] = "Sistema";
            mensajesTemp[1] = "Error al leer el archivo de configuración: " + ex.getMessage();
            panelLectura.addRow(mensajesTemp);
        } catch (IOException ex) {
            mensajesTemp[0] = "Sistema";
            mensajesTemp[1] = "Error al leer el archivo de configuración: " + ex.getMessage();
            panelLectura.addRow(mensajesTemp);
        } catch (NumberFormatException ex) {
        }
        //Conectamos con el servidor
        try {
            conexionConServidor = new Socket(servidor, puerto);
        } catch (UnknownHostException ex) {
            mensajesTemp[0] = "Sistema";
            mensajesTemp[1] = "Servidor desconocido: " + ex.getMessage();
            panelLectura.addRow(mensajesTemp);
        } catch (IOException ex) {
            mensajesTemp[0] = "Sistema";
            mensajesTemp[1] = "Error en el intento de conexión: " + ex.getMessage();
            panelLectura.addRow(mensajesTemp);
        }

        //Si conectamos con el servidor seguimos.
        if (conexionConServidor != null) {
            //Mostramos la ventana
            try {
                VentanaAplicacion tempVentana = new VentanaAplicacion(new DataOutputStream(conexionConServidor.getOutputStream()), panelLectura, true);
                tempVentana.setVisible(true);
            } catch (IOException ex) {
                System.err.println("Error en la creación del flujo de salida: " + ex.getMessage());
            }

            //Leerá eternamente los mensajes del servidor y los mostrará por pantalla
            String cadena = ""; //Nos servirá para tratar los mensajes del servidor.

            //Asociamos el buffer de entrada con el InputStream que nos ayudará a sacar los datos
            try {
                mensajesServidor = new DataInputStream(conexionConServidor.getInputStream());
            } catch (IOException ex) {
                mensajesTemp[0] = "Sistema";
                mensajesTemp[1] = "No se puede conectar el flujo de salida: " + ex.getMessage();
                panelLectura.addRow(mensajesTemp);
            }

            if (mensajesServidor != null) //Vemos que no haya fallado
            {
                while (true) {
                    try {
                        //Espera hasta tener un mensaje.
                        cadena = mensajesServidor.readUTF(); //Extrae cadenas de texto del servidor.
                        System.gc(); //Forzamos el recolector de basura de java para mantener la memoria limpia.
                    } catch (IOException ex) {
                        mensajesTemp[0] = "Sistema";
                        mensajesTemp[1] = "No se puede leer el mensaje: " + ex.getMessage();
                        panelLectura.addRow(mensajesTemp);
                    }

                    if (cadena != null) { //Si han escrito algo diferenciamos al emisor del mensaje
                        mensajesTemp[0] = cadena.substring(0, cadena.indexOf("|@|"));
                        mensajesTemp[1] = cadena.substring(cadena.indexOf("|@|") + 3);
                        panelLectura.addRow(mensajesTemp);
                    } 
                }
            }
            //Si no conectamos la ventana que lancemos no nos dejará hablar.
        } else {
            VentanaAplicacion tempVentana = new VentanaAplicacion(null, panelLectura, false);
            tempVentana.setVisible(true);
        }
    }
}

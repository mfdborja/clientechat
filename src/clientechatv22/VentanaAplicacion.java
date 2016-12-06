package clientechatv22;

import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class VentanaAplicacion extends javax.swing.JFrame {
    //Variables

    DataOutputStream bufferSalida = null; //Esto apunta al flujo del socket de salida de datos y nos da los métodos necesarios.
    String[] tempErrores = {"Sistema", ""}; //Variable temporal para escribir errores en pantalla.
    DefaultTableModel mensajesServidor;

    public VentanaAplicacion(DataOutputStream bufferSalida, DefaultTableModel mensajesServidor, boolean conectado) {
        initComponents();
        //El buffer donde escribiremos los mensajes de salida
        this.bufferSalida = bufferSalida;

        //Hacemos que la tabla muestre los mensajes del servidor
        this.mensajesServidor=mensajesServidor;
        jTablaLectura.setModel(mensajesServidor);

        if(!conectado){
            //Si no se conecta no dejamos hacer nada
            botonEnviar.setEnabled(false);
            jTextMensaje.setEnabled(false);
            nombreusuario.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScroll = new javax.swing.JScrollPane();
        jTablaLectura = new javax.swing.JTable();
        botonEnviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nombreusuario = new javax.swing.JTextField();
        jTextMensaje = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Borjaro2000 - Cliente Chat");

        jScroll.setAutoscrolls(true);
        jScroll.setEnabled(false);

        jTablaLectura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablaLectura.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTablaLectura.setEnabled(false);
        jScroll.setViewportView(jTablaLectura);

        botonEnviar.setText("Enviar");
        botonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("borjaro2000.tk");

        nombreusuario.setText("Anónimo");
        nombreusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreusuarioActionPerformed(evt);
            }
        });

        jTextMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMensajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombreusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextMensaje, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonEnviar, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarActionPerformed
        enviarMensaje();
    }//GEN-LAST:event_botonEnviarActionPerformed

    private void nombreusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreusuarioActionPerformed
        nombreusuario.setEnabled(false);
    }//GEN-LAST:event_nombreusuarioActionPerformed

    private void jTextMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMensajeActionPerformed
        //Si pulsamos enter al escribir envía el mensaje
        enviarMensaje();
    }//GEN-LAST:event_jTextMensajeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScroll;
    private javax.swing.JTable jTablaLectura;
    private javax.swing.JTextField jTextMensaje;
    private javax.swing.JTextField nombreusuario;
    // End of variables declaration//GEN-END:variables

    //Método para envíar mensajes
    private void enviarMensaje() {
        if (jTextMensaje.getText().length() > 0) {
            try {
                bufferSalida.writeUTF(nombreusuario.getText()+"|@|"+jTextMensaje.getText()); //Enviamos el mensaje.
                jTextMensaje.setText(""); //Borramos el panel de escritura.
            } catch (IOException ex) {
                tempErrores[1] = "Error en el envío del mensaje." + ex.getMessage();
            }

            jTextMensaje.requestFocus(); //El cursor vuelve al cuadro para escribir.

            //Aprovechamos y limpiamos un poco la pantalla con mensajes antiguos
            if(mensajesServidor.getRowCount()>1000){
                //Borra los 100 primeros mensajes de la ventana
                for(int i=0; i<100;i++)
                mensajesServidor.removeRow(0);
            }
        }
    }
}

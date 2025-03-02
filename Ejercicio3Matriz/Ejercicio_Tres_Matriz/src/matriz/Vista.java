package matriz;

import javax.swing.*;

public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    private GeneradorMatriz generadorMatriz;
    private OperacionesMatriz operacionesMatriz;

    public Vista() {
        initComponents();
        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        // Ocultar componentes al inicio
        ocultarComponentes();
        // Intentar cargar la matriz desde el archivo al iniciar
        generadorMatriz = GeneradorMatriz.cargarMatrizDesdeArchivo();
        if (generadorMatriz == null) {
            // Si no existe una matriz previa, crear una nueva con dimensiones por defecto
            generadorMatriz = new GeneradorMatriz(3, 3); // Puedes cambiar las dimensiones por defecto
            generadorMatriz.guardarMatrizEnArchivo();
        }
        txtMatriz.setText(generadorMatriz.mostrarMatriz());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumFilas = new javax.swing.JTextField();
        txtNumColumnas = new javax.swing.JTextField();
        btnMostrarMatriz = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMatrizResultado = new javax.swing.JTextArea();
        btnEliminarMultiplos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMatriz = new javax.swing.JTextArea();
        txtMultiplo = new javax.swing.JTextField();
        btnEliminarPrimos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIngresoMultiplos = new javax.swing.JLabel();
        btnCambiarOperacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Ejercicio 3");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ingrese la longitud en filas (M):");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ingrese la longitud en columnas (N):");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, -1));
        jPanel1.add(txtNumFilas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 60, -1));
        jPanel1.add(txtNumColumnas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 60, -1));

        btnMostrarMatriz.setBackground(new java.awt.Color(0, 153, 51));
        btnMostrarMatriz.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMostrarMatriz.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarMatriz.setText("Mostrar matriz");
        btnMostrarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarMatrizActionPerformed(evt);
            }
        });
        jPanel1.add(btnMostrarMatriz, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, -1, -1));

        txtMatrizResultado.setColumns(20);
        txtMatrizResultado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMatrizResultado.setRows(5);
        jScrollPane1.setViewportView(txtMatrizResultado);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 270, 240));

        btnEliminarMultiplos.setBackground(new java.awt.Color(51, 51, 255));
        btnEliminarMultiplos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarMultiplos.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarMultiplos.setText("Eliminar múltiplos");
        btnEliminarMultiplos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMultiplosActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarMultiplos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 160, -1));

        txtMatriz.setColumns(20);
        txtMatriz.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMatriz.setRows(5);
        jScrollPane2.setViewportView(txtMatriz);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 280, 240));

        txtMultiplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMultiploActionPerformed(evt);
            }
        });
        jPanel1.add(txtMultiplo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 200, -1));

        btnEliminarPrimos.setBackground(new java.awt.Color(204, 0, 255));
        btnEliminarPrimos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarPrimos.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarPrimos.setText("Eliminar primos");
        btnEliminarPrimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPrimosActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarPrimos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 150, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setText("Práctica 5");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        txtIngresoMultiplos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtIngresoMultiplos.setText("Ingresa el número a eliminar sus múltiplos:");
        jPanel1.add(txtIngresoMultiplos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        btnCambiarOperacion.setBackground(new java.awt.Color(0, 153, 153));
        btnCambiarOperacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCambiarOperacion.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarOperacion.setText("Cambiar operación");
        btnCambiarOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarOperacionActionPerformed(evt);
            }
        });
        jPanel1.add(btnCambiarOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ocultarComponentes() {
        btnEliminarMultiplos.setVisible(false);
        btnEliminarPrimos.setVisible(false);
        txtMultiplo.setVisible(false);
        txtMatriz.setVisible(false);
        txtMatrizResultado.setVisible(false);
        txtIngresoMultiplos.setVisible(false);
    }

    private void mostrarComponentesMultiplos() {
        txtMultiplo.setVisible(true);
        btnEliminarMultiplos.setVisible(true);
        txtMatriz.setVisible(true);
        txtMatrizResultado.setVisible(true);
        txtIngresoMultiplos.setVisible(true);
    }

    private void mostrarComponentesPrimos() {
        btnEliminarPrimos.setVisible(true);
        txtMatriz.setVisible(true);
        txtMatrizResultado.setVisible(true);
    }
    private void btnMostrarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarMatrizActionPerformed
        try {
            int filas = Integer.parseInt(txtNumFilas.getText());
            int columnas = Integer.parseInt(txtNumColumnas.getText());
            generadorMatriz = new GeneradorMatriz(filas, columnas);
            txtMatriz.setText(generadorMatriz.mostrarMatriz());
            generadorMatriz.guardarMatrizEnArchivo();

            // Mostrar JOptionPane para elegir la operación
            String[] opciones = {"Eliminar Múltiplos", "Eliminar Primos"};
            int eleccion = JOptionPane.showOptionDialog(
                    this,
                    "¿Qué operación desea realizar?",
                    "Seleccione una opción",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (eleccion == 0) {
                // Si elige "Eliminar Múltiplos"
                mostrarComponentesMultiplos();
            } else if (eleccion == 1) {
                // Si elige "Eliminar Primos"
                mostrarComponentesPrimos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para filas y columnas.");
        }
    }//GEN-LAST:event_btnMostrarMatrizActionPerformed

    private void txtMultiploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMultiploActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMultiploActionPerformed

    private void btnEliminarMultiplosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMultiplosActionPerformed
        if (generadorMatriz == null) {
            JOptionPane.showMessageDialog(this, "Primero cree o cargue una matriz.");
            return;
        }

        try {
            int numero = Integer.parseInt(txtMultiplo.getText());
            operacionesMatriz = new OperacionesMatriz(generadorMatriz.obtenerMatriz());
            operacionesMatriz.eliminarMultiplos(numero);
            txtMatrizResultado.setText(operacionesMatriz.mostrarMatriz());
            generadorMatriz.guardarMatrizEnArchivo();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para eliminar múltiplos.");
        }
    }//GEN-LAST:event_btnEliminarMultiplosActionPerformed

    private void btnEliminarPrimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPrimosActionPerformed
        if (generadorMatriz == null) {
            JOptionPane.showMessageDialog(this, "Primero cree o cargue una matriz.");
            return;
        }

        operacionesMatriz = new OperacionesMatriz(generadorMatriz.obtenerMatriz());
        operacionesMatriz.eliminarPrimos();
        txtMatrizResultado.setText(operacionesMatriz.mostrarMatriz());
        generadorMatriz.guardarMatrizEnArchivo();
    }//GEN-LAST:event_btnEliminarPrimosActionPerformed

    private void btnCambiarOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarOperacionActionPerformed
        String[] opciones = {"Eliminar Múltiplos", "Eliminar Primos"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Qué operación desea realizar?",
                "Seleccione una opción",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        // Ocultar todos los componentes primero
        ocultarComponentes();

        // Mostrar los componentes correspondientes a la nueva operación
        if (eleccion == 0) {
            // Si elige "Eliminar Múltiplos"
            mostrarComponentesMultiplos();
        } else if (eleccion == 1) {
            // Si elige "Eliminar Primos"
            mostrarComponentesPrimos();
        }
    }//GEN-LAST:event_btnCambiarOperacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarOperacion;
    private javax.swing.JButton btnEliminarMultiplos;
    private javax.swing.JButton btnEliminarPrimos;
    private javax.swing.JButton btnMostrarMatriz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtIngresoMultiplos;
    private javax.swing.JTextArea txtMatriz;
    private javax.swing.JTextArea txtMatrizResultado;
    private javax.swing.JTextField txtMultiplo;
    private javax.swing.JTextField txtNumColumnas;
    private javax.swing.JTextField txtNumFilas;
    // End of variables declaration//GEN-END:variables
}

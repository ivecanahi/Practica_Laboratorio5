/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.InformeEmpresa;
import controlador.utilidades.Utiles;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import vista.ModeloTablas.MTableInforme;

/**
 *
 * @author ASUS
 */
public class FrmInformeEmpresa extends javax.swing.JDialog {
    private InformeEmpresa informeEmpresa = new InformeEmpresa();
    private MTableInforme mTableInforme = new MTableInforme();
    private Integer fila = -1;

    /**
     * Creates new form FrmInformeEmpresa
     */
    public FrmInformeEmpresa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        crearArreglos();
        cargarTabla();
        btnGuardarFecha.setEnabled(true); 
    }

    private void crearArreglos() {
        btnGuardarRegistros.setEnabled(false); 

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        int mesSeleccionado = cbxmes.getSelectedIndex();

        cbxmes.removeAllItems();
        for (String mes : meses) {
            cbxmes.addItem(mes);
        }

        if (mesSeleccionado != -1) {
            cbxmes.setSelectedIndex(mesSeleccionado);
        }

        cbxmes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    btnGuardarFecha.setEnabled(true); 
                }
            }
        });
    }

    private void guardarMes() {
        String mesSeleccionado = (String) cbxmes.getSelectedItem();
        System.out.println("Guardado: Mes - " + mesSeleccionado);
        btnGuardarRegistros.setEnabled(true); 
    }

    private void cargarTabla() {
        mTableInforme.setInformeEmpresa(informeEmpresa);
        tbltablainforme.setModel(mTableInforme);
        tbltablainforme.updateUI();
    }

    private Boolean verificar() {
        return txtventas.getText().trim().isEmpty() || txtgastos.getText().trim().isEmpty();
    }

    private void limpiar() {
        txtventas.setText("");
        txtgastos.setText("");
        lblpromedio.setText("0.0");
        cargarTabla();
        fila = -1;
        tbltablainforme.clearSelection();
    }

    private void registrar() {
    if (verificar()) {
        JOptionPane.showMessageDialog(null, "Llene todos los campos");
    } else {
        if (fila == -1) {
            if (Utiles.validate(txtventas.getText().trim()) && Utiles.validate(txtgastos.getText().trim())) {
                Float ventas = Utiles.transformStringFloat(txtventas.getText().trim());
                Float gastos = Utiles.transformStringFloat(txtgastos.getText().trim());
                int mesSeleccionado = cbxmes.getSelectedIndex();
                for (int i = 0; i < mesSeleccionado; i++) {
                    if (informeEmpresa.getVentasMensual()[i] == null || informeEmpresa.getGastosMensual()[i] == null) {
                        JOptionPane.showMessageDialog(null, "Debe llenar los meses anteriores primero", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (informeEmpresa.guardar(ventas, gastos)) {
                    JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "OK!", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    actualizarDatos(); // Actualizar datos después de registrar
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al guardar o ya se llenaron todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese únicamente números ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (Utiles.validate(txtgastos.getText().trim())) {
                Float gastos = Utiles.transformStringFloat(txtgastos.getText().trim());
                Float ventas = Utiles.transformStringFloat(txtventas.getText().trim());
                if (informeEmpresa.modificar(ventas, gastos, fila)) {
                    JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "OK!", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    actualizarDatos(); 
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


    private void cargarDatos() {
        fila = tbltablainforme.getSelectedRow();
        if (fila >= 0) {
            if (informeEmpresa.getVentasMensual()[fila] != null && informeEmpresa.getGastosMensual()[fila] != null) {
                txtventas.setText(informeEmpresa.getVentasMensual()[fila].toString());
                txtgastos.setText(informeEmpresa.getGastosMensual()[fila].toString());
            } else {
                fila = -1;
                txtventas.setText("");
                txtgastos.setText("");
                JOptionPane.showMessageDialog(null, "Seleccione un dato válido de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            fila = -1;
            JOptionPane.showMessageDialog(null, "Seleccione un dato de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
  private void actualizarDatos() {
        
        lblTotalVentas.setText(String.valueOf(informeEmpresa.sumarVentas()));
        lblTotalGastos.setText(String.valueOf(informeEmpresa.sumarGastos()));
        
        
        lblVentaAlta.setText(String.valueOf(informeEmpresa.ventaMasAlta()));
        lblVentaBaja.setText(String.valueOf(informeEmpresa.ventaMasBaja()));
        
        
        lblGastoAlto.setText(String.valueOf(informeEmpresa.gastoMasAlto()));
        lblGastoBajo.setText(String.valueOf(informeEmpresa.gastoMasBajo()));
        
        
        lblpromedio.setText(String.valueOf(informeEmpresa.promedioVentas()));
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbxmes = new javax.swing.JComboBox<>();
        btnGuardarFecha = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbxanio = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtventas = new javax.swing.JTextField();
        txtgastos = new javax.swing.JTextField();
        btnGuardarRegistros = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltablainforme = new javax.swing.JTable();
        btnGuardarArchivo = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTotalVentas = new javax.swing.JTextField();
        lblTotalGastos = new javax.swing.JTextField();
        lblpromedio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblVentaAlta = new javax.swing.JTextField();
        lblGastoAlto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblVentaBaja = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lblGastoBajo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Informe Total de Ventas y Gastos de una Empresa");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Para emitir el informe por favor ingrese lo solicitado por cada mes del año");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Seleccione el mes:");

        cbxmes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio ", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cbxmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxmesActionPerformed(evt);
            }
        });

        btnGuardarFecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnGuardarFecha.setText("Guardar");
        btnGuardarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFechaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Seleccione el año:");

        cbxanio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(cbxanio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarFecha)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbxanio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnGuardarFecha)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Ventas: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Gastos: ");

        txtventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtventasActionPerformed(evt);
            }
        });

        txtgastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgastosActionPerformed(evt);
            }
        });

        btnGuardarRegistros.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnGuardarRegistros.setText("Guardar");
        btnGuardarRegistros.setEnabled(false);
        btnGuardarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegistrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtgastos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtventas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarRegistros)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtgastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnGuardarRegistros)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tabla final");

        tbltablainforme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mes", "Ventas", "Gastos"
            }
        ));
        jScrollPane1.setViewportView(tbltablainforme);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnGuardarArchivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnGuardarArchivo.setText("Guardar Archivo");
        btnGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarArchivoActionPerformed(evt);
            }
        });

        btnCargarArchivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnCargarArchivo.setText("Cargar Archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("Total Ventas:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("Total Gastos: ");

        lblTotalVentas.setText("0.0");
        lblTotalVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblTotalVentasActionPerformed(evt);
            }
        });

        lblTotalGastos.setText("0.0");
        lblTotalGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblTotalGastosActionPerformed(evt);
            }
        });

        lblpromedio.setText("0.0");
        lblpromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblpromedioActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("Venta total más alta: ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Gasto total más alto: ");

        lblVentaAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblVentaAltaActionPerformed(evt);
            }
        });

        lblGastoAlto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblGastoAltoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel12.setText("Detalles: ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel13.setText("Venta total más baja: ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setText("Gasto total más bajo: ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel15.setText("Año:");

        jTextField10.setText("2025");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnModificar.setText("Seleccionar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel16.setText("Promedio Ventas:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGastoAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblVentaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblVentaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(lblGastoBajo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(436, 436, 436))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGuardarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(30, 30, 30)
                                        .addComponent(lblpromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblTotalGastos))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(35, 35, 35))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarArchivo)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargarArchivo)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblpromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblTotalGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(lblVentaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblGastoAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(lblVentaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblGastoBajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxmesActionPerformed
        // TODO add your handling code here:
        crearArreglos();
    }//GEN-LAST:event_cbxmesActionPerformed

    private void txtventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtventasActionPerformed
        // TODO add your handling code here:
        registrar();
    }//GEN-LAST:event_txtventasActionPerformed

    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
 
        if (informeEmpresa.cargarArchivo()){
           cargarTabla();
           actualizarDatos();
           lblpromedio.setText(informeEmpresa.promedioVentas().toString());
           btnGuardarRegistros.setEnabled(true);
           btnGuardarArchivo.setEnabled(true);
       }
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnGuardarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFechaActionPerformed
        // TODO add your handling code here:
        guardarMes();
    }//GEN-LAST:event_btnGuardarFechaActionPerformed

    private void txtgastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgastosActionPerformed
        // TODO add your handling code here:
        registrar();
    }//GEN-LAST:event_txtgastosActionPerformed

    private void btnGuardarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegistrosActionPerformed
        // TODO add your handling code here:
       
        registrar();
    }//GEN-LAST:event_btnGuardarRegistrosActionPerformed

    private void btnGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarArchivoActionPerformed
        // TODO add your handling code here:
         if (informeEmpresa.generar_files()){
             JOptionPane.showMessageDialog(null, "Se ha generado el archivo correctamente", "OK!", JOptionPane.INFORMATION_MESSAGE);
        } else {
             JOptionPane.showMessageDialog(null, "Hubo un error, inténtelo más tarde", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
               
    }//GEN-LAST:event_btnGuardarArchivoActionPerformed

    private void lblpromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblpromedioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_lblpromedioActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
       cargarDatos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void lblTotalVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTotalVentasActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_lblTotalVentasActionPerformed

    private void lblTotalGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTotalGastosActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_lblTotalGastosActionPerformed

    private void lblGastoAltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblGastoAltoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblGastoAltoActionPerformed

    private void lblVentaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblVentaAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblVentaAltaActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInformeEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInformeEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInformeEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInformeEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmInformeEmpresa dialog = new FrmInformeEmpresa(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnGuardarArchivo;
    private javax.swing.JButton btnGuardarFecha;
    private javax.swing.JButton btnGuardarRegistros;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxanio;
    private javax.swing.JComboBox<String> cbxmes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField lblGastoAlto;
    private javax.swing.JTextField lblGastoBajo;
    private javax.swing.JTextField lblTotalGastos;
    private javax.swing.JTextField lblTotalVentas;
    private javax.swing.JTextField lblVentaAlta;
    private javax.swing.JTextField lblVentaBaja;
    private javax.swing.JTextField lblpromedio;
    private javax.swing.JTable tbltablainforme;
    private javax.swing.JTextField txtgastos;
    private javax.swing.JTextField txtventas;
    // End of variables declaration//GEN-END:variables
}

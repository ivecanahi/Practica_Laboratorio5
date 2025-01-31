/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.ModeloTablas;

import controlador.InformeEmpresa;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author IVETT
 */
public class MTableInforme extends AbstractTableModel{
private InformeEmpresa informeEmpresa;

    public InformeEmpresa getInformeEmpresa() {
        return informeEmpresa;
    }

    public void setInformeEmpresa(InformeEmpresa InformeEmpresa) {
        this.informeEmpresa = InformeEmpresa;
    }
   
    
    @Override
    public int getRowCount() {
        return informeEmpresa.getGastosMensual().length;
    }

    @Override
    public int getColumnCount() {
     return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
      switch (i1){
         case 0: return (i+1); 
         case 1: return informeEmpresa.getVentasMensual()[i];
         case 2: return informeEmpresa.getGastosMensual()[i];
         default: return null; 
             }
    }

    @Override
    public String getColumnName(int i) {
         switch (i){
         case 0: return "Mes";
         case 1: return "Ventas";
         case 2: return "Gastos";
         default: return null; 
             }
    }
    }

    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.utilidades.Utiles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class InformeEmpresa {

    //Declarar varables con arreglos
    private Float[] ventasMensual = new Float[12];
    private Float[] gastosMensual = new Float[12];

    public Float[] getVentasMensual() {
        return ventasMensual;
    }

    public Float[] getGastosMensual() {
        return gastosMensual;
    }

    public void crear(Integer mes) {
        this.ventasMensual = new Float[mes];
        gastosMensual = new Float[mes];
    }

    public Float promedioVentas() {
        float promedio = 0.0f;
        float cont = 0.0f;
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null) {
                cont++;
                promedio += ventasMensual[i];
            }

        }
        return Utiles.redondear(promedio / 12);

    }

    

    
    public Integer verificar() {

        Integer pos = -1;
        for (int i = 0; i < gastosMensual.length; i++) {
            if (gastosMensual[i] == null) {
                pos = i;
                break;
            }
        }

        return pos;

    }

    public Boolean guardar(Float ventaMensual, Float gastoMensual) {
        int pos = verificar();
        if (pos >= 0 && pos < ventasMensual.length) {
            if (ventasMensual[pos] == null) {
                ventasMensual[pos] = ventaMensual;
                gastosMensual[pos] = gastoMensual;
            } else {
                ventasMensual[pos] += ventaMensual;
                gastosMensual[pos] += gastoMensual;
            }
            System.out.println("guardado ");
            return true;
        }
        return false;
    }

    public Boolean modificar(Float ventaMensual, Float gastoMensual, Integer pos) {
        if (pos >= 0) {
            ventasMensual[pos] += ventaMensual;
            gastosMensual[pos] += gastoMensual;
            return true;
        }
        return false;
    }

    public Boolean generar_files() {
        if (gastosMensual != null && ventasMensual != null) {
            String pathgastos = "data" + File.separatorChar + "gastos.txt";
            String pathventas = "data" + File.separatorChar + "ventas.txt";
            try {
                FileWriter file_gastos = new FileWriter(pathgastos);
                FileWriter file_ventas = new FileWriter(pathventas);
                for (int i = 0; i < ventasMensual.length; i++) {
                    if (ventasMensual[i] != null) {
                        file_ventas.write(ventasMensual[i].toString() + "\n");
                        file_ventas.flush();
                    }
                }
                file_ventas.close();
                for (int i = 0; i < gastosMensual.length; i++) {
                    if (gastosMensual[i] != null) {
                        file_gastos.write(gastosMensual[i] + "\n");
                        file_gastos.flush();
                    }
                }
                file_gastos.close();
                return true;
            } catch (Exception e) {
                System.out.println("Error en archivo " + e);
                return false;
            }
        }
        return false;
    }

    public Boolean cargarArchivo() {
    String pathgastos = "data" + File.separatorChar + "gastos.txt";
    String pathventas = "data" + File.separatorChar + "ventas.txt";

    try (BufferedReader file_gastos = new BufferedReader(new FileReader(pathgastos));
         BufferedReader file_ventas = new BufferedReader(new FileReader(pathventas))) {

        
        crear(12); 

        String linea;
        int cont = 0;
        while ((linea = file_gastos.readLine()) != null) {
            gastosMensual[cont] = Utiles.transformStringFloat(linea);
            cont++;
        }

        cont = 0;
        while ((linea = file_ventas.readLine()) != null) {
            ventasMensual[cont] = Utiles.transformStringFloat(linea);
            cont++;
        }
        return true;

    } catch (FileNotFoundException e) {
        System.out.println("Archivo no encontrado: " + e.getMessage());
        return false;
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
        return false;
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        return false;
    }
}
    public float sumarVentas() {
        float total = 0;
        for (Float venta : ventasMensual) {
            if (venta != null) {
                total += venta;
            }
        }
        return total;
    }

   
    public float sumarGastos() {
        float total = 0;
        for (Float gasto : gastosMensual) {
            if (gasto != null) {
                total += gasto;
            }
        }
        return total;
    }
    
  
    public float ventaMasAlta() {
        float max = Float.MIN_VALUE;
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null && ventasMensual[i] > max) {
                max = ventasMensual[i];
            }
        }
        return max;
    }

    public float ventaMasBaja() {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null && ventasMensual[i] < min) {
                min = ventasMensual[i];
            }
        }
        return min;
    }
    
    public float gastoMasAlto() {
    float max = 0; 
    boolean tieneDatos = false;
    
    for (Float gasto : gastosMensual) {
        if (gasto != null) {
            tieneDatos = true;
            if (gasto > max) {
                max = gasto;
            }
        }
    }

    return tieneDatos ? max : 0; 
}


    public float gastoMasBajo() {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < gastosMensual.length; i++) {
            if (gastosMensual[i] != null && gastosMensual[i] < min) {
                min = gastosMensual[i];
            }
        }
        return min;
    }

}

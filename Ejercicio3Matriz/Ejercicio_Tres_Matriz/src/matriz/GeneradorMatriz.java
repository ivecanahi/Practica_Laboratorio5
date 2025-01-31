package matriz;

import java.io.*;
import java.util.Random;

public class GeneradorMatriz {
    private int filas;
    private int columnas;
    private int[][] matriz;
    private static final String ARCHIVO = "matriz.txt";

    public GeneradorMatriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new int[filas][columnas];
        generarMatrizAleatoria();
    }

    private void generarMatrizAleatoria() {
        Random rand = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = rand.nextInt(90) + 10; // Números entre 10 y 99
            }
        }
    }

    public int[][] obtenerMatriz() {
        return matriz;
    }

    public void guardarMatrizEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            writer.write(filas + " " + columnas + "\n");
            writer.write("Matriz Generada:\n");
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    writer.write(matriz[i][j] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Solo imprimimos la excepción en consola
        }
    }

    public static GeneradorMatriz cargarMatrizDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String[] dimensiones = reader.readLine().split(" ");
            int filas = Integer.parseInt(dimensiones[0]);
            int columnas = Integer.parseInt(dimensiones[1]);
            GeneradorMatriz generador = new GeneradorMatriz(filas, columnas);

            reader.readLine(); // Saltar "Matriz Generada:"
            for (int i = 0; i < filas; i++) {
                String[] valores = reader.readLine().split(" ");
                for (int j = 0; j < columnas; j++) {
                    generador.matriz[i][j] = Integer.parseInt(valores[j]);
                }
            }
            return generador;
        } catch (IOException | NumberFormatException e) {
            // Si el archivo no existe o hay un error, simplemente devolvemos null
            return null;
        }
    }

    public String mostrarMatriz() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(matriz[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
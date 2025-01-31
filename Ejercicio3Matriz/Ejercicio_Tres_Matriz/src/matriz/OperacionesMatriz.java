package matriz;

public class OperacionesMatriz {
    private int[][] matriz;

    public OperacionesMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void eliminarMultiplos(int numero) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] % numero == 0) {
                    matriz[i][j] = 0; // Eliminar múltiplos
                }
            }
        }
    }

    public void eliminarPrimos() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (esPrimo(matriz[i][j])) {
                    matriz[i][j] = 0; // Eliminar primos
                }
            }
        }
    }

    private boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    public String mostrarMatriz() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(matriz[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
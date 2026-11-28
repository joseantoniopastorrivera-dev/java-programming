//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa PruebaAlgoritmos.java

import java.util.Arrays;
import java.util.Random;

public class PruebaAlgoritmos {

    // Array de n números aleatorios entre 0 y max-1
    private static int[] aleatorio(Random r, int n, int max) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = r.nextInt(max);
        }
        return v;
    }

    public static void main(String[] args) {
        Random r = new Random(2026); // Semilla fija: la prueba sale igual cada vez

        System.out.println("--- Comprobación con 1000 arrays aleatorios ---");
        int fallos = 0;
        for (int prueba = 0; prueba < 1000; prueba++) {
            int[] original = aleatorio(r, r.nextInt(50), 100); // Tamaño de 0 a 49, con repetidos
            int[] esperado = original.clone(); // clone(): copia del array (si no, los tres serían el mismo)
            Arrays.sort(esperado);             // La referencia correcta
            int[] q = original.clone();
            int[] s = original.clone();
            QuickSort.ordenar(q);
            ShellSort.ordenar(s);
            // Arrays.equals compara contenido; == compararía si son el mismo objeto
            if (!Arrays.equals(q, esperado) || !Arrays.equals(s, esperado)) {
                fallos++;
            }
        }
        System.out.println("Fallos: " + fallos);

        System.out.println("\n--- Comparaciones con 10000 elementos ---");
        int[] grande = aleatorio(r, 10000, 1000000);
        int[] copia1 = grande.clone();
        int[] copia2 = grande.clone();
        QuickSort.ordenar(copia1);
        ShellSort.ordenar(copia2);
        System.out.println("QuickSort: " + QuickSort.comparaciones);
        System.out.println("ShellSort: " + ShellSort.comparaciones);
        System.out.println("Burbuja (n·(n-1)/2): " + (10000L * 9999 / 2)); // L: cálculo en long

        System.out.println("\n--- Búsqueda en el array ordenado ---");
        int[] v = copia1; // Ya ordenado
        int[] buscados = {v[0], v[5000], v[9999], -7}; // Tres que están y uno que no
        for (int x : buscados) {
            int pos = BusquedaBinaria.buscar(v, x);
            int pasosBin = BusquedaBinaria.pasos;
            int posRec = BusquedaBinaria.buscarRecursivo(v, x);
            BusquedaBinaria.buscarLineal(v, x);
            int pasosLin = BusquedaBinaria.pasos;
            // Con repetidos, la binaria puede encontrar otra posición con el mismo valor: se compara el valor
            boolean coinciden = (pos == -1 && posRec == -1) || (pos != -1 && posRec != -1 && v[pos] == v[posRec]);
            System.out.printf("x=%7d -> pos %5d | binaria %2d pasos | lineal %5d pasos | recursiva coincide: %b%n",
                    x, pos, pasosBin, pasosLin, coinciden);
        }
        // log2(10000) ≈ 13,3: la binaria nunca necesita más de 14 pasos
        System.out.println("Máximo teórico de la binaria: " + (int) Math.ceil(Math.log(10000) / Math.log(2)));
    }
}

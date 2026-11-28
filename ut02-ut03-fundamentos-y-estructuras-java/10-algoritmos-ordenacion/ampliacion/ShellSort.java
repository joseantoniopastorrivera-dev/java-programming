//Autor: JAPR
//Fecha: 28/Nov/2026
//Algoritmo ShellSort.java

public class ShellSort {
    public static long comparaciones = 0;

    // Inserción "a saltos": primero ordena elementos lejanos y luego reduce el salto hasta 1
    public static void ordenar(int[] v) {
        comparaciones = 0;
        for (int salto = v.length / 2; salto > 0; salto /= 2) { // n/2, n/4, … 1
            // Con salto = 1 es exactamente la inserción normal, pero el array ya está casi ordenado
            for (int i = salto; i < v.length; i++) {
                int actual = v[i]; // El que se va a insertar
                int j = i;
                // Mueve hacia la derecha los mayores que "actual", de salto en salto
                while (j >= salto) {
                    comparaciones++;
                    if (v[j - salto] <= actual) {
                        break; // Ya está en su sitio dentro de su subgrupo
                    }
                    v[j] = v[j - salto];
                    j -= salto;
                }
                v[j] = actual; // Lo coloca en el hueco que ha quedado
            }
        }
    }
}

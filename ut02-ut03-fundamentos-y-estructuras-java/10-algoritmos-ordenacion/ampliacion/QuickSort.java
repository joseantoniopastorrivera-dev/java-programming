//Autor: JAPR
//Fecha: 28/Nov/2026
//Algoritmo QuickSort.java (partición de Lomuto)

public class QuickSort {
    public static long comparaciones = 0; // static: un solo contador para todas las llamadas

    // Método público sencillo: oculta los índices de la recursión
    public static void ordenar(int[] v) {
        comparaciones = 0;
        ordenar(v, 0, v.length - 1); // Sobrecarga: mismo nombre, distintos parámetros
    }

    // Ordena v entre las posiciones ini y fin (incluidas)
    private static void ordenar(int[] v, int ini, int fin) {
        if (ini >= fin) {   // Caso base: 0 o 1 elementos ya están ordenados
            return;
        }
        int p = particion(v, ini, fin); // El pivote queda en su sitio definitivo
        ordenar(v, ini, p - 1);         // Ordena lo que queda a su izquierda (menores)
        ordenar(v, p + 1, fin);         // Y lo que queda a su derecha (mayores)
    }

    // Lomuto: deja a la izquierda los menores que el pivote y devuelve dónde acaba el pivote
    private static int particion(int[] v, int ini, int fin) {
        int pivote = v[fin]; // Se elige el último como pivote
        int i = ini - 1;     // Frontera: hasta i están los menores
        for (int j = ini; j < fin; j++) {
            comparaciones++;
            if (v[j] < pivote) {
                i++;
                intercambiar(v, i, j); // Mete v[j] en la zona de los menores
            }
        }
        intercambiar(v, i + 1, fin); // El pivote va justo detrás de los menores
        return i + 1;
    }

    private static void intercambiar(int[] v, int a, int b) {
        int aux = v[a]; // Variable auxiliar: sin ella se perdería un valor
        v[a] = v[b];
        v[b] = aux;
    }
}

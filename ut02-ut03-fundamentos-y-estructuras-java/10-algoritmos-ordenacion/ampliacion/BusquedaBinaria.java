//Autor: JAPR
//Fecha: 28/Nov/2026
//Algoritmo BusquedaBinaria.java

public class BusquedaBinaria {
    public static int pasos = 0; // Vueltas de la última búsqueda

    // Requisito: v debe estar ordenado de menor a mayor
    public static int buscar(int[] v, int x) {
        pasos = 0;
        int ini = 0;
        int fin = v.length - 1;
        while (ini <= fin) {      // Mientras quede un trozo donde buscar
            pasos++;
            // ini + (fin - ini) / 2 en vez de (ini + fin) / 2: evita desbordar int con arrays enormes
            int medio = ini + (fin - ini) / 2;
            if (v[medio] == x) {
                return medio;     // Encontrado
            } else if (v[medio] < x) {
                ini = medio + 1;  // Está a la derecha: se descarta la mitad izquierda
            } else {
                fin = medio - 1;  // Está a la izquierda
            }
        }
        return -1; // Se ha quedado sin trozo: no está
    }

    public static int buscarRecursivo(int[] v, int x) {
        pasos = 0;
        return buscarRecursivo(v, x, 0, v.length - 1);
    }

    // Misma idea: cada llamada trabaja con la mitad del trozo anterior
    private static int buscarRecursivo(int[] v, int x, int ini, int fin) {
        if (ini > fin) {
            return -1;     // Caso base: trozo vacío
        }
        pasos++;
        int medio = ini + (fin - ini) / 2;
        if (v[medio] == x) {
            return medio;  // Caso base: encontrado
        }
        if (v[medio] < x) {
            return buscarRecursivo(v, x, medio + 1, fin);
        }
        return buscarRecursivo(v, x, ini, medio - 1);
    }

    // Búsqueda lineal para comparar: mira uno a uno
    public static int buscarLineal(int[] v, int x) {
        pasos = 0;
        for (int i = 0; i < v.length; i++) {
            pasos++;
            if (v[i] == x) {
                return i;
            }
        }
        return -1;
    }
}

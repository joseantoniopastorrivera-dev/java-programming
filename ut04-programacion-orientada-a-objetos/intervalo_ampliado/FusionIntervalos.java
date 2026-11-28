//Autor: JAPR
//Fecha: 28/Nov/2026
//Algoritmo FusionIntervalos.java (merge intervals)

package intervalo_ampliado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FusionIntervalos {

    private FusionIntervalos() { } // Clase de utilidad: no se instancia

    // Devuelve una lista nueva con los solapados unidos. No modifica la lista recibida
    public static List<Intervalo> fusionar(List<Intervalo> entrada) {
        List<Intervalo> ordenados = new ArrayList<>(entrada); // Copia: no se desordena la del que llama
        Collections.sort(ordenados);  // Usa compareTo: quedan ordenados por mínimo

        List<Intervalo> resultado = new ArrayList<>();
        for (Intervalo actual : ordenados) {
            if (resultado.isEmpty()) {    // El primero entra tal cual
                resultado.add(actual);
                continue;                 // continue: salta a la siguiente vuelta del bucle
            }
            int ultimoIndice = resultado.size() - 1;
            Intervalo ultimo = resultado.get(ultimoIndice);
            if (ultimo.seSolapa(actual)) {
                // Como están ordenados, solo puede solaparse con el último añadido
                resultado.set(ultimoIndice, ultimo.union(actual)); // set reemplaza esa posición
            } else {
                resultado.add(actual);    // Hay hueco entre medias: empieza un grupo nuevo
            }
        }
        return resultado; // Coste: O(n log n) por la ordenación; el recorrido es O(n)
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase IntervaloTest.java ampliada

package intervalo_ampliado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntervaloTest {
    public static void main(String[] args) {
        System.out.println("--- Constructor que valida ---");
        try {
            new Intervalo(10, 5); // Mínimo mayor que máximo
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Solapamiento y unión ---");
        Intervalo a = new Intervalo(1, 5);
        Intervalo b = new Intervalo(4, 9);
        Intervalo c = new Intervalo(10, 12);
        System.out.println(a + " se solapa con " + b + ": " + a.seSolapa(b));
        System.out.println(a + " unión " + b + " = " + a.union(b));
        System.out.println(a + " se solapa con " + c + ": " + a.seSolapa(c));
        try {
            a.union(c);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Inmutabilidad ---");
        Intervalo d = a.desplazado(3);
        System.out.println("a sigue siendo " + a + "; desplazado: " + d);

        System.out.println("\n--- equals y hashCode ---");
        Intervalo copia = new Intervalo(1, 5);
        System.out.println("a == copia: " + (a == copia) + " | a.equals(copia): " + a.equals(copia));
        Set<Intervalo> conjunto = new HashSet<>(); // Un Set no admite repetidos (usa equals y hashCode)
        conjunto.add(a);
        conjunto.add(copia);
        System.out.println("Tamaño del HashSet tras añadir a y copia: " + conjunto.size()); // 1

        System.out.println("\n--- Ordenación (Comparable) ---");
        // Arrays.asList crea una lista de tamaño fijo; se copia en un ArrayList para poder ordenarla
        List<Intervalo> lista = new ArrayList<>(Arrays.asList(
                new Intervalo(8, 10), new Intervalo(1, 3), new Intervalo(1, 2), new Intervalo(5, 6)));
        Collections.sort(lista);
        System.out.println(lista);

        System.out.println("\n--- Fusión de intervalos ---");
        List<Intervalo> entrada = Arrays.asList(
                new Intervalo(8, 10), new Intervalo(1, 3), new Intervalo(15, 18),
                new Intervalo(2, 6), new Intervalo(9, 12));
        System.out.println("Entrada:   " + entrada);
        System.out.println("Fusionado: " + FusionIntervalos.fusionar(entrada)); // [1,6] [8,12] [15,18]
    }
}

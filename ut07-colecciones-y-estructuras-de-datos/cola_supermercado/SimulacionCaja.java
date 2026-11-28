//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa SimulacionCaja.java

package cola_supermercado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulacionCaja {
    private static final int MINUTOS = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.55;

    // Devuelve la caja con menos gente (la primera si empatan)
    private static Caja masLibre(List<Caja> cajas) {
        Caja mejor = cajas.get(0);
        for (Caja c : cajas) {
            if (c.ocupacion() < mejor.ocupacion()) {
                mejor = c;
            }
        }
        return mejor;
    }

    public static void main(String[] args) {
        Random azar = new Random(2026); // Semilla fija: misma simulación cada vez
        List<Caja> cajas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            cajas.add(new Caja(i));
        }

        int llegados = 0;
        for (int minuto = 0; minuto < MINUTOS; minuto++) {
            // nextDouble() da un número entre 0 y 1: es menor que 0,55 el 55 % de las veces
            if (azar.nextDouble() < PROBABILIDAD_LLEGADA) {
                llegados++;
                int articulos = 1 + azar.nextInt(8); // nextInt(8): 0..7, +1 → 1..8
                masLibre(cajas).llega(new Cliente(llegados, minuto, articulos));
            }
            for (Caja c : cajas) {
                c.avanzarMinuto(minuto); // Todas las cajas trabajan a la vez cada minuto
            }
            if (minuto % 30 == 29) { // Cada media hora, una foto de las colas
                System.out.print("Minuto " + (minuto + 1) + " -> ocupación:");
                for (Caja c : cajas) {
                    System.out.print(" " + c.ocupacion());
                }
                System.out.println();
            }
        }

        System.out.println("\n--- Resultado tras " + MINUTOS + " minutos ---");
        System.out.println("Clientes llegados: " + llegados);
        int atendidos = 0;
        for (Caja c : cajas) {
            System.out.println(c);
            atendidos += c.atendidos();
        }
        System.out.println("Atendidos en total: " + atendidos);
    }
}

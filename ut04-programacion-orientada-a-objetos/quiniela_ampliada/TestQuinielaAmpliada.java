//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase TestQuinielaAmpliada.java

package quiniela_ampliada;

import java.util.Random;

public class TestQuinielaAmpliada {
    public static void main(String[] args) {
        // Cada fila: {partido, signos}. Hay 3 dobles ("1X", "X2", "12") y 2 triples ("1X2")
        String[][] datos = {
            {"Real Madrid - Barcelona", "1X"},
            {"Atlético - Valladolid", "1"},
            {"Sevilla - Valencia", "X2"},
            {"Betis - Celta", "1X2"},
            {"Getafe - Rayo", "X"},
            {"Osasuna - Mallorca", "1"},
            {"Girona - Alavés", "12"},
            {"Villarreal - Espanyol", "1"},
            {"Athletic - Real Sociedad", "1X2"},
            {"Leganés - Las Palmas", "2"},
            {"Zaragoza - Sporting", "1"},
            {"Racing - Oviedo", "X"},
            {"Málaga - Cádiz", "1"},
            {"Granada - Almería", "2"},
        };

        Boleto boleto = new Boleto();
        for (String[] fila : datos) {
            boleto.anadir(new Pronostico(fila[0], fila[1]));
        }

        System.out.println("--- Boleto ---");
        boleto.mostrar();
        // 2^3 × 3^2 = 8 × 9 = 72 apuestas
        System.out.println("Apuestas: " + boleto.numeroApuestas());
        System.out.printf("Precio: %.2f €%n", boleto.precio());

        System.out.println("\n--- Resultados (semilla fija 2026) ---");
        Random azar = new Random(2026); // Con semilla, la secuencia "aleatoria" siempre es la misma
        Signo[] resultados = new Signo[Boleto.PARTIDOS];
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < resultados.length; i++) {
            resultados[i] = Signo.values()[azar.nextInt(3)]; // nextInt(3): 0, 1 o 2 → índice del enum
            linea.append(resultados[i].getCaracter()).append(' ');
        }
        System.out.println(linea);

        int aciertos = boleto.aciertos(resultados);
        System.out.println("Aciertos: " + aciertos + " -> " + Boleto.categoria(aciertos));

        System.out.println("\n--- Errores controlados ---");
        try {
            new Pronostico("Partido raro", "1Z"); // 'Z' no es un signo
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            boleto.anadir(new Pronostico("Partido 15", "1")); // El boleto ya está lleno
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            new Boleto().numeroApuestas(); // Boleto vacío
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

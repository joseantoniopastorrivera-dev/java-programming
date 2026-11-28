//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Boleto.java: escrutinio, precio y premio

package quiniela_ampliada;

public class Boleto {
    public static final int PARTIDOS = 14;
    private static final double PRECIO_APUESTA = 0.75;

    private final Pronostico[] pronosticos = new Pronostico[PARTIDOS]; // Array de tamaño fijo, como el original
    private int rellenos = 0; // Cuántos huecos se han ocupado

    public void anadir(Pronostico p) {
        if (rellenos == PARTIDOS) {
            // IllegalStateException: el objeto no está en un estado que permita la operación
            throw new IllegalStateException("El boleto ya tiene " + PARTIDOS + " partidos");
        }
        pronosticos[rellenos++] = p; // Usa rellenos como índice y después lo incrementa
    }

    private void comprobarCompleto() {
        if (rellenos < PARTIDOS) {
            throw new IllegalStateException("Faltan " + (PARTIDOS - rellenos) + " partidos");
        }
    }

    // 2^dobles × 3^triples: cada doble duplica las combinaciones y cada triple las triplica
    public long numeroApuestas() {
        comprobarCompleto();
        long apuestas = 1; // long: con muchos triples el número crece muy rápido
        for (Pronostico p : pronosticos) {
            apuestas *= p.numeroSignos(); // Multiplicar por 1, 2 o 3 es lo mismo que la fórmula
        }
        return apuestas;
    }

    public double precio() {
        return numeroApuestas() * PRECIO_APUESTA;
    }

    // Cuenta los partidos acertados
    public int aciertos(Signo[] resultados) {
        comprobarCompleto();
        if (resultados.length != PARTIDOS) {
            throw new IllegalArgumentException("Hacen falta " + PARTIDOS + " resultados");
        }
        int aciertos = 0;
        for (int i = 0; i < PARTIDOS; i++) { // for con índice: se recorren dos arrays a la vez
            if (pronosticos[i].acierta(resultados[i])) {
                aciertos++;
            }
        }
        return aciertos;
    }

    // static: no depende de ningún boleto concreto
    public static String categoria(int aciertos) {
        switch (aciertos) { // switch clásico, válido en Java 8
            case 14: return "1.ª categoría";
            case 13: return "2.ª categoría";
            case 12: return "3.ª categoría";
            case 11: return "4.ª categoría";
            case 10: return "5.ª categoría";
            default: return "Sin premio"; // return sale del método: no hace falta break
        }
    }

    public void mostrar() {
        for (int i = 0; i < rellenos; i++) {
            System.out.printf("%2d. %s%n", i + 1, pronosticos[i]);
        }
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Minijuego Ahorcado.java (ampliación de los minijuegos de UT02-UT03)
//Enunciado: el ordenador elige una palabra al azar de una lista. El jugador dice letras;
//cada fallo resta una vida (hay 6). Se gana al descubrir la palabra y se pierde sin vidas.
//No se puede repetir letra, solo valen letras de la A a la Z y la Ñ, y se muestran las falladas.
//Ejecutar: javac Ahorcado.java && java Ahorcado

import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    // Palabras sin tildes para no complicar la comparación de letras
    static final String[] PALABRAS = {"PROGRAMACION", "VARIABLE", "BUCLE", "ARRAY", "OBJETO", "COMPILADOR", "EXCEPCION"};
    static final int VIDAS = 6;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Si se pasa un número como argumento, se usa como semilla (sirve para probar el juego)
        Random azar = args.length > 0 ? new Random(Long.parseLong(args[0])) : new Random();
        String palabra = PALABRAS[azar.nextInt(PALABRAS.length)];

        char[] descubierta = new char[palabra.length()]; // Lo que ve el jugador
        for (int i = 0; i < descubierta.length; i++) {
            descubierta[i] = '_';
        }
        boolean[] usadas = new boolean[27]; // 26 letras A-Z más la Ñ en la posición 26
        StringBuilder falladas = new StringBuilder();
        int vidas = VIDAS;
        int pendientes = palabra.length(); // Letras que faltan por descubrir

        System.out.println("=== AHORCADO === (" + palabra.length() + " letras, " + VIDAS + " vidas)");
        while (vidas > 0 && pendientes > 0) {
            System.out.println("\n" + new String(descubierta).replace("", " ").trim()); // Espacios entre letras
            System.out.println("Vidas: " + vidas + " | Falladas: " + falladas);
            System.out.print("Letra: ");
            if (!sc.hasNextLine()) { // Si se acaba la entrada (por ejemplo, en una prueba), se sale
                break;
            }
            String entrada = sc.nextLine().trim().toUpperCase();
            if (entrada.length() != 1) {
                System.out.println("Escribe una sola letra.");
                continue;
            }
            char letra = entrada.charAt(0);
            int indice;
            if (letra == 'Ñ') {
                indice = 26;
            } else if (letra >= 'A' && letra <= 'Z') { // Los char se comparan por su código Unicode
                indice = letra - 'A';                  // 'A' → 0, 'B' → 1…
            } else {
                System.out.println("Eso no es una letra.");
                continue;
            }
            if (usadas[indice]) {
                System.out.println("Ya has dicho la " + letra + ".");
                continue; // Repetir no cuesta vida
            }
            usadas[indice] = true;

            boolean acierto = false;
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) { // Puede aparecer varias veces
                    descubierta[i] = letra;
                    pendientes--;
                    acierto = true;
                }
            }
            if (!acierto) {
                vidas--;
                falladas.append(letra).append(' ');
                System.out.println("No está.");
            }
        }

        if (pendientes == 0) {
            System.out.println("\n¡Has ganado! La palabra era " + palabra + " (te sobraron " + vidas + " vidas).");
        } else {
            System.out.println("\nHas perdido. La palabra era " + palabra + ".");
        }
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Palindromos.java (ampliación de manipulación de Strings)
//Enunciado: comprobar si una frase es palíndroma ignorando espacios, signos, mayúsculas y tildes
//("Dábale arroz a la zorra el abad"), invertir cada palabra de una frase y buscar el palíndromo
//más largo dentro de un texto. Ejecutar: javac Palindromos.java && java Palindromos

import java.text.Normalizer; // Permite separar las tildes de las letras

public class Palindromos {

    // Deja solo letras y números, en minúsculas y sin tildes
    static String normalizar(String texto) {
        // NFD descompone "á" en "a" + tilde; la regex \\p{M} borra esas marcas (tildes, diéresis)
        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        // [^a-z0-9]: todo lo que NO sea letra o número se borra
        return sinTildes.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    // Dos índices que avanzan desde los extremos hacia el centro
    static boolean esPalindromo(String frase) {
        String s = normalizar(frase);
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false; // A la primera diferencia ya no lo es
            }
            i++;
            j--;
        }
        return true; // Cadena vacía o de una letra: es palíndromo
    }

    // Invierte cada palabra manteniendo el orden: "hola mundo" → "aloh odnum"
    static String invertirPalabras(String frase) {
        String[] palabras = frase.trim().split("\\s+"); // \\s+: uno o más espacios seguidos
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < palabras.length; k++) {
            if (k > 0) {
                sb.append(' ');
            }
            sb.append(new StringBuilder(palabras[k]).reverse()); // reverse() de StringBuilder invierte
        }
        return sb.toString();
    }

    // Palíndromo más largo: "expandir desde el centro" probando cada posición
    static String palindromoMasLargo(String texto) {
        String s = texto.toLowerCase();
        int mejorIni = 0;
        int mejorLong = 0;
        for (int centro = 0; centro < s.length(); centro++) {
            // Dos casos: centro en una letra (impar, "ana") o entre dos letras (par, "otto")
            for (int par = 0; par <= 1; par++) {
                int izq = centro;
                int der = centro + par;
                while (izq >= 0 && der < s.length() && s.charAt(izq) == s.charAt(der)) {
                    izq--; // Mientras coincidan los extremos, se abre hacia fuera
                    der++;
                }
                int longitud = der - izq - 1; // Se ha pasado una posición por cada lado
                if (longitud > mejorLong) {
                    mejorLong = longitud;
                    mejorIni = izq + 1;
                }
            }
        }
        return texto.substring(mejorIni, mejorIni + mejorLong);
    }

    public static void main(String[] args) {
        String[] frases = {
            "Dábale arroz a la zorra el abad",
            "Anita lava la tina",
            "¿Acaso hubo búhos acá?",
            "Esto no es un palíndromo",
            "12321",
        };
        System.out.println("--- ¿Es palíndromo? ---");
        for (String f : frases) {
            System.out.printf("%-35s -> %b%n", f, esPalindromo(f));
        }

        System.out.println("\n--- Invertir palabras ---");
        System.out.println(invertirPalabras("  Hola   mundo desde Java "));

        System.out.println("\n--- Palíndromo más largo ---");
        System.out.println(palindromoMasLargo("el reconocer de otto es radar")); // " reconocer "
        System.out.println(palindromoMasLargo("abcddcbz"));                      // "bcddcb"
    }
}

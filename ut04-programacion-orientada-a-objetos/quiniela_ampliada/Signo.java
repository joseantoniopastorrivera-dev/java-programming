//Autor: JAPR
//Fecha: 28/Nov/2026
//Enumerado Signo.java

package quiniela_ampliada;

// Sustituye a los char '1', 'X', '2' del original: con un enum no se puede apostar una 'Z'
public enum Signo {
    UNO('1'),
    EQUIS('X'),
    DOS('2');

    private final char caracter;

    Signo(char caracter) {
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }

    // Convierte el char que escribe el usuario en el enum
    public static Signo desdeCaracter(char c) {
        char mayus = Character.toUpperCase(c); // Acepta 'x' minúscula
        for (Signo s : values()) {             // Recorre UNO, EQUIS y DOS
            if (s.caracter == mayus) {
                return s;
            }
        }
        throw new IllegalArgumentException("Signo no válido: '" + c + "' (usa 1, X o 2)");
    }
}

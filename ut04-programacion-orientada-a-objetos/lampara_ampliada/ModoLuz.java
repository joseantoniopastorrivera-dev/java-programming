//Autor: JAPR
//Fecha: 28/Nov/2026
//Enumerado ModoLuz.java

package lampara_ampliada;

// Un enum es una clase con un número fijo de objetos: no se pueden crear más con new
public enum ModoLuz {
    NORMAL(100),  // Cada constante llama al constructor con su intensidad
    LECTURA(70),
    NOCHE(15);    // El punto y coma separa las constantes del resto del enum

    private final int intensidad; // Cada modo guarda su propio valor

    // El constructor de un enum es siempre privado
    ModoLuz(int intensidad) {
        this.intensidad = intensidad;
    }

    public int getIntensidad() {
        return intensidad;
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Entidad Libro.java con equals y hashCode

package biblioteca_ampliada;

public class Libro {
    private final String titulo; // final: un libro no cambia de título ni de ISBN
    private final String autor;
    private final String isbn;
    private boolean prestado;    // Este sí cambia: no es final

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prestado = false; // Un libro nuevo está disponible
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public boolean isPrestado() { return prestado; } // Con boolean, el getter empieza por "is"

    // Solo la biblioteca debería llamarlo: no lleva public (visibilidad de paquete)
    void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    // Dos libros son el mismo si tienen el mismo ISBN, aunque sean objetos distintos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                  // Mismo objeto en memoria
        if (!(o instanceof Libro)) return false;     // null o de otra clase: no son iguales
        Libro otro = (Libro) o;                      // Cast seguro tras el instanceof
        return isbn.equals(otro.isbn);               // Se compara el contenido, no la referencia
    }

    // Regla de Java: si dos objetos son equals, deben tener el mismo hashCode.
    // Si no se redefine, ArrayList.contains() funciona pero HashSet y HashMap fallarían
    @Override
    public int hashCode() {
        return isbn.hashCode(); // Se usa el mismo campo que en equals
    }

    @Override
    public String toString() {
        // Operador ternario: condición ? valor_si_true : valor_si_false
        return titulo + " (" + autor + ") [" + isbn + "] " + (prestado ? "PRESTADO" : "disponible");
    }
}

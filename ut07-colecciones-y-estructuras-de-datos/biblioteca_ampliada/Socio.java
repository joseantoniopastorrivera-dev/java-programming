//Autor: JAPR
//Fecha: 28/Nov/2026
//Entidad Socio.java

package biblioteca_ampliada;

import java.util.ArrayList;

public class Socio {
    public static final int MAX_PRESTAMOS = 3; // Constante pública: la usa también Biblioteca
    private final int numero;
    private final String nombre;
    private final ArrayList<Libro> prestados = new ArrayList<>(); // Libros que tiene ahora

    public Socio(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public int getNumero() { return numero; }
    public String getNombre() { return nombre; }

    // ¿Puede llevarse otro libro?
    public boolean puedePedir() {
        return prestados.size() < MAX_PRESTAMOS; // size(): cuántos elementos hay
    }

    // Visibilidad de paquete: solo Biblioteca cambia la lista
    void anadirPrestamo(Libro l) {
        prestados.add(l);
    }

    // remove(Object) usa equals(): por eso Libro redefine equals con el ISBN
    boolean quitarPrestamo(Libro l) {
        return prestados.remove(l); // true si lo encontró y lo quitó
    }

    @Override
    public String toString() {
        return "Socio " + numero + " - " + nombre + " | Prestados: " + prestados.size() + "/" + MAX_PRESTAMOS;
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Biblioteca.java: préstamos con HashMap

package biblioteca_ampliada;

import java.util.ArrayList;
import java.util.Collections; // Utilidades para listas: sort, reverse, max…
import java.util.Comparator;  // Indica cómo comparar dos objetos para ordenar
import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private final Map<String, Libro> libros = new HashMap<>();  // ISBN → Libro
    private final Map<Integer, Socio> socios = new HashMap<>(); // nº de socio → Socio (Integer: los mapas no admiten int)

    public void anadirLibro(Libro l) {
        libros.put(l.getIsbn(), l);
    }

    public void anadirSocio(Socio s) {
        socios.put(s.getNumero(), s); // Autoboxing: el int se convierte solo en Integer
    }

    // Busca el socio o lanza un error claro si no existe
    private Socio buscarSocio(int numero) {
        Socio s = socios.get(numero);
        if (s == null) {
            throw new IllegalArgumentException("No existe el socio " + numero);
        }
        return s;
    }

    // Presta un libro comprobando, en este orden, el socio, el libro y el límite
    public void prestar(int numSocio, String isbn) throws LibroNoDisponibleException, LimitePrestamosException {
        Socio s = buscarSocio(numSocio);
        Libro l = libros.get(isbn);
        if (l == null) {
            throw new LibroNoDisponibleException("No existe el libro con ISBN " + isbn);
        }
        if (l.isPrestado()) {
            throw new LibroNoDisponibleException("\"" + l.getTitulo() + "\" ya está prestado");
        }
        if (!s.puedePedir()) {
            throw new LimitePrestamosException(s.getNombre(), Socio.MAX_PRESTAMOS);
        }
        // Solo se cambia el estado cuando todas las comprobaciones han pasado
        l.setPrestado(true);
        s.anadirPrestamo(l);
    }

    // Devuelve un libro: el socio tiene que tenerlo
    public void devolver(int numSocio, String isbn) {
        Socio s = buscarSocio(numSocio);
        Libro l = libros.get(isbn);
        if (l == null || !s.quitarPrestamo(l)) { // Cortocircuito: si l es null no se llama a quitarPrestamo
            throw new IllegalArgumentException(s.getNombre() + " no tiene prestado el ISBN " + isbn);
        }
        l.setPrestado(false);
    }

    // Libros no prestados, ordenados por título
    public ArrayList<Libro> librosDisponibles() {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro l : libros.values()) {
            if (!l.isPrestado()) {
                resultado.add(l);
            }
        }
        // Comparator.comparing: ordena por lo que devuelva getTitulo (referencia a método, Java 8)
        Collections.sort(resultado, Comparator.comparing(Libro::getTitulo));
        return resultado;
    }

    // Búsqueda por autor sin distinguir mayúsculas
    public ArrayList<Libro> buscarPorAutor(String autor) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro l : libros.values()) {
            if (l.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public void mostrarSocios() {
        for (Socio s : socios.values()) {
            System.out.println("  " + s);
        }
    }
}

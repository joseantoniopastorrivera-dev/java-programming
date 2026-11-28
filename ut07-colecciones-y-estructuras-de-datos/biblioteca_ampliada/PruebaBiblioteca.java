//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaBiblioteca.java

package biblioteca_ampliada;

public class PruebaBiblioteca {

    // Método auxiliar: evita repetir el mismo try-catch en cada préstamo
    private static void intentarPrestar(Biblioteca b, int socio, String isbn) {
        try {
            b.prestar(socio, isbn);
            System.out.println("OK: socio " + socio + " se lleva " + isbn);
        } catch (LibroNoDisponibleException | LimitePrestamosException e) { // Las dos propias
            System.out.println("No se pudo: " + e.getMessage());
        } catch (IllegalArgumentException e) { // Socio inexistente
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();
        b.anadirLibro(new Libro("Don Quijote", "Cervantes", "111"));
        b.anadirLibro(new Libro("Novelas ejemplares", "Cervantes", "112"));
        b.anadirLibro(new Libro("La Regenta", "Clarín", "222"));
        b.anadirLibro(new Libro("Fortunata y Jacinta", "Galdós", "333"));
        b.anadirLibro(new Libro("Marianela", "Galdós", "334"));
        b.anadirSocio(new Socio(1, "JAPR"));
        b.anadirSocio(new Socio(2, "Ana"));

        System.out.println("--- Préstamos ---");
        intentarPrestar(b, 1, "111");
        intentarPrestar(b, 1, "222");
        intentarPrestar(b, 1, "333");
        intentarPrestar(b, 1, "334"); // Cuarto libro: supera el límite de 3
        intentarPrestar(b, 2, "111"); // Ya lo tiene JAPR
        intentarPrestar(b, 2, "999"); // No existe
        intentarPrestar(b, 9, "112"); // Socio inexistente

        System.out.println("\n--- Disponibles (por título) ---");
        for (Libro l : b.librosDisponibles()) {
            System.out.println("  " + l);
        }

        System.out.println("\n--- Devolución ---");
        b.devolver(1, "111");
        System.out.println("JAPR devuelve 111");
        intentarPrestar(b, 2, "111"); // Ahora Ana sí puede
        try {
            b.devolver(2, "222"); // Ana no tiene ese libro
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Libros de \"galdós\" (sin distinguir mayúsculas) ---");
        for (Libro l : b.buscarPorAutor("GALDÓS")) {
            System.out.println("  " + l);
        }

        System.out.println("\n--- Socios ---");
        b.mostrarSocios();

        // equals por ISBN: dos objetos distintos con el mismo ISBN son iguales
        Libro a = new Libro("Copia", "X", "111");
        Libro c = new Libro("Otra copia", "Y", "111");
        System.out.println("\n¿a == c? " + (a == c) + " | ¿a.equals(c)? " + a.equals(c));
    }
}

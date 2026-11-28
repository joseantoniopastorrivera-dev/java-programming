//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción LibroNoDisponibleException.java

package biblioteca_ampliada;

// Comprobada (hereda de Exception): quien preste un libro debe tratar el caso
public class LibroNoDisponibleException extends Exception {
    private static final long serialVersionUID = 1L; // Versión de serialización

    // Solo recibe el mensaje y se lo pasa al constructor de Exception
    public LibroNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}

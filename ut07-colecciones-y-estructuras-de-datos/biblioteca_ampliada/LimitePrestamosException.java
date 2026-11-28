//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción LimitePrestamosException.java

package biblioteca_ampliada;

// Otra excepción distinta para que quien la capture sepa exactamente qué pasó
public class LimitePrestamosException extends Exception {
    private static final long serialVersionUID = 1L;

    // Recibe el nombre del socio y el límite para montar un mensaje claro
    public LimitePrestamosException(String socio, int limite) {
        super(socio + " ya tiene " + limite + " libros prestados, que es el máximo");
    }
}

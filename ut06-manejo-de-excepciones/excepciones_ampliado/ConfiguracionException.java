//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción base ConfiguracionException.java

package excepciones_ampliado;

// Excepción base de la jerarquía: se puede capturar ella sola para tratar todos los errores de configuración
public class ConfiguracionException extends Exception {
    private static final long serialVersionUID = 1L;

    // Solo mensaje: para errores detectados por nuestro propio código
    public ConfiguracionException(String mensaje) {
        super(mensaje);
    }

    // Mensaje y causa: envuelve otra excepción sin perderla (excepción encadenada).
    // Throwable es la superclase de todas las excepciones y errores
    public ConfiguracionException(String mensaje, Throwable causa) {
        super(mensaje, causa); // La causa queda disponible con getCause()
    }
}

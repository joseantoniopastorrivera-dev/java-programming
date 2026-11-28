//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción DniInvalidoException.java

package dni_ampliado;

// Comprobada: quien registre un DNI tiene que decidir qué hacer si es incorrecto
public class DniInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    // Junta el DNI recibido y el motivo en un solo mensaje
    public DniInvalidoException(String dni, String motivo) {
        super("DNI \"" + dni + "\" no válido: " + motivo);
    }
}

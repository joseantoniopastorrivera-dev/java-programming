//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción hija ClaveObligatoriaException.java

package excepciones_ampliado;

// Hereda de ConfiguracionException: un catch (ConfiguracionException e) también la captura
public class ClaveObligatoriaException extends ConfiguracionException {
    private static final long serialVersionUID = 1L;

    private final String clave; // Dato extra: quien la capture puede saber qué clave faltaba

    public ClaveObligatoriaException(String clave) {
        super("Falta la clave obligatoria \"" + clave + "\"");
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }
}

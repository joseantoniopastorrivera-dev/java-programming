//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción LineaCsvException.java

package inventario_csv;

public class LineaCsvException extends Exception {
    private static final long serialVersionUID = 1L;

    public LineaCsvException(String mensaje) {
        super(mensaje);
    }

    // Con causa, para no perder el NumberFormatException original
    public LineaCsvException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

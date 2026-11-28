//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase de utilidad ValidadorDni.java

package dni_ampliado;

// final + constructor privado: es una clase de utilidad, solo tiene métodos static (como Math)
public final class ValidadorDni {
    // Tabla oficial: la posición i es la letra que corresponde al resto i
    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    private ValidadorDni() { } // Nadie puede hacer new ValidadorDni()

    // Letra de control de un número de DNI
    public static char calcularLetra(int numero) {
        return LETRAS.charAt(numero % 23); // % da el resto (0..22): justo los índices de la tabla
    }

    // Valida y devuelve el DNI normalizado (sin espacios y en mayúsculas)
    public static String validar(String dni) throws DniInvalidoException {
        if (dni == null) { // Se comprueba antes de llamar a ningún método sobre dni
            throw new DniInvalidoException("null", "está vacío");
        }
        String limpio = dni.trim().toUpperCase(); // trim quita espacios al principio y al final
        // Expresión regular: \\d{8} = 8 dígitos, [A-Z] = una letra. matches() exige que encaje entero
        if (!limpio.matches("\\d{8}[A-Z]")) {
            throw new DniInvalidoException(dni, "debe tener 8 cifras y una letra");
        }
        // substring(0, 8): los 8 primeros caracteres. parseInt los convierte en número
        int numero = Integer.parseInt(limpio.substring(0, 8));
        char letra = limpio.charAt(8); // Posición 8: la novena, la letra
        char correcta = calcularLetra(numero);
        if (letra != correcta) { // char se compara con != (es un tipo primitivo)
            throw new DniInvalidoException(dni, "la letra debería ser " + correcta);
        }
        return limpio;
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase LectorConfiguracion.java

package excepciones_ampliado;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap; // Como HashMap, pero recuerda el orden de inserción
import java.util.Map;

public class LectorConfiguracion {
    private final Map<String, String> valores = new LinkedHashMap<>();

    // Lee el fichero entero. Todas las causas de error salen como ConfiguracionException
    public LectorConfiguracion(Path fichero) throws ConfiguracionException {
        // try-with-resources: lo declarado entre paréntesis se cierra solo al salir,
        // haya excepción o no (BufferedReader implementa AutoCloseable)
        try (BufferedReader lector = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
            String linea;
            int numero = 0;
            while ((linea = lector.readLine()) != null) { // readLine devuelve null al final del fichero
                numero++;
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue; // Vacía o comentario: se salta
                }
                int igual = linea.indexOf('='); // Posición del primer '=', o -1 si no hay
                if (igual <= 0) {               // -1 (no hay) o 0 (clave vacía)
                    throw new ConfiguracionException("Línea " + numero + " sin formato clave=valor: \"" + linea + "\"");
                }
                valores.put(linea.substring(0, igual).trim(), linea.substring(igual + 1).trim());
            }
        } catch (IOException e) {
            // Excepción encadenada: se traduce a nuestra excepción, pero se guarda la original
            throw new ConfiguracionException("No se pudo leer " + fichero.getFileName(), e);
        }
    }

    // null si la clave no está
    public String get(String clave) {
        return valores.get(clave);
    }

    public String getObligatoria(String clave) throws ClaveObligatoriaException {
        String valor = valores.get(clave);
        if (valor == null) {
            throw new ClaveObligatoriaException(clave);
        }
        return valor;
    }

    public int getEntero(String clave) throws ConfiguracionException {
        String valor = getObligatoria(clave); // Puede lanzar la hija; el throws de la madre la cubre
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) { // parseInt lanza esta excepción no comprobada
            throw new ConfiguracionException("El valor de \"" + clave + "\" no es un entero: " + valor, e);
        }
    }

    public int tamanio() {
        return valores.size();
    }
}

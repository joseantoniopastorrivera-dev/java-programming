//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaExcepciones.java

package excepciones_ampliado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class PruebaExcepciones {
    private static int intentos = 0; // Lo incrementa el finally

    // Lee un fichero y muestra el resultado o el error con su causa
    private static void probar(Path fichero) {
        System.out.println("\n>> " + fichero.getFileName());
        try {
            LectorConfiguracion conf = new LectorConfiguracion(fichero);
            System.out.println("Claves leídas: " + conf.tamanio());
            System.out.println("servidor = " + conf.getObligatoria("servidor"));
            System.out.println("puerto   = " + conf.getEntero("puerto"));
            System.out.println("usuario  = " + conf.getObligatoria("usuario"));
        } catch (ClaveObligatoriaException e) { // La hija va ANTES que la madre: si no, no compila
            System.out.println("Falta un dato: " + e.getClave());
        } catch (ConfiguracionException e) {
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) { // Excepción encadenada: la original sigue ahí
                System.out.println("  Causa: " + e.getCause().getClass().getSimpleName());
            }
        } finally {
            intentos++; // finally se ejecuta siempre: con éxito, con error capturado o sin capturar
        }
    }

    public static void main(String[] args) throws IOException { // Si falla crear los ficheros, el programa para
        Path carpeta = Files.createTempDirectory("config"); // Carpeta temporal del sistema: no ensucia el repo

        Path bien = carpeta.resolve("bien.conf"); // resolve: une la carpeta y el nombre
        Files.write(bien, Arrays.asList("# Comentario", "servidor = localhost", "", "puerto=8080", "usuario=japr"),
                StandardCharsets.UTF_8);
        Path sinUsuario = carpeta.resolve("sin-usuario.conf");
        Files.write(sinUsuario, Arrays.asList("servidor=localhost", "puerto=3306"), StandardCharsets.UTF_8);
        Path lineaMal = carpeta.resolve("linea-mal.conf");
        Files.write(lineaMal, Arrays.asList("servidor=localhost", "esto no vale"), StandardCharsets.UTF_8);
        Path numeroMal = carpeta.resolve("numero-mal.conf");
        Files.write(numeroMal, Arrays.asList("servidor=localhost", "puerto=ochenta"), StandardCharsets.UTF_8);
        Path noExiste = carpeta.resolve("no-existe.conf");

        probar(bien);
        probar(sinUsuario);
        probar(lineaMal);
        probar(numeroMal);
        probar(noExiste);

        System.out.println("\nIntentos contados en finally: " + intentos);

        // Limpieza: se borran los ficheros y la carpeta temporal
        for (Path p : new Path[] {bien, sinUsuario, lineaMal, numeroMal}) {
            Files.deleteIfExists(p);
        }
        Files.deleteIfExists(carpeta);
    }
}

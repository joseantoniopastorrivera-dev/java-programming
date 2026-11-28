//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaInventarioCsv.java

package inventario_csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebaInventarioCsv {
    public static void main(String[] args) {
        Inventario inv = new Inventario();
        inv.anadir(new Producto("P03", "Teclado", 24.90, 12));
        inv.anadir(new Producto("P01", "Ratón", 9.50, 40));
        inv.anadir(new Producto("P02", "Monitor 24", 129.00, 3));
        inv.anadir(new Producto("P04", "Cable HDMI", 5.25, 2));

        Path csv = null; // Se declara fuera del try para poder borrarlo en el finally
        try {
            csv = Files.createTempFile("inventario", ".csv"); // Fichero temporal único
            inv.guardar(csv);
            System.out.println("--- Contenido de " + csv.getFileName() + " ---");
            for (String linea : Files.readAllLines(csv, StandardCharsets.UTF_8)) { // Lee todo de una vez
                System.out.println(linea);
            }

            // Se añaden dos líneas erróneas al final (APPEND: sin borrar lo anterior)
            Files.write(csv, Arrays.asList("P05;Altavoz;doce;4", "P06;Webcam;30.00"),
                    StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            System.out.println("\n--- Carga ---");
            List<Producto> antes = new ArrayList<>(inv.getProductos()); // Copia para comparar luego
            Inventario cargado = new Inventario();
            int malas = cargado.cargar(csv);
            System.out.println("Productos cargados: " + cargado.getProductos().size() + " | descartadas: " + malas);
            for (Producto p : cargado.getProductos()) {
                System.out.println("  " + p);
            }
            // equals de List compara elemento a elemento con el equals de Producto
            System.out.println("¿Lo cargado coincide con lo guardado? " + antes.equals(new ArrayList<>(cargado.getProductos())));

            System.out.printf("%nValor total: %.2f €%n", cargado.valorTotal());
            System.out.println("Con menos de 5 unidades:");
            for (Producto p : cargado.bajoStock(5)) {
                System.out.println("  " + p);
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } finally {
            if (csv != null) {
                try {
                    Files.deleteIfExists(csv); // Limpieza del temporal
                } catch (IOException e) {
                    System.out.println("No se pudo borrar el temporal: " + e.getMessage());
                }
            }
        }
    }
}

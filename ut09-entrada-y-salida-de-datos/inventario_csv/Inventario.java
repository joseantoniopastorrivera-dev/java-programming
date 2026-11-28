//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Inventario.java: guarda y carga CSV

package inventario_csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Inventario {
    private static final String CABECERA = "codigo;nombre;precio;unidades";
    private final Map<String, Producto> productos = new TreeMap<>(); // Ordenado por código

    public void anadir(Producto p) {
        productos.put(p.getCodigo(), p);
    }

    public Collection<Producto> getProductos() {
        return productos.values();
    }

    // IOException hacia fuera: quien llame decide qué hacer si el disco falla
    public void guardar(Path fichero) throws IOException {
        // BufferedWriter junta las escrituras en memoria y las vuelca de golpe: más rápido
        try (BufferedWriter out = Files.newBufferedWriter(fichero, StandardCharsets.UTF_8)) {
            out.write(CABECERA);
            out.newLine(); // Salto de línea del sistema (\r\n en Windows, \n en Linux)
            for (Producto p : productos.values()) {
                out.write(p.aCsv());
                out.newLine();
            }
        } // Aquí se cierra out y se vacía el búfer. Sin cerrar, el fichero podría quedar a medias
    }

    // Devuelve cuántas líneas descartó
    public int cargar(Path fichero) throws IOException {
        productos.clear(); // Se sustituye el contenido actual por el del fichero
        int descartadas = 0;
        try (BufferedReader in = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
            String linea = in.readLine(); // Primera línea: la cabecera
            if (linea == null) {
                return 0; // Fichero vacío
            }
            int numero = 1;
            while ((linea = in.readLine()) != null) {
                numero++;
                if (linea.trim().isEmpty()) {
                    continue;
                }
                try {
                    anadir(Producto.desdeCsv(linea));
                } catch (LineaCsvException e) {
                    // Una línea mala no impide cargar las demás
                    System.out.println("  Línea " + numero + " descartada: " + e.getMessage());
                    descartadas++;
                }
            }
        }
        return descartadas;
    }

    public double valorTotal() {
        double total = 0;
        for (Producto p : productos.values()) {
            total += p.getPrecio() * p.getUnidades();
        }
        return total;
    }

    public List<Producto> bajoStock(int minimo) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getUnidades() < minimo) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}

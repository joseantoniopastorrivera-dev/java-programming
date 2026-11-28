//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Producto.java con conversión a CSV

package inventario_csv;

import java.util.Locale;

public class Producto {
    public static final String SEPARADOR = ";"; // ; y no , porque en español la coma es el decimal

    private final String codigo;
    private final String nombre;
    private final double precio;
    private final int unidades;

    public Producto(String codigo, String nombre, double precio, int unidades) {
        if (nombre.contains(SEPARADOR)) { // Un ; dentro del nombre rompería las columnas
            throw new IllegalArgumentException("El nombre no puede contener '" + SEPARADOR + "'");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getUnidades() { return unidades; }

    // Locale.ROOT: siempre punto decimal. Con el idioma español saldría "2,50" y parseDouble fallaría al leerlo
    public String aCsv() {
        return String.format(Locale.ROOT, "%s;%s;%.2f;%d", codigo, nombre, precio, unidades);
    }

    // static: crea un Producto a partir de una línea (método "fábrica")
    public static Producto desdeCsv(String linea) throws LineaCsvException {
        String[] campos = linea.split(SEPARADOR, -1); // -1: conserva los campos vacíos del final
        if (campos.length != 4) {
            throw new LineaCsvException("se esperaban 4 campos y hay " + campos.length);
        }
        try {
            double precio = Double.parseDouble(campos[2].trim());
            int unidades = Integer.parseInt(campos[3].trim());
            return new Producto(campos[0].trim(), campos[1].trim(), precio, unidades);
        } catch (NumberFormatException e) {
            throw new LineaCsvException("número no válido (" + e.getMessage() + ")", e);
        }
    }

    // Para comprobar que lo cargado es igual que lo guardado
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto p = (Producto) o;
        // Math.abs(...) < 0.001: los double no se comparan con == por los errores de redondeo
        return codigo.equals(p.codigo) && nombre.equals(p.nombre)
                && Math.abs(precio - p.precio) < 0.001 && unidades == p.unidades;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%-5s %-14s %7.2f € x %3d", codigo, nombre, precio, unidades);
    }
}

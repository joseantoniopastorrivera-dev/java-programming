//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase abstracta Empleado.java

package nominas_ampliado;

// Una clase hereda de una sola clase, pero puede implementar varias interfaces
public abstract class Empleado implements Tributable, Comparable<Empleado> {
    protected final String nombre;
    protected final String dni;

    public Empleado(String nombre, String dni) {
        if (dni == null || dni.trim().isEmpty()) { // trim().isEmpty(): vale para Java 8 (isBlank es de Java 11)
            throw new IllegalArgumentException("El DNI no puede estar vacío");
        }
        this.nombre = nombre;
        this.dni = dni.trim().toUpperCase();
    }

    // Método protegido de ayuda para que las hijas validen sus importes
    protected static double noNegativo(double valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException(campo + ": no se admiten valores negativos (" + valor + ")");
        }
        return valor;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }

    // Tramos simplificados de ejemplo (no son los oficiales)
    @Override
    public double tipoRetencion() {
        double bruto = salarioBruto(); // Cada hija calcula su bruto: polimorfismo
        if (bruto <= 1500) {
            return 0.10;
        } else if (bruto <= 3000) {
            return 0.15;
        }
        return 0.20;
    }

    // Orden natural: por DNI (String ya sabe compararse alfabéticamente)
    @Override
    public int compareTo(Empleado otro) {
        return this.dni.compareTo(otro.dni);
    }

    @Override
    public String toString() {
        return String.format("%-17s %-10s %-8s bruto %8.2f € | retención %2.0f %% | neto %8.2f €",
                getClass().getSimpleName(), dni, nombre, salarioBruto(), tipoRetencion() * 100, salarioNeto());
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase EmpleadoComercial.java

package nominas_ampliado;

public class EmpleadoComercial extends Empleado {
    private final double base;
    private final double ventas;     // Importe vendido en el mes
    private final double porcentaje; // Comisión de 0 a 1

    public EmpleadoComercial(String nombre, String dni, double base, double ventas, double porcentaje) {
        super(nombre, dni);
        this.base = noNegativo(base, "La base");
        this.ventas = noNegativo(ventas, "Las ventas");
        if (porcentaje > 1) { // Evita el error típico de escribir 5 en vez de 0.05
            throw new IllegalArgumentException("La comisión va de 0 a 1 (0.05 = 5 %): " + porcentaje);
        }
        this.porcentaje = noNegativo(porcentaje, "La comisión");
    }

    @Override
    public double salarioBruto() {
        return base + ventas * porcentaje;
    }
}

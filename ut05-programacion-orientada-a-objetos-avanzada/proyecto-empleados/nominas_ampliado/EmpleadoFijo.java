//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase EmpleadoFijo.java

package nominas_ampliado;

public class EmpleadoFijo extends Empleado {
    private final double base;
    private final double plus;

    public EmpleadoFijo(String nombre, String dni, double base, double plus) {
        super(nombre, dni);                    // super() siempre va en la primera línea
        this.base = noNegativo(base, "La base"); // Método heredado del padre
        this.plus = noNegativo(plus, "El plus");
    }

    // Implementa el método abstracto de Tributable
    @Override
    public double salarioBruto() {
        return base + plus;
    }
}

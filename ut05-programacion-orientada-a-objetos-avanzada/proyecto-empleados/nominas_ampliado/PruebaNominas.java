//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaNominas.java

package nominas_ampliado;

import java.util.List;

public class PruebaNominas {

    private static void mostrar(String titulo, List<Empleado> lista) {
        System.out.println("\n--- " + titulo + " ---");
        for (Empleado e : lista) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Empresa empresa = new Empresa("Empresa DAM");
        empresa.contratar(new EmpleadoFijo("Ana", "33333333P", 1800, 200));
        empresa.contratar(new EmpleadoHoras("Luis", "11111111H", 15, 12));      // Menos de 20 h: sin retención
        empresa.contratar(new EmpleadoHoras("Marta", "22222222J", 120, 14));
        empresa.contratar(new EmpleadoComercial("JAPR", "44444444A", 1200, 40000, 0.05));
        empresa.contratar(new EmpleadoFijo("Pedro", "55555555K", 1100, 0));

        mostrar("Plantilla (orden de contratación)", empresa.getPlantilla());
        mostrar("Por sueldo, de mayor a menor (Comparator)", empresa.ordenadosPorSueldo());
        mostrar("Por DNI (Comparable)", empresa.ordenadosPorDni());

        System.out.println("\n--- Resumen ---");
        System.out.printf("Masa salarial bruta: %.2f €%n", empresa.masaSalarial());
        System.out.println("Por tipo: " + empresa.contarPorTipo());
        System.out.println("Mejor pagado: " + empresa.mejorPagado().getNombre());

        // Una variable de tipo interfaz puede guardar cualquier objeto que la implemente
        Tributable t = empresa.mejorPagado();
        System.out.printf("Su neto, visto como Tributable: %.2f €%n", t.salarioNeto());

        System.out.println("\n--- Despido ---");
        System.out.println("¿Despedido 55555555k? " + empresa.despedir("55555555k"));
        System.out.println("¿Despedido 99999999X? " + empresa.despedir("99999999X"));

        System.out.println("\n--- Errores ---");
        try {
            empresa.contratar(new EmpleadoFijo("Copia", "33333333P", 1000, 0)); // DNI repetido
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            new EmpleadoComercial("Mal", "66666666Q", 1000, 5000, 5); // 5 en vez de 0.05
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            new EmpleadoHoras("Neg", "77777777B", -3, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            empresa.getPlantilla().clear(); // La lista es de solo lectura
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: la plantilla no se puede modificar desde fuera");
        }
        try {
            new Empresa("Vacía").mejorPagado();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

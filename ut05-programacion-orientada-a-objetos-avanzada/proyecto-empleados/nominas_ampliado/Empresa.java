//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Empresa.java: gestiona la plantilla

package nominas_ampliado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Empresa {
    private final String nombre;
    private final List<Empleado> plantilla = new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public void contratar(Empleado e) {
        for (Empleado actual : plantilla) {
            if (actual.getDni().equals(e.getDni())) { // equals compara el texto; == compararía referencias
                throw new IllegalArgumentException("Ya hay un empleado con DNI " + e.getDni());
            }
        }
        plantilla.add(e);
    }

    // Iterator: la forma segura de borrar mientras se recorre (con for-each daría ConcurrentModificationException)
    public boolean despedir(String dni) {
        Iterator<Empleado> it = plantilla.iterator();
        while (it.hasNext()) {
            if (it.next().getDni().equalsIgnoreCase(dni)) {
                it.remove(); // Borra el último elemento devuelto por next()
                return true;
            }
        }
        return false;
    }

    public double masaSalarial() {
        double total = 0;
        for (Empleado e : plantilla) {
            total += e.salarioBruto(); // Cada uno usa su versión: polimorfismo
        }
        return total;
    }

    // Copia ordenada: no se toca el orden de la plantilla original
    public List<Empleado> ordenadosPorSueldo() {
        List<Empleado> copia = new ArrayList<>(plantilla);
        // comparingDouble: compara por el bruto; reversed() invierte el orden (de mayor a menor)
        copia.sort(Comparator.comparingDouble(Empleado::salarioBruto).reversed());
        return copia;
    }

    // Orden natural (compareTo de Empleado): por DNI
    public List<Empleado> ordenadosPorDni() {
        List<Empleado> copia = new ArrayList<>(plantilla);
        Collections.sort(copia);
        return copia;
    }

    // Cuenta empleados por clase. TreeMap para que salga en orden alfabético
    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> cuenta = new TreeMap<>();
        for (Empleado e : plantilla) {
            String tipo = e.getClass().getSimpleName();
            cuenta.put(tipo, cuenta.getOrDefault(tipo, 0) + 1);
        }
        return cuenta;
    }

    public Empleado mejorPagado() {
        if (plantilla.isEmpty()) {
            throw new IllegalStateException("La empresa no tiene empleados");
        }
        // Collections.max con un Comparator devuelve el mayor según ese criterio
        return Collections.max(plantilla, Comparator.comparingDouble(Empleado::salarioBruto));
    }

    public List<Empleado> getPlantilla() {
        return Collections.unmodifiableList(plantilla); // Vista de solo lectura: desde fuera no se puede añadir
    }
}

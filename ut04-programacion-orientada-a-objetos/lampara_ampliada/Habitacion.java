//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Habitacion.java: agrupa lámparas

package lampara_ampliada;

import java.util.ArrayList;

public class Habitacion {
    private final String nombre;
    // Composición: la habitación "tiene" lámparas
    private final ArrayList<Lampara> lamparas = new ArrayList<>();

    public Habitacion(String nombre) {
        this.nombre = nombre;
    }

    public void anadir(Lampara l) {
        lamparas.add(l);
    }

    public void encenderTodas() {
        for (Lampara l : lamparas) { // Se delega en cada lámpara: Habitacion no toca sus atributos
            l.encender();
        }
    }

    public void apagarTodas() {
        for (Lampara l : lamparas) {
            l.apagar();
        }
    }

    public void aplicarModo(ModoLuz modo) {
        for (Lampara l : lamparas) {
            l.aplicarModo(modo);
        }
    }

    public void usar(double horas) {
        for (Lampara l : lamparas) {
            l.usar(horas);
        }
    }

    // Suma el consumo de todas
    public double consumoTotal() {
        double total = 0;
        for (Lampara l : lamparas) {
            total += l.getConsumoWh();
        }
        return total;
    }

    public void mostrar() {
        System.out.println("Habitación: " + nombre);
        for (Lampara l : lamparas) {
            System.out.println("  " + l);
        }
    }
}

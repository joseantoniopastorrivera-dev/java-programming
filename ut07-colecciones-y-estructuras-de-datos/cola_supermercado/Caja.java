//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Caja.java con una cola FIFO

package cola_supermercado;

import java.util.ArrayDeque;
import java.util.Queue;

public class Caja {
    private final int numero;
    private final Queue<Cliente> cola = new ArrayDeque<>(); // Queue: interfaz de cola; ArrayDeque, la implementación
    private Cliente atendiendo = null; // El que está en la caja ahora mismo (no está en la cola)
    private int atendidos = 0;
    private long esperaTotal = 0;      // Suma de minutos de espera de los atendidos

    public Caja(int numero) {
        this.numero = numero;
    }

    public void llega(Cliente c) {
        cola.offer(c); // offer: añade al final. Igual que add, pero devuelve false en vez de lanzar si no cabe
    }

    // Gente en la caja: los de la cola más el que se está atendiendo
    public int ocupacion() {
        return cola.size() + (atendiendo == null ? 0 : 1);
    }

    public void avanzarMinuto(int minuto) {
        if (atendiendo == null) {   // Caja libre: entra el siguiente de la cola
            atendiendo = cola.poll(); // poll: saca el primero o devuelve null si está vacía (remove lanzaría excepción)
            if (atendiendo == null) {
                return;             // No hay nadie: la cajera espera
            }
            esperaTotal += minuto - atendiendo.getMinutoLlegada(); // Lo que esperó en la cola
        }
        if (atendiendo.pasarArticulo()) { // Un artículo por minuto
            atendidos++;
            atendiendo = null;      // Termina: el siguiente entra en el próximo minuto
        }
    }

    public int atendidos() {
        return atendidos;
    }

    public double esperaMedia() {
        return atendidos == 0 ? 0 : (double) esperaTotal / atendidos; // El cast evita la división entera
    }

    @Override
    public String toString() {
        // peek: mira el primero sin sacarlo
        return String.format("Caja %d: atendidos %2d | espera media %4.1f min | en cola %d | siguiente: %s",
                numero, atendidos, esperaMedia(), cola.size(), cola.peek() == null ? "nadie" : cola.peek());
    }
}

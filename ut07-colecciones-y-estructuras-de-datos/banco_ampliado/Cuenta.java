//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase abstracta Cuenta.java

package banco_ampliado; // Todas las clases del ejercicio comparten paquete

import java.util.ArrayList; // Lista dinámica para el historial de movimientos

// abstract: no se pueden crear "Cuenta" a secas, solo sus hijas
public abstract class Cuenta {
    protected String numero;    // protected: lo ven las clases hijas
    protected String titular;   // Nombre del cliente
    protected double saldo;     // Dinero actual de la cuenta
    // final: la referencia a la lista no cambia, aunque sí su contenido
    protected final ArrayList<String> movimientos = new ArrayList<>();

    // Constructor común: lo llaman las hijas con super(...)
    public Cuenta(String numero, String titular, double saldoInicial) {
        this.numero = numero;           // this distingue el atributo del parámetro
        this.titular = titular;
        this.saldo = saldoInicial;
        movimientos.add("Apertura: " + String.format("%.2f €", saldoInicial)); // Primer movimiento
    }

    // Ingresar dinero: solo cantidades positivas
    public void ingresar(double cantidad) {
        if (cantidad <= 0) { // Ingresar 0 o un negativo no tiene sentido
            // IllegalArgumentException es no comprobada: indica un error de quien llama
            throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }
        saldo += cantidad; // Suma al saldo
        movimientos.add("Ingreso: +" + String.format("%.2f €", cantidad)); // Lo apunta
    }

    // Retirar dinero: "throws" avisa de que puede lanzar la excepción comprobada
    public void retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad <= 0) { // Misma validación que en ingresar
            throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
        }
        if (cantidad > getDisponible()) { // Se compara con lo disponible, no con el saldo:
                                          // así CuentaCorriente puede sumar su descubierto
            throw new SaldoInsuficienteException(cantidad, getDisponible()); // Corta el método aquí
        }
        saldo -= cantidad; // Solo se llega aquí si había dinero suficiente
        movimientos.add("Retirada: -" + String.format("%.2f €", cantidad)); // Lo apunta
    }

    // Lo que se puede sacar. Por defecto, el saldo; las hijas lo pueden redefinir
    public double getDisponible() {
        return saldo;
    }

    // Cada tipo de cuenta calcula sus intereses a su manera (polimorfismo)
    public abstract double calcularInteres();

    // Getters: el resto de clases leen los datos sin poder cambiarlos
    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // Muestra el historial, un movimiento por línea
    public void mostrarMovimientos() {
        System.out.println("Movimientos de " + numero + ":");
        for (String m : movimientos) { // for-each: recorre la lista sin índices
            System.out.println("  - " + m);
        }
    }

    // toString común: getClass().getSimpleName() escribe el tipo real (CuentaCorriente…)
    @Override
    public String toString() {
        return String.format("%s %s | Titular: %s | Saldo: %.2f €",
                getClass().getSimpleName(), numero, titular, saldo);
    }
}

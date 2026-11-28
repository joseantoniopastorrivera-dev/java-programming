//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase hija CuentaPlazoFijo.java

package banco_ampliado;

public class CuentaPlazoFijo extends Cuenta {
    private static final double INTERES_ANUAL = 0.035; // 3,5 % al año
    private final int anios; // "anios" en vez de "años": la ñ compila, pero da problemas de codificación

    public CuentaPlazoFijo(String numero, String titular, double saldoInicial, int anios) {
        super(numero, titular, saldoInicial); // Parte común
        this.anios = anios;                   // Duración del depósito
    }

    // Interés simple: saldo × interés × años. Devuelve solo los intereses
    @Override
    public double calcularInteres() {
        return saldo * INTERES_ANUAL * anios;
    }

    // Un plazo fijo no deja sacar dinero antes de vencer: se anula la retirada del padre.
    // UnsupportedOperationException es la excepción estándar de Java para "operación no permitida".
    @Override
    public void retirar(double cantidad) {
        // No hace falta "throws SaldoInsuficienteException": una redefinición puede lanzar
        // menos excepciones comprobadas que el método original, nunca más
        throw new UnsupportedOperationException("Un plazo fijo no admite retiradas hasta su vencimiento");
    }

    @Override
    public String toString() {
        return super.toString() + " | Plazo: " + anios + " años";
    }
}

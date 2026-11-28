//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase hija CuentaCorriente.java

package banco_ampliado;

// extends: hereda atributos y métodos de Cuenta
public class CuentaCorriente extends Cuenta {
    private static final double INTERES = 0.015; // 1,5 %. static final: una constante común a todas
    private final double limiteDescubierto;      // Cuánto puede quedar en negativo

    public CuentaCorriente(String numero, String titular, double saldoInicial, double limiteDescubierto) {
        super(numero, titular, saldoInicial);       // Primero se construye la parte de Cuenta
        this.limiteDescubierto = limiteDescubierto; // Después, lo propio de la hija
    }

    // Redefine lo disponible: saldo más descubierto. retirar() de Cuenta llama a este
    // método sin saber qué hija es: eso es polimorfismo (enlace dinámico)
    @Override
    public double getDisponible() {
        return saldo + limiteDescubierto;
    }

    // Devuelve solo los intereses. El ejercicio original devolvía saldo + intereses
    @Override
    public double calcularInteres() {
        if (saldo <= 0) { // En descubierto no se pagan intereses
            return 0;
        }
        return saldo * INTERES;
    }

    // Reutiliza el toString del padre (super.toString()) y le añade el descubierto
    @Override
    public String toString() {
        return super.toString() + String.format(" | Descubierto: %.2f €", limiteDescubierto);
    }
}

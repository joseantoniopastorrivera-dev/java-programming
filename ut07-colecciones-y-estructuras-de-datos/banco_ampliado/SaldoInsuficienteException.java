//Autor: JAPR
//Fecha: 28/Nov/2026
//Excepción propia SaldoInsuficienteException.java

package banco_ampliado; // Mismo paquete que el resto de clases del ejercicio

// Hereda de Exception (no de RuntimeException): es una excepción COMPROBADA,
// así el compilador obliga a quien llame a retirar() a capturarla o declararla.
public class SaldoInsuficienteException extends Exception {

    // Exception implementa Serializable: se declara la versión para evitar el aviso del compilador
    private static final long serialVersionUID = 1L;

    // Recibe lo que se intentó retirar y lo que había disponible
    public SaldoInsuficienteException(double intentado, double disponible) {
        // super(...) guarda el mensaje en Exception; lo recupera getMessage().
        // String.format con %.2f deja dos decimales, como un importe en euros.
        super(String.format("Saldo insuficiente: se intentó retirar %.2f € y solo hay %.2f € disponibles",
                intentado, disponible));
    }
}

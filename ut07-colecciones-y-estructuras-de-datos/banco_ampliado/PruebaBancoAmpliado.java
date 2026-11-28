//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaBancoAmpliado.java

package banco_ampliado;

public class PruebaBancoAmpliado {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco DAM"); // Nombre inventado: no se usa ningún banco real

        // Polimorfismo: variables de tipo Cuenta que guardan objetos de las hijas
        Cuenta cc = new CuentaCorriente("ES01", "JAPR", 1000, 300); // 300 € de descubierto
        Cuenta pf = new CuentaPlazoFijo("ES02", "JAPR", 5000, 5);   // 5 años
        Cuenta cc2 = new CuentaCorriente("ES03", "Ana", 200, 0);    // Sin descubierto

        banco.abrirCuenta(cc);  // Se guardan en el HashMap con su número como clave
        banco.abrirCuenta(pf);
        banco.abrirCuenta(cc2);

        System.out.println("--- 1. Cuentas abiertas ---");
        banco.listar();

        System.out.println("\n--- 2. Ingreso y retirada correctos ---");
        cc.ingresar(250); // 1000 + 250 = 1250
        try { // retirar() declara una excepción comprobada: hay que capturarla
            cc.retirar(1400); // 1250 + 300 de descubierto = 1550 disponibles: cabe
            System.out.printf("Saldo de ES01 tras retirar 1400: %.2f €%n", cc.getSaldo()); // -150
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- 3. Retirada que supera el descubierto ---");
        try {
            cc.retirar(500); // Disponible: -150 + 300 = 150 €: no cabe
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage()); // El mensaje que montó la excepción
        }

        System.out.println("\n--- 4. Ingreso no válido ---");
        try {
            cc.ingresar(-20);
        } catch (IllegalArgumentException e) { // No comprobada: se captura porque queremos verla
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- 5. Retirada en un plazo fijo ---");
        try {
            pf.retirar(100); // CuentaPlazoFijo redefine retirar() y siempre lo rechaza
        } catch (UnsupportedOperationException | SaldoInsuficienteException e) { // multi-catch (Java 7+)
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- 6. Transferencias ---");
        try {
            banco.transferir("ES03", "ES01", 150); // Ana tiene 200: sale bien
            System.out.println("Transferencia de 150 € de ES03 a ES01 hecha");
            banco.transferir("ES03", "ES01", 100); // A Ana le quedan 50: falla
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            banco.transferir("ES03", "ES99", 10); // Cuenta de destino inexistente
        } catch (IllegalArgumentException | SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- 7. Cuenta repetida ---");
        try {
            banco.abrirCuenta(new CuentaCorriente("ES01", "Otro", 10, 0)); // ES01 ya existe
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- 8. Intereses (polimorfismo) ---");
        // Cada objeto ejecuta SU calcularInteres(), aunque la variable sea de tipo Cuenta
        System.out.printf("ES01: %.2f € | ES02: %.2f € | ES03: %.2f €%n",
                cc.calcularInteres(), pf.calcularInteres(), cc2.calcularInteres());

        System.out.println("\n--- 9. Historial y resumen ---");
        cc.mostrarMovimientos();
        cc2.mostrarMovimientos();
        banco.listar();
        System.out.printf("Saldo total del banco: %.2f €%n", banco.saldoTotal());
    }
}

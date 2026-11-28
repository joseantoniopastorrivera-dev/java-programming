//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Banco.java con un HashMap de cuentas

package banco_ampliado;

import java.util.HashMap; // Implementación de Map: busca por clave en tiempo casi constante
import java.util.Map;     // Interfaz: se declara el tipo como Map para no atarse a HashMap

public class Banco {
    private final String nombre;
    // Clave: número de cuenta (String). Valor: la cuenta (Cuenta, cualquiera de sus hijas)
    private final Map<String, Cuenta> cuentas = new HashMap<>();

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    // Abre una cuenta si su número no existe ya
    public void abrirCuenta(Cuenta c) {
        if (cuentas.containsKey(c.getNumero())) { // containsKey mira solo las claves
            // put() sobrescribiría la cuenta antigua sin avisar: por eso se comprueba antes
            throw new IllegalArgumentException("Ya existe la cuenta " + c.getNumero());
        }
        cuentas.put(c.getNumero(), c); // Guarda el par clave → valor
    }

    // Busca por número. get() devuelve null si la clave no está
    public Cuenta buscar(String numero) {
        Cuenta c = cuentas.get(numero);
        if (c == null) { // Se convierte el null en un error claro en vez de un NullPointerException
            throw new IllegalArgumentException("No existe la cuenta " + numero);
        }
        return c;
    }

    // Transferencia: primero se retira y, solo si sale bien, se ingresa
    public void transferir(String origen, String destino, double cantidad) throws SaldoInsuficienteException {
        Cuenta desde = buscar(origen);  // Si alguna no existe, se lanza el error antes de mover dinero
        Cuenta hacia = buscar(destino);
        if (desde == hacia) {           // == compara referencias: es la misma cuenta
            throw new IllegalArgumentException("El origen y el destino no pueden ser la misma cuenta");
        }
        desde.retirar(cantidad); // Si lanza la excepción, la línea siguiente no se ejecuta
        hacia.ingresar(cantidad); // Así el destino nunca recibe dinero que no salió del origen
    }

    // Recorre los valores del mapa. HashMap no garantiza ningún orden
    public void listar() {
        System.out.println("Cuentas de " + nombre + " (" + cuentas.size() + "):");
        for (Cuenta c : cuentas.values()) { // values() da solo los valores, sin las claves
            System.out.println("  " + c);
        }
    }

    // Suma del saldo de todas las cuentas del banco
    public double saldoTotal() {
        double total = 0;
        for (Cuenta c : cuentas.values()) {
            total += c.getSaldo();
        }
        return total;
    }
}

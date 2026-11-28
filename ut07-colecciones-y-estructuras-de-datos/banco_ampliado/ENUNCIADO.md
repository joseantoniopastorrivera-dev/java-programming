# Banco ampliado (UT07, ampliación del ejercicio `banco`)

Ampliación del ejercicio [`banco`](../banco/) (marzo de 2026), que solo creaba cuentas
y calculaba intereses. Basada en estos enunciados públicos:

- [Sistema bancario (Coddy)](https://coddy.tech/learn/es/java/object_oriented_programming/banking_system):
  ingresos y retiradas con una excepción propia de saldo insuficiente y una cuenta corriente con descubierto.
- [`SimpleBank` (Universidad de Verona)](https://www.di.univr.it/documenti/Avviso/all/all640700.java):
  el banco guarda las cuentas en un `HashMap` y las busca por su número.

## Enunciado

1. Crea la excepción comprobada `SaldoInsuficienteException` (hereda de `Exception`). Su
   mensaje dice cuánto se intentó retirar y cuánto había disponible, con dos decimales.
2. La clase abstracta `Cuenta` tiene número, titular, saldo y una lista de movimientos (`ArrayList<String>`):
   - `ingresar(double cantidad)`: suma la cantidad. Si no es positiva, lanza `IllegalArgumentException`.
   - `retirar(double cantidad)`: resta la cantidad. Si supera el disponible, lanza `SaldoInsuficienteException`.
   - `getDisponible()`: lo que se puede retirar. Por defecto es el saldo.
   - Cada operación correcta se apunta en los movimientos.
   - `calcularInteres()` es abstracto.
3. `CuentaCorriente` permite un descubierto (saldo negativo hasta un límite) y paga un 1,5 % de interés.
4. `CuentaPlazoFijo` paga un 3,5 % al año durante N años y **no permite retiradas**
   (lanza `UnsupportedOperationException`).
5. `Banco` guarda las cuentas en un `HashMap<String, Cuenta>` cuya clave es el número de cuenta:
   - `abrirCuenta(Cuenta c)`: no admite números repetidos.
   - `buscar(String numero)`: devuelve la cuenta o lanza `IllegalArgumentException` si no existe.
   - `transferir(String origen, String destino, double cantidad)`: si la retirada falla, el destino no recibe nada.
   - `listar()`: muestra todas las cuentas.
6. `PruebaBancoAmpliado` prueba cada caso, también los que fallan, capturando las excepciones.

## Compilar y ejecutar

Desde `ut07-colecciones-y-estructuras-de-datos/`:

```bash
javac banco_ampliado/*.java
java banco_ampliado.PruebaBancoAmpliado
```

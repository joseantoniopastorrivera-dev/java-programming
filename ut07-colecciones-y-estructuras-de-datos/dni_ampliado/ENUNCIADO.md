# Registro de DNI ampliado (UT07, ampliación de `reto_mapas_dni`)

Ampliación del reto [`reto_mapas_dni`](../reto_mapas_dni/) (marzo de 2026), que guardaba
DNI y nombres en un `HashMap` sin comprobar nada. La validación de la letra sigue el
algoritmo oficial del Ministerio del Interior: la letra es el resto de dividir el número entre 23, buscado en la tabla `TRWAGMYFPDXBNJZSQVHLCKE`.

## Enunciado

1. `DniInvalidoException` (comprobada) con el motivo del error.
2. `ValidadorDni`, con métodos `static`:
   - `calcularLetra(int numero)`.
   - `validar(String dni)`: comprueba el formato (8 cifras y una letra) con una expresión
     regular y que la letra sea la correcta. Acepta minúsculas y espacios alrededor.
     Devuelve el DNI normalizado en mayúsculas o lanza la excepción.
3. `RegistroDni`, con un `TreeMap<String, String>` (DNI → nombre) para que el listado salga
   ordenado:
   - `registrar(dni, nombre)`: valida y **no sobrescribe** un DNI que ya exista (el reto
     original sí sobrescribía). Devuelve `false` si ya estaba.
   - `buscar(dni)`, `eliminar(dni)` y `listar()`.
   - `contarPorLetra()`: devuelve un `Map<Character, Integer>` con cuántos DNI acaban en cada letra.
4. `PruebaDni` prueba DNI válidos, con la letra mal, con el formato mal y repetidos.

## Compilar y ejecutar

Desde `ut07-colecciones-y-estructuras-de-datos/`:

```bash
javac dni_ampliado/*.java
java dni_ampliado.PruebaDni
```

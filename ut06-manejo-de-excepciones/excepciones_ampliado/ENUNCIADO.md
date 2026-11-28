# Excepciones ampliado (UT06, ampliación de `excepciones`)

Ampliación del proyecto [`excepciones`](../excepciones/) (hojas de ejercicios y repaso
final con `Empleado` y `Cargo`). Aquí se practican las partes de la unidad que no salían:
excepciones encadenadas (*cause*), `try-with-resources`, `finally` y una jerarquía propia.

## Enunciado

1. Jerarquía de excepciones comprobadas:
   - `ConfiguracionException` (base), con un constructor que recibe la causa.
   - `ClaveObligatoriaException` (hija): falta una clave obligatoria.
2. `LectorConfiguracion` lee un fichero `clave=valor`:
   - Usa `try-with-resources` con un `BufferedReader` para que el fichero se cierre siempre.
   - Ignora las líneas vacías y los comentarios (`#`).
   - Una línea sin `=` lanza `ConfiguracionException` con el número de línea.
   - Si el fichero no existe, la `IOException` se envuelve en una `ConfiguracionException`
     (excepción encadenada: `getCause()` devuelve la original).
   - `getEntero(clave)` convierte el valor y envuelve el `NumberFormatException`.
   - `getObligatoria(clave)` lanza `ClaveObligatoriaException` si no está.
3. `PruebaExcepciones` crea en una carpeta temporal tres ficheros (uno correcto, uno con una
   línea mal y uno con un número mal), los lee y muestra cada error con su causa. Usa
   `finally` para contar los intentos.

## Compilar y ejecutar

Desde `ut06-manejo-de-excepciones/`:

```bash
javac excepciones_ampliado/*.java
java excepciones_ampliado.PruebaExcepciones
```

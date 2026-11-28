# Nóminas ampliado (UT05, ampliación del proyecto `empleados`)

Ampliación del proyecto Maven [`empleados`](../empleados/) (fijo, por horas y comercial,
con `calcularSalario()` abstracto). Aquí se añaden una interfaz con método `default`, el
orden natural con `Comparable`, ordenaciones alternativas con `Comparator` y una empresa que
gestiona la plantilla. Va en una carpeta aparte sin Maven para compilarla con `javac`.

## Enunciado

1. Interfaz `Tributable`:
   - `double salarioBruto()` (abstracto).
   - `double tipoRetencion()` (abstracto): porcentaje de retención, de 0 a 1.
   - `default double salarioNeto()`: bruto menos la retención. Los métodos `default`
     (Java 8) dan una implementación en la propia interfaz.
2. `Empleado` abstracta, que implementa `Tributable` y `Comparable<Empleado>` (orden natural
   por DNI). El DNI no puede estar vacío. La retención depende del bruto (tramos simplificados,
   **no** son los oficiales): hasta 1.500 € un 10 %, hasta 3.000 € un 15 % y por encima un 20 %.
3. Hijas `EmpleadoFijo` (base + plus), `EmpleadoHoras` (horas × precio) y `EmpleadoComercial`
   (base + porcentaje de las ventas). Ningún importe puede ser negativo.
4. `Empresa` con un `ArrayList<Empleado>`:
   - `contratar` (no admite DNI repetidos) y `despedir(dni)`.
   - `masaSalarial()`: suma de los brutos.
   - `ordenadosPorSueldo()`: copia ordenada de mayor a menor bruto con un `Comparator`.
   - `contarPorTipo()`: un `Map<String, Integer>` con cuántos empleados hay de cada clase.
   - `mejorPagado()`.
5. `PruebaNominas` muestra las nóminas, las ordenaciones y los errores.

## Compilar y ejecutar

Desde `ut05-programacion-orientada-a-objetos-avanzada/proyecto-empleados/`:

```bash
javac nominas_ampliado/*.java
java nominas_ampliado.PruebaNominas
```

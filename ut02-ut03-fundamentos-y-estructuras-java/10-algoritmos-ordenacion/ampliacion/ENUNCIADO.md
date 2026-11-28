# Búsqueda y ordenación ampliado (UT02-UT03)

Ampliación de [`10-algoritmos-ordenacion`](../), que tenía burbuja, inserción, selección y
mezcla. Aquí se añaden los algoritmos que faltaban de la lista clásica y la búsqueda binaria,
que solo funciona sobre un array ordenado.

## Enunciado

1. `QuickSort.ordenar(int[] v)`: ordenación rápida con partición de Lomuto (el pivote es el
   último elemento). Cuenta las comparaciones que hace.
2. `ShellSort.ordenar(int[] v)`: inserción con saltos que se reducen a la mitad. También
   cuenta comparaciones.
3. `BusquedaBinaria`:
   - `buscar(int[] v, int x)`: versión iterativa. Devuelve la posición o -1.
   - `buscarRecursivo(int[] v, int x)`: la misma con recursividad.
   - `pasos`: cuántas vueltas dio, para compararla con una búsqueda lineal.
4. `PruebaAlgoritmos`:
   - Comprueba con 1.000 arrays aleatorios (semilla fija) que los dos métodos ordenan igual que
     `Arrays.sort`.
   - Compara cuántas comparaciones hace cada uno con 10.000 elementos.
   - Busca valores que están y que no están, y muestra los pasos de la binaria frente a la lineal.

## Compilar y ejecutar

Desde esta carpeta:

```bash
javac *.java
java PruebaAlgoritmos
```

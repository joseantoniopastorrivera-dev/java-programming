# Simulación de cajas de supermercado (UT07, colas)

Ampliación de [`reto_listas_linked`](../reto_listas_linked/) y [`reto_iterador`](../reto_iterador/):
se usa una cola FIFO (el primero en llegar es el primero en salir) con `ArrayDeque`, la
implementación de `Queue` que la documentación de Java recomienda frente a `LinkedList`.

## Enunciado

1. `Cliente`: número de llegada, minuto de llegada y número de artículos.
2. `Caja`: una `Queue<Cliente>` (`ArrayDeque`). Atiende a 1 artículo por minuto.
   - `llega(Cliente)` lo pone al final con `offer`.
   - `avanzarMinuto(int minuto)`: pasa un minuto en el cliente que se está atendiendo y, si ha
     terminado, saca al siguiente con `poll`. Guarda la espera de cada cliente atendido.
   - `esperaMedia()` y `atendidos()`.
3. `SimulacionCaja` simula 120 minutos con una semilla fija:
   - Cada minuto llega un cliente con un 55 % de probabilidad, con entre 1 y 8 artículos.
   - El cliente se pone en la caja con menos gente (hay 3 cajas).
   - Al final muestra, por caja, los atendidos, la espera media y los que quedan en cola.

## Compilar y ejecutar

Desde `ut07-colecciones-y-estructuras-de-datos/`:

```bash
javac cola_supermercado/*.java
java cola_supermercado.SimulacionCaja
```

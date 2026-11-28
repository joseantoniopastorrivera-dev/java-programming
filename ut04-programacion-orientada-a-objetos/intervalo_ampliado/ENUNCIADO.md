# Intervalo ampliado (UT04, ampliación del ejercicio `intervalo`)

Ampliación de [`intervalo`](../intervalo/) (febrero de 2026). El original ya tenía
longitud, punto medio, intersección, troceado… pero era mutable, comparaba con un método
propio `iguales` y no se podía ordenar. La segunda parte es el problema clásico de
«fusionar intervalos solapados» (*merge intervals*), muy habitual en entrevistas técnicas.

## Enunciado

1. `Intervalo` **inmutable** (atributos `final`, sin setters): las operaciones devuelven un
   intervalo nuevo.
   - El constructor lanza `IllegalArgumentException` si el mínimo es mayor que el máximo
     (el original solo escribía un mensaje y lo dejaba a 0).
   - `seSolapa(Intervalo otro)` y `union(Intervalo otro)`. La unión solo existe si se solapan.
   - Redefine `equals` y `hashCode` (sustituyen al `iguales` del original).
   - Implementa `Comparable<Intervalo>`: ordena por mínimo y, si empatan, por máximo.
2. `FusionIntervalos.fusionar(List<Intervalo>)`: ordena la lista y une los que se solapan.
   Por ejemplo, `[1,3] [2,6] [8,10] [9,12] [15,18]` da `[1,6] [8,12] [15,18]`.
3. `IntervaloTest` prueba todo lo anterior.

## Compilar y ejecutar

Desde `ut04-programacion-orientada-a-objetos/`:

```bash
javac intervalo_ampliado/*.java
java intervalo_ampliado.IntervaloTest
```

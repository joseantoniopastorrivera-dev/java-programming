# Lámpara ampliada (UT04, ampliación del ejercicio `lampara`)

Ampliación de [`lampara`](../lampara/) (febrero de 2026), que tenía encendido, intensidad y
una conversión de voltaje a intensidad.

## Enunciado

1. Enumerado `ModoLuz` con tres modos: `NORMAL` (100 %), `LECTURA` (70 %) y `NOCHE` (15 %).
   Cada modo guarda su intensidad en un atributo del `enum`.
2. `Lampara`:
   - Atributos: nombre, potencia máxima en vatios, encendida, intensidad (0-100) y energía
     consumida en vatios-hora.
   - `encender()`, `apagar()` y `conmutar()`. Encender una lámpara apagada la deja al 100 %.
   - `subir(int puntos)` y `bajar(int puntos)` sin salirse de 0-100. Bajar a 0 la apaga.
   - `aplicarModo(ModoLuz modo)` cambia la intensidad a la del modo.
   - `usar(double horas)` suma el consumo: potencia × (intensidad / 100) × horas.
     Si está apagada, no consume.
3. `Habitacion`: tiene un `ArrayList<Lampara>` y permite encenderlas o apagarlas todas,
   aplicar un modo a todas y calcular el consumo total.
4. `LamparaTest` prueba todos los métodos.

## Compilar y ejecutar

Desde `ut04-programacion-orientada-a-objetos/`:

```bash
javac lampara_ampliada/*.java
java lampara_ampliada.LamparaTest
```

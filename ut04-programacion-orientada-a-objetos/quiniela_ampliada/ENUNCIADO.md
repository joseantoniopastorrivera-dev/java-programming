# Quiniela ampliada (UT04, ampliación del ejercicio `quiniela`)

Ampliación de [`quiniela`](../quiniela/) (febrero de 2026), que guardaba partidos y
apuestas en arrays de `char` y contaba aciertos. Aquí se añaden los dobles y triples y
las categorías de premio, siguiendo las reglas generales de la quiniela de fútbol
(14 partidos y el «pleno al 15» aparte; en este ejercicio se simplifica a 14 partidos).

## Enunciado

1. Enumerado `Signo` con `UNO`, `EQUIS` y `DOS`, cada uno con su carácter (`'1'`, `'X'`,
   `'2'`) y un método `static desdeCaracter(char c)` que lanza `IllegalArgumentException`
   si el carácter no es válido.
2. `Pronostico`: un partido y el conjunto de signos apostados (`EnumSet<Signo>`). Con un
   signo es sencillo; con dos, doble, y con tres, triple.
3. `Boleto`: 14 pronósticos.
   - `numeroApuestas()`: 2 elevado a los dobles por 3 elevado a los triples.
   - `precio()`: 0,75 € por apuesta.
   - `aciertos(Signo[] resultados)`: un partido se acierta si el resultado está entre sus signos.
   - `categoria(int aciertos)`: 14 aciertos = 1.ª, 13 = 2.ª, 12 = 3.ª, 11 = 4.ª, 10 = 5.ª
     y menos, sin premio.
4. `TestQuinielaAmpliada` rellena un boleto con dobles y triples, genera unos resultados
   con una semilla fija (`new Random(2026)`, para que siempre salgan los mismos) y muestra el
   escrutinio.

## Compilar y ejecutar

Desde `ut04-programacion-orientada-a-objetos/`:

```bash
javac quiniela_ampliada/*.java
java quiniela_ampliada.TestQuinielaAmpliada
```

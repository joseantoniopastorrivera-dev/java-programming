# Biblioteca ampliada (UT07, ampliación del ejercicio `biblioteca`)

Ampliación del ejercicio [`biblioteca`](../biblioteca/) (marzo de 2026), que solo guardaba
libros en un `ArrayList` y los buscaba. Inspirada en el clásico ejercicio de préstamos de
biblioteca de los apuntes de agrupación de objetos de la
[Universidad Complutense](https://www.fdi.ucm.es/profesor/jpavon/poo/1.4.Agrupación%20de%20objetos-Ejercicios.pdf).

## Enunciado

1. `Libro`: título, autor, ISBN y si está prestado. Dos libros son iguales si tienen el mismo
   ISBN (redefine `equals` y `hashCode`).
2. `Socio`: número de socio, nombre y la lista de libros que tiene prestados. Un socio puede
   tener como máximo 3 libros a la vez.
3. Excepciones propias (comprobadas):
   - `LibroNoDisponibleException`: el libro no existe o ya está prestado.
   - `LimitePrestamosException`: el socio ya tiene 3 libros.
4. `Biblioteca`:
   - Guarda los libros en un `HashMap<String, Libro>` (clave: ISBN) y los socios en un
     `HashMap<Integer, Socio>` (clave: número de socio).
   - `prestar(int socio, String isbn)` y `devolver(int socio, String isbn)`.
   - `librosDisponibles()` devuelve un `ArrayList` con los libros que no están prestados,
     ordenados por título.
   - `buscarPorAutor(String autor)` sin distinguir mayúsculas.
5. `PruebaBiblioteca` prueba los préstamos correctos y los que fallan.

## Compilar y ejecutar

Desde `ut07-colecciones-y-estructuras-de-datos/`:

```bash
javac biblioteca_ampliada/*.java
java biblioteca_ampliada.PruebaBiblioteca
```

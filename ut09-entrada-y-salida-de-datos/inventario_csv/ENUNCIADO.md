# Inventario en CSV (UT09, ampliación de los ejercicios de ficheros)

Ampliación de los ejercicios de la unidad (`EscribirFichero01/02`, `LeerFichero02xd`,
`Persona`), que escribían y leían texto línea a línea. Aquí los datos son objetos que se
guardan y se cargan en formato CSV (valores separados por `;`).

## Enunciado

1. `Producto`: código, nombre, precio y unidades.
   - `aCsv()` lo convierte en una línea `codigo;nombre;precio;unidades`. El precio se escribe
     con punto decimal pase lo que pase con el idioma del sistema (`Locale.ROOT`).
   - `static desdeCsv(String linea)` hace lo contrario.
2. `LineaCsvException` (comprobada): una línea no tiene 4 campos o un número no es válido.
3. `Inventario`, con un `TreeMap<String, Producto>` ordenado por código:
   - `guardar(Path)`: escribe una cabecera y un producto por línea con `BufferedWriter` y
     `try-with-resources`.
   - `cargar(Path)`: lee el fichero, se salta la cabecera y las líneas erróneas, y devuelve
     cuántas líneas descartó (mostrando el motivo de cada una).
   - `valorTotal()` y `bajoStock(int minimo)`.
4. `PruebaInventarioCsv` guarda un inventario en `inventario.csv`, le añade a mano dos líneas
   erróneas, lo vuelve a cargar y comprueba que los datos coinciden.

## Compilar y ejecutar

Desde `ut09-entrada-y-salida-de-datos/`:

```bash
javac inventario_csv/*.java
java inventario_csv.PruebaInventarioCsv
```

El CSV se crea en la carpeta temporal del sistema, así que no ensucia el repositorio.

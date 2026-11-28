# Agenda de contactos (UT07, ampliación de `diccionarios` y `arrayList`)

Junta lo practicado en [`arrayList`](../arrayList/) y [`diccionarios`](../diccionarios/):
una agenda donde cada contacto tiene varios teléfonos y varias etiquetas.

## Enunciado

1. `Contacto`: nombre, una `List<String>` de teléfonos y un `Set<String>` de etiquetas
   (`HashSet`: sin repetidos). Un teléfono solo tiene cifras y entre 9 y 12 caracteres, que
   se comprueban con una expresión regular.
2. `Agenda`, con un `HashMap<String, Contacto>` cuya clave es el nombre en minúsculas, para
   que la búsqueda no distinga mayúsculas:
   - `anadir`, `eliminar` y `buscar`.
   - `buscarPorPrefijo(String)`: los contactos cuyo nombre empieza por ese texto, ordenados.
   - `conEtiqueta(String)`: los contactos que tienen una etiqueta.
   - `quienTiene(String telefono)`: búsqueda inversa por teléfono.
   - `etiquetasUsadas()`: la unión de todas las etiquetas en un `TreeSet` (ordenado).
3. `PruebaAgenda` prueba todos los métodos.

## Compilar y ejecutar

Desde `ut07-colecciones-y-estructuras-de-datos/`:

```bash
javac agenda_contactos/*.java
java agenda_contactos.PruebaAgenda
```

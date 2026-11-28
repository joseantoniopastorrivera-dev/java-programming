//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaDni.java

package dni_ampliado;

public class PruebaDni {
    public static void main(String[] args) {
        RegistroDni registro = new RegistroDni();

        // DNI inventados con su letra correcta, otros con errores y uno repetido en minúsculas
        String[][] datos = {             // Array de arrays: cada fila es {dni, nombre}
            {"12345678Z", "JAPR"},
            {"00000000T", "Ana"},
            {" 87654321x ", "Luis"},     // Minúsculas y espacios: se normaliza
            {"12345678A", "Mal letra"},  // La letra correcta es Z
            {"1234567Z", "Corto"},       // Solo 7 cifras
            {"ABCDEFGHZ", "Letras"},     // No son cifras
            {"12345678z", "Repetido"},   // Mismo DNI que el primero
        };

        System.out.println("--- Registro ---");
        for (String[] fila : datos) { // fila[0] es el DNI y fila[1] el nombre
            try {
                boolean nuevo = registro.registrar(fila[0], fila[1]);
                System.out.println((nuevo ? "Registrado: " : "Ya existía: ") + fila[0].trim());
            } catch (DniInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- Listado ordenado (TreeMap) ---");
        registro.listar();

        System.out.println("\n--- Búsqueda y borrado ---");
        try {
            System.out.println("12345678z es de: " + registro.buscar("12345678z"));
            System.out.println("¿Borrado 00000000T? " + registro.eliminar("00000000T"));
            System.out.println("¿Borrado otra vez? " + registro.eliminar("00000000T"));
        } catch (DniInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Recuento por letra ---");
        System.out.println(registro.contarPorLetra()); // El toString de un Map: {X=1, Z=1}
        System.out.println("Total registrados: " + registro.total());
    }
}

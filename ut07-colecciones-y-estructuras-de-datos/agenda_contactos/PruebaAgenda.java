//Autor: JAPR
//Fecha: 28/Nov/2026
//Programa principal PruebaAgenda.java

package agenda_contactos;

public class PruebaAgenda {

    // Método auxiliar para crear contactos en una línea. String... = número variable de argumentos (varargs)
    private static Contacto crear(String nombre, String telefono, String... etiquetas) {
        Contacto c = new Contacto(nombre);
        c.anadirTelefono(telefono);
        for (String e : etiquetas) { // Dentro del método, varargs es un array normal
            c.etiquetar(e);
        }
        return c;
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.anadir(crear("Ana", "600 11 22 33", "clase", "amigos"));
        agenda.anadir(crear("Andrés", "611223344", "trabajo"));
        agenda.anadir(crear("Antonio", "622334455", "Familia", "AMIGOS")); // Etiquetas en minúsculas al guardar
        agenda.anadir(crear("Beatriz", "633445566", "clase"));
        agenda.buscar("ana").anadirTelefono("911223344"); // Segundo teléfono para Ana
        agenda.buscar("ANA").anadirTelefono("600112233"); // Repetido: no se añade

        System.out.println("--- Búsquedas ---");
        System.out.println("buscar(\"aNa\"): " + agenda.buscar("aNa"));
        System.out.println("Empiezan por \"an\": " + agenda.buscarPorPrefijo("an"));
        System.out.println("Etiqueta amigos: " + agenda.conEtiqueta("Amigos"));
        System.out.println("¿Quién tiene el 633 44 55 66? " + agenda.quienTiene("633 44 55 66").getNombre());
        System.out.println("¿Y el 699999999? " + agenda.quienTiene("699999999"));
        System.out.println("Etiquetas usadas: " + agenda.etiquetasUsadas());

        System.out.println("\n--- Errores ---");
        try {
            agenda.anadir(new Contacto("ANA")); // Mismo nombre con otras mayúsculas
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            agenda.buscar("Beatriz").anadirTelefono("12-34");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            agenda.buscar("Ana").getTelefonos().add("000000000"); // Lista de solo lectura
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: los teléfonos solo se añaden con anadirTelefono()");
        }

        System.out.println("\n--- Borrado ---");
        System.out.println("¿Eliminado andrés? " + agenda.eliminar("andrés"));
        System.out.println("Contactos que quedan: " + agenda.tamanio());
    }
}

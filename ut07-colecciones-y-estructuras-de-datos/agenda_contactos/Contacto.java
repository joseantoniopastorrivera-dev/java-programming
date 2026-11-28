//Autor: JAPR
//Fecha: 28/Nov/2026
//Entidad Contacto.java

package agenda_contactos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacto {
    private final String nombre;
    private final List<String> telefonos = new ArrayList<>(); // List: admite orden y repetidos (pero se evitan)
    private final Set<String> etiquetas = new HashSet<>();    // Set: no admite repetidos

    public Contacto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public void anadirTelefono(String telefono) {
        String limpio = telefono.replace(" ", ""); // Quita los espacios: "600 11 22 33" → "600112233"
        if (!limpio.matches("\\d{9,12}")) {        // {9,12}: entre 9 y 12 dígitos
            throw new IllegalArgumentException("Teléfono no válido: " + telefono);
        }
        if (!telefonos.contains(limpio)) { // En una List hay que evitar los repetidos a mano
            telefonos.add(limpio);
        }
    }

    public void etiquetar(String etiqueta) {
        etiquetas.add(etiqueta.toLowerCase()); // Si ya estaba, add no hace nada (devuelve false)
    }

    public boolean tieneTelefono(String telefono) {
        return telefonos.contains(telefono.replace(" ", ""));
    }

    public boolean tieneEtiqueta(String etiqueta) {
        return etiquetas.contains(etiqueta.toLowerCase()); // contains en HashSet: muy rápido
    }

    // Vistas de solo lectura: desde fuera no se puede modificar la lista interna
    public List<String> getTelefonos() { return Collections.unmodifiableList(telefonos); }
    public Set<String> getEtiquetas() { return Collections.unmodifiableSet(etiquetas); }

    @Override
    public String toString() {
        return nombre + " " + telefonos + " " + etiquetas; // Las colecciones ya tienen su toString: [a, b]
    }
}

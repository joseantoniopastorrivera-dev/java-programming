//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Agenda.java

package agenda_contactos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Agenda {
    // Clave en minúsculas: "Ana", "ANA" y "ana" son el mismo contacto
    private final Map<String, Contacto> contactos = new HashMap<>();

    private static String clave(String nombre) {
        return nombre.trim().toLowerCase();
    }

    public void anadir(Contacto c) {
        // putIfAbsent (Java 8): solo inserta si la clave no existe y devuelve el valor que había
        if (contactos.putIfAbsent(clave(c.getNombre()), c) != null) {
            throw new IllegalArgumentException("Ya existe un contacto llamado " + c.getNombre());
        }
    }

    public boolean eliminar(String nombre) {
        return contactos.remove(clave(nombre)) != null;
    }

    public Contacto buscar(String nombre) {
        return contactos.get(clave(nombre)); // null si no existe
    }

    public List<Contacto> buscarPorPrefijo(String prefijo) {
        List<Contacto> resultado = new ArrayList<>();
        String p = clave(prefijo);
        for (Map.Entry<String, Contacto> e : contactos.entrySet()) {
            if (e.getKey().startsWith(p)) { // La clave ya está en minúsculas
                resultado.add(e.getValue());
            }
        }
        // Ordena por nombre sin distinguir mayúsculas
        resultado.sort(Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER));
        return resultado;
    }

    public List<Contacto> conEtiqueta(String etiqueta) {
        List<Contacto> resultado = new ArrayList<>();
        for (Contacto c : contactos.values()) {
            if (c.tieneEtiqueta(etiqueta)) {
                resultado.add(c);
            }
        }
        resultado.sort(Comparator.comparing(Contacto::getNombre));
        return resultado;
    }

    // Búsqueda inversa: el mapa va de nombre a contacto, así que hay que recorrerlo entero
    public Contacto quienTiene(String telefono) {
        for (Contacto c : contactos.values()) {
            if (c.tieneTelefono(telefono)) {
                return c;
            }
        }
        return null;
    }

    public Set<String> etiquetasUsadas() {
        Set<String> todas = new TreeSet<>(); // TreeSet: sin repetidos y ordenado
        for (Contacto c : contactos.values()) {
            todas.addAll(c.getEtiquetas()); // addAll: añade todos los de otra colección (unión)
        }
        return todas;
    }

    public int tamanio() {
        return contactos.size();
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase RegistroDni.java con TreeMap

package dni_ampliado;

import java.util.Map;
import java.util.TreeMap; // Como HashMap, pero mantiene las claves ordenadas

public class RegistroDni {
    // TreeMap ordena por la clave (String: orden alfabético). HashMap no garantiza orden
    private final Map<String, String> registro = new TreeMap<>();

    // Devuelve true si lo registra y false si el DNI ya estaba
    public boolean registrar(String dni, String nombre) throws DniInvalidoException {
        String valido = ValidadorDni.validar(dni); // Si no es válido, la excepción sale de aquí
        if (registro.containsKey(valido)) {
            return false; // No se sobrescribe: el reto original perdía el nombre anterior
        }
        registro.put(valido, nombre);
        return true;
    }

    // Busca por DNI. Se normaliza para que "12345678z" encuentre "12345678Z"
    public String buscar(String dni) throws DniInvalidoException {
        return registro.get(ValidadorDni.validar(dni)); // null si no está
    }

    // remove() devuelve el valor que había, o null si no estaba
    public boolean eliminar(String dni) throws DniInvalidoException {
        return registro.remove(ValidadorDni.validar(dni)) != null;
    }

    // entrySet(): pares clave-valor, para no tener que hacer get() en cada vuelta
    public void listar() {
        for (Map.Entry<String, String> e : registro.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
    }

    // Cuenta cuántos DNI acaban en cada letra. Character e Integer: los mapas no admiten primitivos
    public Map<Character, Integer> contarPorLetra() {
        Map<Character, Integer> cuenta = new TreeMap<>();
        for (String dni : registro.keySet()) {
            char letra = dni.charAt(8);
            // getOrDefault (Java 8): si la letra aún no está, parte de 0
            cuenta.put(letra, cuenta.getOrDefault(letra, 0) + 1);
        }
        return cuenta;
    }

    public int total() {
        return registro.size();
    }
}

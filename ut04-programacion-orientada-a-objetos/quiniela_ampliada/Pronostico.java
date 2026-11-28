//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Pronostico.java: un partido y sus signos

package quiniela_ampliada;

import java.util.EnumSet; // Conjunto especializado para enums: rápido y sin repetidos

public class Pronostico {
    private final String partido;
    private final EnumSet<Signo> signos;

    // "String signos" recibe, por ejemplo, "1X" para un doble
    public Pronostico(String partido, String signos) {
        this.partido = partido;
        this.signos = EnumSet.noneOf(Signo.class); // Conjunto vacío de Signo
        for (char c : signos.toCharArray()) {      // toCharArray: recorre la cadena letra a letra
            this.signos.add(Signo.desdeCaracter(c)); // Un Set ignora los repetidos ("11" = "1")
        }
        if (this.signos.isEmpty()) {
            throw new IllegalArgumentException("El partido " + partido + " no tiene ningún signo");
        }
    }

    // 1 = sencillo, 2 = doble, 3 = triple
    public int numeroSignos() {
        return signos.size();
    }

    // Se acierta si el resultado está entre los signos apostados
    public boolean acierta(Signo resultado) {
        return signos.contains(resultado);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Más eficiente que sumar Strings en un bucle
        for (Signo s : signos) {
            sb.append(s.getCaracter());
        }
        return String.format("%-28s %s", partido, sb); // Alinea los signos en columna
    }
}

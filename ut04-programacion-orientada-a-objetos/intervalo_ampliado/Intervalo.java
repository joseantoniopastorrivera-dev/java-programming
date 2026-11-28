//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Intervalo.java inmutable y comparable

package intervalo_ampliado;

// Comparable<Intervalo>: los intervalos tienen un "orden natural" y Collections.sort los sabe ordenar
public final class Intervalo implements Comparable<Intervalo> { // final: nadie puede heredar y romper la inmutabilidad
    private final double minimo; // final: se asignan una vez en el constructor y no cambian
    private final double maximo;

    public Intervalo(double minimo, double maximo) {
        if (minimo > maximo) { // Mejor fallar al crear que tener un objeto incorrecto
            throw new IllegalArgumentException("El mínimo (" + minimo + ") es mayor que el máximo (" + maximo + ")");
        }
        this.minimo = minimo;
        this.maximo = maximo;
    }

    public double getMinimo() { return minimo; }
    public double getMaximo() { return maximo; }

    public double longitud() {
        return maximo - minimo;
    }

    // Se solapan si cada uno empieza antes de que acabe el otro (los extremos cuentan)
    public boolean seSolapa(Intervalo otro) {
        return this.minimo <= otro.maximo && otro.minimo <= this.maximo;
    }

    // Unión: del menor mínimo al mayor máximo. Si no se tocan, la unión no es un intervalo
    public Intervalo union(Intervalo otro) {
        if (!seSolapa(otro)) {
            throw new IllegalArgumentException(this + " y " + otro + " no se solapan");
        }
        return new Intervalo(Math.min(minimo, otro.minimo), Math.max(maximo, otro.maximo)); // Objeto nuevo
    }

    // Desplazar devuelve uno nuevo: el original no cambia (inmutabilidad)
    public Intervalo desplazado(double cantidad) {
        return new Intervalo(minimo + cantidad, maximo + cantidad);
    }

    // compareTo: negativo si this va antes, 0 si empatan, positivo si va después
    @Override
    public int compareTo(Intervalo otro) {
        int porMinimo = Double.compare(this.minimo, otro.minimo); // Double.compare evita restar doubles
        if (porMinimo != 0) {
            return porMinimo;
        }
        return Double.compare(this.maximo, otro.maximo); // Desempate por el máximo
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intervalo)) return false;
        Intervalo otro = (Intervalo) o;
        // Double.compare también trata bien casos raros como NaN o -0.0
        return Double.compare(minimo, otro.minimo) == 0 && Double.compare(maximo, otro.maximo) == 0;
    }

    @Override
    public int hashCode() {
        // Combina los dos campos. 31 es un primo: reparte mejor los valores
        return 31 * Double.hashCode(minimo) + Double.hashCode(maximo);
    }

    @Override
    public String toString() {
        return "[" + minimo + ", " + maximo + "]";
    }
}

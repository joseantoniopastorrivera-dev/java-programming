//Autor: JAPR
//Fecha: 28/Nov/2026
//Entidad Cliente.java

package cola_supermercado;

public class Cliente {
    private final int numero;
    private final int minutoLlegada;
    private int articulosPendientes; // Baja mientras se le atiende

    public Cliente(int numero, int minutoLlegada, int articulos) {
        this.numero = numero;
        this.minutoLlegada = minutoLlegada;
        this.articulosPendientes = articulos;
    }

    public int getNumero() { return numero; }
    public int getMinutoLlegada() { return minutoLlegada; }

    // Pasa un artículo por la caja. Devuelve true si ya no le quedan
    public boolean pasarArticulo() {
        articulosPendientes--;
        return articulosPendientes <= 0;
    }

    @Override
    public String toString() {
        return "Cliente " + numero + " (llegó en el min " + minutoLlegada + ", quedan " + articulosPendientes + ")";
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase Lampara.java ampliada

package lampara_ampliada;

public class Lampara {
    private static final int MIN = 0;   // Límites de la intensidad como constantes:
    private static final int MAX = 100; // así no hay "números mágicos" por el código

    private final String nombre;
    private final double potenciaMaxima; // Vatios a intensidad 100
    private boolean encendida;
    private int intensidad;
    private double consumoWh;            // Energía acumulada en vatios-hora

    public Lampara(String nombre, double potenciaMaxima) {
        this.nombre = nombre;
        this.potenciaMaxima = potenciaMaxima;
        this.encendida = false; // Empieza apagada, como en el ejercicio original
        this.intensidad = MIN;
        this.consumoWh = 0;
    }

    public void encender() {
        if (!encendida) {      // Solo si estaba apagada: encender dos veces no cambia nada
            encendida = true;
            intensidad = MAX;
        }
    }

    public void apagar() {
        encendida = false;
        intensidad = MIN;
    }

    // Cambia al estado contrario reutilizando los otros dos métodos
    public void conmutar() {
        if (encendida) {
            apagar();
        } else {
            encender();
        }
    }

    // Math.min evita pasarse de 100. Si estaba apagada, subir la enciende
    public void subir(int puntos) {
        intensidad = Math.min(MAX, intensidad + puntos);
        encendida = intensidad > MIN;
    }

    // Math.max evita bajar de 0. Si llega a 0, queda apagada
    public void bajar(int puntos) {
        intensidad = Math.max(MIN, intensidad - puntos);
        encendida = intensidad > MIN;
    }

    // El modo trae su intensidad; aplicar un modo enciende la lámpara
    public void aplicarModo(ModoLuz modo) {
        intensidad = modo.getIntensidad();
        encendida = true;
    }

    // Suma lo consumido en ese tiempo. 100.0 (double) para no hacer división entera
    public void usar(double horas) {
        if (encendida) {
            consumoWh += potenciaMaxima * (intensidad / 100.0) * horas;
        }
    }

    public boolean isEncendida() { return encendida; }
    public int getIntensidad() { return intensidad; }
    public double getConsumoWh() { return consumoWh; }

    @Override
    public String toString() {
        // String.format: %-10s rellena el nombre a 10 caracteres alineado a la izquierda
        return String.format("%-10s Luz: %-3s Intensidad: %3d %% Consumo: %.1f Wh",
                nombre, encendida ? "ON" : "OFF", intensidad, consumoWh); // %% escribe un %
    }
}

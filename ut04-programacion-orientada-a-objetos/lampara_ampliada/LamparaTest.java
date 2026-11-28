//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase LamparaTest.java ampliada

package lampara_ampliada;

public class LamparaTest {
    public static void main(String[] args) {
        System.out.println("--- Una lámpara ---");
        Lampara mesa = new Lampara("Mesa", 10); // 10 W
        System.out.println(mesa);               // Apagada, intensidad 0

        mesa.encender();
        System.out.println("Encendida: " + mesa);
        mesa.bajar(30);
        System.out.println("Bajar 30:  " + mesa);
        mesa.subir(50);                          // 70 + 50 = 120, se queda en 100
        System.out.println("Subir 50:  " + mesa);
        mesa.bajar(200);                         // Baja a 0 y se apaga
        System.out.println("Bajar 200: " + mesa);
        mesa.conmutar();                         // Estaba apagada: se enciende
        System.out.println("Conmutar:  " + mesa);

        System.out.println("\n--- Modos (enum) ---");
        for (ModoLuz modo : ModoLuz.values()) { // values() devuelve todas las constantes del enum
            mesa.aplicarModo(modo);
            System.out.println(modo + " -> " + mesa.getIntensidad() + " %");
        }

        System.out.println("\n--- Consumo ---");
        Lampara techo = new Lampara("Techo", 60);
        techo.encender();
        techo.usar(2);                   // 60 W × 1 × 2 h = 120 Wh
        techo.aplicarModo(ModoLuz.NOCHE);
        techo.usar(4);                   // 60 × 0,15 × 4 = 36 Wh
        techo.apagar();
        techo.usar(10);                  // Apagada: no suma
        System.out.println(techo);       // 156 Wh

        System.out.println("\n--- Habitación ---");
        Habitacion salon = new Habitacion("Salón");
        salon.anadir(new Lampara("Techo", 60));
        salon.anadir(new Lampara("Pie", 20));
        salon.anadir(new Lampara("Lectura", 8));
        salon.encenderTodas();
        salon.usar(1);                   // 60 + 20 + 8 = 88 Wh
        salon.aplicarModo(ModoLuz.LECTURA);
        salon.usar(2);                   // 88 × 0,7 × 2 = 123,2 Wh
        salon.mostrar();
        System.out.printf("Consumo total: %.1f Wh%n", salon.consumoTotal()); // 211,2
        salon.apagarTodas();
        salon.mostrar();
    }
}

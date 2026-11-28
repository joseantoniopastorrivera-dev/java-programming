//Autor: JAPR
//Fecha: 28/Nov/2026
//Clase EmpleadoHoras.java

package nominas_ampliado;

public class EmpleadoHoras extends Empleado {
    private final double horas;
    private final double precioHora;

    public EmpleadoHoras(String nombre, String dni, double horas, double precioHora) {
        super(nombre, dni);
        this.horas = noNegativo(horas, "Las horas");
        this.precioHora = noNegativo(precioHora, "El precio por hora");
    }

    @Override
    public double salarioBruto() {
        return horas * precioHora;
    }

    // Un empleado por horas con pocas horas no tiene retención: redefine la del padre
    @Override
    public double tipoRetencion() {
        if (horas < 20) {
            return 0;
        }
        return super.tipoRetencion(); // En el resto de casos, los tramos normales
    }
}

//Autor: JAPR
//Fecha: 28/Nov/2026
//Interfaz Tributable.java

package nominas_ampliado;

// Una interfaz es un contrato: quien la implemente promete tener estos métodos
public interface Tributable {

    // Sin cuerpo: son abstractos y public por defecto
    double salarioBruto();

    double tipoRetencion(); // De 0 a 1 (0,15 = 15 %)

    // default (Java 8): método con cuerpo dentro de la interfaz.
    // Todas las clases que la implementen lo heredan sin escribirlo
    default double salarioNeto() {
        return salarioBruto() * (1 - tipoRetencion()); // Llama a los abstractos: cada clase pone los suyos
    }
}

package org.example;

public class MataVino {
    private String codigo;
    private String calidad;
    private String cepa;
    private double cantidadVinoObtenible;
    private boolean estaListoParaCosechar;

    public MataVino(String codigo, String cepa, String calidad, double cantidadVinoObtenible, boolean estaListoParaCosechar) {
        this.codigo = codigo;
        this.calidad = calidad;
        this.cepa = cepa;
        this.cantidadVinoObtenible = cantidadVinoObtenible;
        this.estaListoParaCosechar = estaListoParaCosechar;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCalidad() {
        return calidad;
    }

    public String getCepa() {
        return cepa;
    }

    public double getCantidadVinoObtenible() {
        return cantidadVinoObtenible;
    }

    public boolean estaListoParaCosechar() {
        return estaListoParaCosechar;
    }
}

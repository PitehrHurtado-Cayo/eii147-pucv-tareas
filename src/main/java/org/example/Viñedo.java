package org.example;

public class Viñedo {
    public static final String nombre = "PITEHR";
    public static final String apellido = "HURTADO";

    private Hectarea [] hectareas;
    private int plibre;

    public Viñedo(int cantidadHectareas) {
        this.hectareas = new Hectarea[cantidadHectareas];
        this.plibre = 0;
    }

    public boolean agregarHectarea(Hectarea obj){
        if (plibre == hectareas.length) {
            return false;
        }
        for(int i = 0; i < plibre; i++){
            if (obj.getCodigo() == hectareas[i].getCodigo()) {
                return false;
            }
        }
        hectareas[plibre] = obj;
        plibre++;
        return true;
    }

    public boolean plantarMataVino(int idHectarea, MataVino obj){
        // validar que se no se encuentre
        for (int i = 0; i < plibre; i++) {
            if (hectareas[i].existeMataVino(obj.getCodigo())) {
                return false;
            }
        }
        // validar que exista la hectarea
        for(int i = 0; i < plibre; i++){
            if (idHectarea == hectareas[i].getCodigo()) {
                return hectareas[i].plantarMataVino(obj);
            }
        }
        return false;
    }

    public double cosecharVino( String cepaACosechar){
        double cantidadVinoObtenible = 0.0;
        for(int i = 0; i < plibre; i++){
            cantidadVinoObtenible += hectareas[i].cosecharVino(cepaACosechar);
        }
        return cantidadVinoObtenible;
    }


}

package org.example;

import javax.print.DocFlavor;

public class Hectarea {

    public static final String nombre = "PITEHR";
    public static final String apellido = "HURTADO";

    private int codigo;
    private String cepa;
    private MataVino [][] mataVinos;

    public Hectarea(int codigo, String cepa) {
        this.codigo = codigo;
        this.cepa = cepa;
        this.mataVinos = new MataVino[100][100];
    }

    public boolean existeMataVino(String codigo) {
        for(int i = 0; i < mataVinos.length; i++){
            for(int j = 0; j < mataVinos[i].length; j++){
                if (mataVinos[i][j]!=null && codigo.equals(mataVinos[i][j].getCodigo())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean plantarMataVino(MataVino mataVino) {
        if (!cepa.equals(mataVino.getCepa())) {
            return false;
        }
        for (int i = 0; i < mataVinos.length; i++) {
            for (int j = 0; j < mataVinos[i].length; j++) {
                if (mataVinos[i][j] == null) {
                    mataVinos[i][j] = mataVino;
                    return true;
                }
            }
        }
        return false;
    }

    public double cosecharVino(String cepaACosechar){
        if (!cepa.equals(cepaACosechar)) {
            return 0;
        }
        double cantidadVinoObtenible = 0;
        for(int i = 0; i < mataVinos.length; i++){
            for(int j = 0; j < mataVinos[i].length; j++){
                if(mataVinos[i][j] != null && mataVinos[i][j].estaListoParaCosechar()){
                    cantidadVinoObtenible += mataVinos[i][j].getCantidadVinoObtenible();
                    mataVinos[i][j] = null;
                }
            }
        }
        return cantidadVinoObtenible;
    }

    public int getCodigo() {
        return codigo;
    }
}

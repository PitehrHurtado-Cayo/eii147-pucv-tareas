package org.example;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;



public class HectareaTest {

    private static final double puntaje_existeMataVino = 1.0;
    private static final double puntaje_plantarMataVino = 1.0;
    private static final double puntaje_cosecharVino = 1.0;

    private double puntaje;
    private Hectarea hectarea;
    private StringBuilder testReport;

    @Before
    public void setUp() {
        testReport = new StringBuilder();
        puntaje = 0;
        setUpHectarea();
    }

    public void setUpHectarea() {
        hectarea = new Hectarea(1, "CepaPrueba");
    }

    public void testExiteMataVinoInexistente(){
        try{
            setUpHectarea();
            testReport.append("testExisteMataVinoInexistente:\n");
            boolean result = hectarea.existeMataVino("CodigoInexistente");
            double puntajeLocal = result==false ? puntaje_existeMataVino : 0;
            testReport.append("\tresultado: " + (!result ? "PASSED" : "FAILED") + " puntaje: " + puntajeLocal +"\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    public void testExisteMataVinoExistente() {
        try{
            setUpHectarea();
            testReport.append("testExisteMataVinoExistente:\n");
            MataVino mataVino = new MataVino("CodigoExistente", "CepaPrueba", "calidad", 5.0, true);
            hectarea.plantarMataVino(mataVino);
            boolean result = hectarea.existeMataVino("CodigoExistente");
            double puntajeLocal = result==true ? puntaje_existeMataVino : 0;
            testReport.append("\tresultado (after planting): " + (result ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal+ "\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    public void testPlantarMataVinoDiferenteCepa() {
        try{
            setUpHectarea();
            testReport.append("testPlantarMataVinoDiferenteCepa:\n");
            MataVino mataVino = new MataVino("CodigoPlantado", "CepaDiferente", "calidad", 5.0, true);
            boolean result = hectarea.plantarMataVino(mataVino);
            double puntajeLocal = !result ? puntaje_plantarMataVino : 0;
            testReport.append("\tresultado: " + (!result ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal+ "\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    public void testPlantarMataVinoMismaCepa(){
        try{
            setUpHectarea();
            testReport.append("testPlantarMataVinoMismaCepa:\n");
            MataVino mataVino = new MataVino("CodigoPlantado", "CepaPrueba", "calidad", 5.0, true);
            boolean result = hectarea.plantarMataVino(mataVino);
            double puntajeLocal = result ? puntaje_plantarMataVino : 0;
            testReport.append("\tresultado: " + (result ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal+ "\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    public void testPlantarMataVino() {
        try {
            setUpHectarea();
            testReport.append("testPlantarMataVino:\n");
            // Prueba cuando la hectárea ya está llena
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    hectarea.plantarMataVino(new MataVino("CodigoLleno" + i + j, "CepaPrueba", "calidad", 5.0, true));
                }
            }
            boolean result = hectarea.plantarMataVino(new MataVino("CodigoLleno", "CepaPrueba", "calidad", 5.0, true));
            double puntajeLocal = !result ? puntaje_plantarMataVino : 0;
            testReport.append("\tresultado (when full): " + (!result ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal+ "\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado (when full): FAILED\n");
            testReport.append("  Exception: " + e.getMessage() + "\n\n");
        }
    }

    public void testCosecharVinoDistintaCepa(){
        try{
            setUpHectarea();
            testReport.append("testCosecharVinoDistintaCepa:\n");
            MataVino mataVino = new MataVino("CodigoPlantado", "CepaDiferente", "calidad", 5.0, true);
            hectarea.plantarMataVino(mataVino);
            double result = hectarea.cosecharVino("CepaPrueba");
            double puntajeLocal = result==0.0 ? puntaje_cosecharVino : 0;
            testReport.append("\tresultado: " + (result == 0.0 ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal+ "\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    public void testCosecharVinoMismaCepaNoPreparado(){
        try{
            setUpHectarea();
            testReport.append("testCosecharVinoMismaCepaNoPreparado:\n");
            MataVino mataVino = new MataVino("CodigoPlantado", "CepaPrueba", "calidad", 5.0, false);
            hectarea.plantarMataVino(mataVino);
            double result = hectarea.cosecharVino("CepaPrueba");
            double puntajeLocal = result==0.0 ? puntaje_cosecharVino : 0;
            testReport.append("\tresultado: " + (result == 0.0 ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal+ "\n\n");
            puntaje += puntajeLocal;
        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    public void testCosecharVinoMismaCepaSoloPreparado() {
        try{
            setUpHectarea();
            testReport.append("testCosecharVinoMismaCepaSoloPreparado:\n");
            MataVino mataVinoListo = new MataVino("CodigoPlantado_1", "CepaPrueba", "calidad", 5.0, true);
            MataVino mataVinoNoListo = new MataVino("CodigoPlantado_2", "CepaPrueba", "calidad", 5.0, false);
            hectarea.plantarMataVino(mataVinoListo);
            hectarea.plantarMataVino(mataVinoNoListo);
            double result = hectarea.cosecharVino("CepaPrueba");

            // validar que solo se coseche el que está listo
            testReport.append("\tvalidar que solo se coseche el que esta listo:\n");
            double puntajeLocal1 = result==5.0 ? puntaje_cosecharVino : 0;
            testReport.append("\t\tresultado: " + (result == 5.0 ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal1+ "\n");
            puntaje += puntajeLocal1;

            // validar que haya eliminado el que se cosechó
            testReport.append("\tvalidar que haya eliminado el que se cosecho:\n");
            boolean result2 = hectarea.existeMataVino("CodigoPlantado_1");
            double puntajeLocal2 = !result2 ? puntaje_cosecharVino : 0;
            testReport.append("\t\tresultado: " + (!result2 ? "PASSED" : "FAILED") + " puntaje: " +puntajeLocal2+ "\n\n");
            puntaje += puntajeLocal2;

        } catch (Exception e) {
            testReport.append("\tresultado: FAILED\n");
            testReport.append("\t\tException: " + e.getMessage() + "\n\n");
        }
    }

    @Test
    public void testGenerarErrores() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("errores.txt"));
        try {
            writer.write("Integrante: "+Hectarea.nombre+" "+Hectarea.apellido+"\n\n");
            testExiteMataVinoInexistente();
            testExisteMataVinoExistente();
            testPlantarMataVinoDiferenteCepa();
            testPlantarMataVinoMismaCepa();
            testPlantarMataVino();
            testCosecharVinoDistintaCepa();
            testCosecharVinoMismaCepaNoPreparado();
            testCosecharVinoMismaCepaSoloPreparado();

            writer.write("HECTAREA:\n\nResultado de las pruebas:\n\n");
            writer.write(testReport.toString());
            writer.write("\n\nPuntaje obtenido: " + puntaje + "\n");
            writer.newLine();
        } finally {
            writer.close();
        }
    }
}


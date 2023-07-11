package pe.edu.utp.tp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GestorDatos {
    private String archivo;
    private String[][] matrizDatos;

    public GestorDatos(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String[][] getMatrizDatos() {
        return matrizDatos;
    }

    public void setMatrizDatos(String[][] matrizDatos) {
        this.matrizDatos = matrizDatos;
    }

    public void leerArchivo(){
        try {
            String linea;
            int numeroLinea = 0;
            BufferedReader archivo = new BufferedReader(new FileReader(getArchivo()));
            linea = archivo.readLine();
            while (linea != null){
                String[] arregloLinea = linea.split(",");
                for (int i=0; i<arregloLinea.length; i++){
                    matrizDatos[numeroLinea][i] = arregloLinea[i]; //Me aparece que matrizDatos es null
                }
                numeroLinea++;
                linea = archivo.readLine();
            }
            for (int fila=0; fila<matrizDatos.length; fila++){
                for (int j=0; j<matrizDatos[fila].length; j++){
                    System.out.println(matrizDatos[fila][j] + " ");
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }catch (IOException e){
            System.out.println("No hay lineas para leer");
        }
    }
}

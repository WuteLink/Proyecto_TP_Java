package pe.edu.utp.tp.data;

import pe.edu.utp.tp.audit.LoggerUtil;
import pe.edu.utp.tp.data.Sismo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static pe.edu.utp.tp.log.Acceso.getCodIngresado;

public class GestorDatos {
    private String archivo;
    private Sismo datos[];
    private  int numeroLinea;

    public GestorDatos(String archivo){
        this.archivo = archivo;
        datos = new Sismo[indiceDatosSismo()];
        leerArchivo();
    }

    public String getArchivo() {
        return archivo;
    }

    public Sismo[] getDatos() {
        return datos;
    }

    //Funcion para leer archivo csv y guardar los datos en un arreglo y mostrar su contenido
    public void leerArchivo(){
        try {
            int indice = 0, contador = 0;
            String linea;
            BufferedReader archivo = new BufferedReader(new FileReader(getArchivo()));
            linea = archivo.readLine();
            while (linea != null){
                if (contador > 0){
                    String[] arregloLinea = linea.split(",");
                    Sismo nuevoSismo = new Sismo();
                    nuevoSismo.setId(Integer.parseInt(arregloLinea[0]));
                    nuevoSismo.setFecha_utc(new SimpleDateFormat("yyyyMMdd").parse(arregloLinea[1]));
                    nuevoSismo.setHora_utc(new Time(new SimpleDateFormat("HHmmss").parse(arregloLinea[2]).getTime()));
                    nuevoSismo.setLatitud(Double.parseDouble(arregloLinea[3]));
                    nuevoSismo.setLongutid(Double.parseDouble(arregloLinea[4]));
                    nuevoSismo.setProfundidad(Integer.parseInt(arregloLinea[5]));
                    nuevoSismo.setMagnitud(Float.parseFloat(arregloLinea[6]));
                    datos[indice++] = nuevoSismo;
                }
                contador++;
                linea = archivo.readLine();
            }
            numeroLinea = indice;

        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
            LoggerUtil.logException("Error de archivo", "archivo no entontrado", getCodIngresado());
        }catch (IOException e){
            System.out.println("No hay lineas para leer");
            LoggerUtil.logException("Error de lectura", "Sin lineas para leer", getCodIngresado());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //Indice para el arreglo de tipo sismo, retorna la cantidad de lineas que tiene el archivo csv
    public int indiceDatosSismo(){
        try {
            int contador = 0;
            String linea;
            BufferedReader archivo = new BufferedReader(new FileReader(getArchivo()));
            linea = archivo.readLine();
            while (linea != null){
                if (contador > 0){
                    String[] arregloLinea = linea.split(",");
                }
                contador++;
                linea = archivo.readLine();
            }
            numeroLinea = contador-1;
        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
            LoggerUtil.logException("Error de archivo", "archivo no entontrado", getCodIngresado());
        }catch (IOException e){
            System.out.println("No hay lineas para leer");
            LoggerUtil.logException("Error de lectura", "Sin lineas para leer", getCodIngresado());
        }
        return numeroLinea;
    }
}

package pe.edu.utp.tp.consult_export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Exportador {
    public static void exportarTabla(String contenidoTabla, String nombreDestino){
        try(FileWriter escritor = new FileWriter(nombreDestino)){
            escritor.write(contenidoTabla);
            System.out.println("Tabla exportada exitosamente al archivo " + nombreDestino);


        }catch (IOException e){
            System.out.println("Error al exportar archivo ");
            e.printStackTrace();
        }
    }
}


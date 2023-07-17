package pe.edu.utp.tp.consult_export;

import java.io.FileWriter;
import java.io.IOException;
import pe.edu.utp.tp.audit.LoggerUtil;
import pe.edu.utp.tp.log.Acceso;



public class Exportador {
    public static void exportarTabla(String contenidoTabla, String nombreDestino) {
        try(FileWriter escritor = new FileWriter(nombreDestino)){
            escritor.write(contenidoTabla);
            System.out.println("Tabla exportada exitosamente al archivo " + nombreDestino);

        }catch (IOException e){

            System.out.println("Error al exportar archivo ");
            System.out.println("Usted está ingresando símbolos no admitidos");
            e.printStackTrace();
            LoggerUtil.logException(Acceso.getCodIngresado(),e);

        }

    }
}


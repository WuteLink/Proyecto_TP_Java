package pe.edu.utp.tp.audit;
import pe.edu.utp.tp.log.Acceso;

import java.io.IOException;
import java.util.logging.*;
import java.util.Date;

public class LoggerUtil {
    private static final String LOG_ARCHIVO = "auditoria.log";
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_ARCHIVO, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);


        } catch (IOException e) {
            System.out.println("Error al configurar el logger");

        }
    }
    public static void logException(String errorTipo, String mensaje, String nombreUsuario) {
        final String fecha = new Date().toString();
        logger.warning(String.format("Fecha: %s Usuario: %s Tipo de error: %s Mensaje de error: %s\n", fecha, nombreUsuario, errorTipo, mensaje));
    }


}

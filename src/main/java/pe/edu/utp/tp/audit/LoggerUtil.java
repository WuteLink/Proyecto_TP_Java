package pe.edu.utp.tp.audit;
import java.io.IOException;
import java.util.logging.*;
import java.util.Date;

public class LoggerUtil {
    private static final String LOG_ARCHIVO = "auditoria.log";
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_ARCHIVO, true);
            SimpleFormatter formatter = new SimpleFormatter() {
                private final String format = "[%1$tF %1$tT] [%2$s] [%3$s] [%4$s] [%5$s] %n";

                @Override
                public synchronized String format(java.util.logging.LogRecord record) {
                    return String.format(format, new Date(record.getMillis()), record.getLevel().getName(), Thread.currentThread().getName(), record.getMessage(), record.getThrown());
                }
            };
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.out.println("Error al configurar el logger");
        }
    }

    public static void logException(String codigoUsuario, Exception exception) {
        logger.log(Level.SEVERE, "[" + codigoUsuario + "] Error: " + exception.getMessage(), exception);
    }
}

package pe.edu.utp.tp;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class AnalizadorSismico {
    private final int AÑO_INICIAL = 1960;
    private final int AÑO_FINAL = 2021;
    //Funciones para sacar las tablas

    public void generarTablaEventosPorAño(Sismo[] datos, int añoIni, int añoFin){
        try{
            if (añoIni<AÑO_INICIAL || añoFin > AÑO_FINAL || añoIni > añoFin){
                System.out.println("Rango de años inválido");

            }
        }catch (Exception e){

        }


        Map<Integer, Integer> eventosPorAño = new HashMap<>();
        for (Sismo sismo: datos) {
            Date fecha = sismo.getFecha_utc();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int año = calendar.get(Calendar.YEAR);

            if (año >= añoIni && año <= añoFin){
                eventosPorAño.put(año, eventosPorAño.getOrDefault(año,0)+1);

            }

        }
        //mostrar la tabla
        System.out.println("TABLA DE EVENTOS SISMICOS POR AÑO ");
        for (int año = añoIni; año<=añoFin;año++){
            int cantidadEventos = eventosPorAño.getOrDefault(año,0);
            System.out.println("AÑO: "+ año + ": "+ cantidadEventos + " eventos");
        }
    }

    public void generarTablasEventosPorMes(Sismo[] datos, int año){

        Map<Integer, Integer> eventosPorMes = new HashMap<>();

        for (Sismo sismo : datos ) {
            Date fecha = sismo.getFecha_utc();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int sismoAño = calendar.get(Calendar.YEAR);
            int sismoMes = calendar.get(Calendar.MONTH)+1;

            if (sismoAño == año){
                eventosPorMes.put(sismoMes, eventosPorMes.getOrDefault(sismoMes,0)+1);

            }
        }
        System.out.println("TABLA DE EVENTOS EN EL AÑO: " + año);
        for (int mes = 1; mes <= 12; mes++){
            int cantidadEventos = eventosPorMes.getOrDefault(mes,0);
            System.out.println("Mes: "+ mes + ": "+ cantidadEventos + " eventos");
        }

    }
}

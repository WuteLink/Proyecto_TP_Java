package pe.edu.utp.tp;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class AnalizadorSismico {
    private final int AÑO_INICIAL = 1960;
    private final int AÑO_FINAL = 2021;
    //Funciones para sacar las tablas


    //Método para generar tabla de eventos por año
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
    //Método para generar tabla de eventos por mes
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
    //Método para generar tabla de eventos pro mes dado un rango de magnitudes
    public void generarTablaEventosPorMesRango(Sismo[] datos, int año, float magIni, float magFin){
        Map<Integer, Integer> eventosPorMes = new HashMap<>();
        for (Sismo sismo: datos) {
            Date fecha= sismo.getFecha_utc();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int sismoAño = calendar.get(Calendar.YEAR);
            int sismoMes = calendar.get(Calendar.MONTH)+1;
            float magnitud = sismo.getMagnitud();

            if (sismoAño == año && magnitud >= magIni && magnitud <= magFin){
                eventosPorMes.put(sismoMes, eventosPorMes.getOrDefault(sismoMes,0)+1);

            }
        }
        //Mostrar tabla
        System.out.println("Tabla de eventos sísmicos por mes en el año " + año + "Según las magnitudes: " +magIni +" - "+ magFin);
        for (int i = 1; i <= 12; i++){
            int cantidadEventos = eventosPorMes.getOrDefault(i,0);
            System.out.println("Mes " + i + ": "+ cantidadEventos+ " eventos");

        }
    }

    public void generarTablaEventosPorHoraenAño(Sismo[] datos, int año){
        Map<Integer, Integer> eventosPorHora  = new HashMap<>();
        for (Sismo sismo: datos) {
            Date fecha= sismo.getFecha_utc();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int sismoAño = calendar.get(Calendar.YEAR);

            if (sismoAño == año ){
                int hora= sismo.getHora_utc().toLocalTime().getHour();
                eventosPorHora.put(hora,eventosPorHora.getOrDefault(hora,0)+1);

            }
        }
        //imprimir tabla de eventos por hora
        System.out.println("TABLA DE EVENTOS POR HORA \n");
        for (int i = 0; i<24; i++){
            int cantidadEventos= eventosPorHora.getOrDefault(i,0);
            System.out.println("Hora "+ i + ":00 - "+ (i+1)+ ":00: "+ cantidadEventos+ " eventos");
        }

    }
}

package pe.edu.utp.tp;
import java.util.*;

public class AnalizadorSismico {
    private final int AÑO_INICIAL;
    private final int AÑO_FINAL;
    private Map<Integer, Integer> tablaeventos;

    public AnalizadorSismico(){
        AÑO_INICIAL = 1960;
        AÑO_FINAL = 2021;
        tablaeventos = new HashMap<>();
    }

    //Funciones para sacar las tablas

    //Método para generar tabla de eventos por año
    public String generarTablaEventosPorAño(Sismo[] datos, int anioIni, int anioFin){
        StringBuilder constructor = new StringBuilder();

        try{
            //Map<Integer, Integer> eventosPorAño = new HashMap<>();

            for (Sismo sismo: datos) {
                Date fecha = sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int año = calendar.get(Calendar.YEAR);

                if (año >= anioIni && año <= anioFin){
                    tablaeventos.put(año, tablaeventos.getOrDefault(año,0)+1);
                }
            }
            //Tabla como cadena de texto
            constructor.append("TABLA EVENTOS SISMICOS POR AÑO").append(System.lineSeparator());
            for (int año = anioIni; año <= anioFin; año++) {
                int cantidadEventos = tablaeventos.getOrDefault(año, 0);
                constructor.append("AÑO: ").append(año).append(": ").append(cantidadEventos).append(" eventos").append(System.lineSeparator());
            }

        }catch (Exception e){
            if (anioIni<AÑO_INICIAL || anioFin > AÑO_FINAL || anioIni > anioFin){
                System.out.println("Rango de años inválido");
            }
        }
        return constructor.toString();
    }

    public void imprimirPantallaPorAnio(int anioIni, int anioFin){
        System.out.println("TABLA DE EVENTOS SISMICOS POR AÑO ");
        for (int año = anioIni; año<=anioFin;año++){
            int cantidadEventos = tablaeventos.getOrDefault(año,0);
            System.out.println("AÑO: "+ año + ": "+ cantidadEventos + " eventos");
        }
    }

    //Método para generar tabla de eventos por mes
    public String generarTablasEventosPorMes(Sismo[] datos, int anio){
        StringBuilder constructor = new StringBuilder();
        //Map<Integer, Integer> eventosPorMes = new HashMap<>();
        try{
            for (Sismo sismo : datos ) {
                Date fecha = sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int sismoAño = calendar.get(Calendar.YEAR);
                int sismoMes = calendar.get(Calendar.MONTH)+1;

                if (sismoAño == anio){
                    tablaeventos.put(sismoMes, tablaeventos.getOrDefault(sismoMes,0)+1);
                }
            }
            //Tabla como cadena de texto
            constructor.append("TABLA DE EVENTOS POR MES EN EL AÑO"+ anio).append(System.lineSeparator());
            for (int mes=1; mes<=12;mes++) {
                int cantidadEventos = tablaeventos.getOrDefault(mes, 0);
                constructor.append("MES: ").append(mes).append(": ").append(cantidadEventos).append(" eventos").append(System.lineSeparator());
            }

        }catch (Exception e){
            if (anio<AÑO_INICIAL || anio > AÑO_FINAL){
                System.out.println("Año fuera del rango");
            }
        }
        return constructor.toString();

    }

    //Imprimir pantalla
    public void imprimirPantallaPorMes(int anio){
        System.out.println("TABLA DE EVENTOS EN EL AÑO: " + anio);
        for (int mes = 1; mes <= 12; mes++){
            int cantidadEventos = tablaeventos.getOrDefault(mes,0);
            System.out.println("Mes: "+ mes + ": "+ cantidadEventos + " eventos");
        }
    }

    //Método para generar tabla de eventos pro mes dado un rango de magnitudes
    public String generarTablaEventosPorMesRango(Sismo[] datos, int anio, float magnitudInicial, float magnitudFinal){
        //Map<Integer, Integer> eventosPorMes = new HashMap<>();
        StringBuilder constructor = new StringBuilder();
        try{
            for (Sismo sismo: datos) {
                Date fecha= sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int sismoAño = calendar.get(Calendar.YEAR);
                int sismoMes = calendar.get(Calendar.MONTH)+1;
                float magnitud = sismo.getMagnitud();

                if (sismoAño == anio && magnitud >= magnitudInicial && magnitud <= magnitudFinal){
                    tablaeventos.put(sismoMes, tablaeventos.getOrDefault(sismoMes,0)+1);
                }


            }

            //Tabla como cadena de texto
            constructor.append("TABLA DE EVENTOS POR MES EN EL AÑO"+ anio + " según las magnitudes: "+ magnitudInicial +"- "+ magnitudFinal).append(System.lineSeparator());
            for (int mes=1; mes<=12;mes++) {
                int cantidadEventos = tablaeventos.getOrDefault(mes, 0);
                constructor.append("MES: ").append(mes).append(": ").append(cantidadEventos).append(" eventos").append(System.lineSeparator());
            }

        }catch (Exception e) {
            if (anio < AÑO_INICIAL || anio > AÑO_FINAL) {
                System.out.println("Año fuera del rango");
            }
        }
        return constructor.toString();

    }

    //Mostrar tabla
    public void imprimirPantallaPorMesRango(int anio, float magnitudInicial, float magnitudFinal){
        System.out.println("Tabla de eventos sísmicos por mes en el año " + anio + " Según las magnitudes: " +magnitudInicial +" - "+ magnitudFinal);
        for (int i = 1; i <= 12; i++){
            int cantidadEventos = tablaeventos.getOrDefault(i,0);
            System.out.println("Mes " + i + ": "+ cantidadEventos+ " eventos");
        }
    }

    public String generarTablaEventosPorHoraenAnio(Sismo[] datos, int anio){
        //Map<Integer, Integer> eventosPorHora  = new HashMap<>();
        StringBuilder constructor = new StringBuilder();
        try{
            for (Sismo sismo: datos) {
                Date fecha= sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int sismoAño = calendar.get(Calendar.YEAR);
                if (sismoAño == anio ){
                    int hora= sismo.getHora_utc().toLocalTime().getHour();
                    tablaeventos.put(hora,tablaeventos.getOrDefault(hora,0)+1);
                }
            }
            //Tabla como cadena de texto
            constructor.append("Tabla de eventos por hora").append(System.lineSeparator());
            for (int i=0; i<=24;i++) {
                int cantidadEventos = tablaeventos.getOrDefault(i, 0);
                constructor.append("Hora: ").append(i).append(":00 -").append(i+1).append(":00 >>>>>>>>>").append(cantidadEventos).append(" eventos").append(System.lineSeparator());
            }

        }catch (Exception e) {
            if (anio < AÑO_INICIAL || anio > AÑO_FINAL) {
                System.out.println("Año fuera del rango");
            }
        }

        return constructor.toString();
    }

    //imprimir tabla de eventos por hora
    public void imprimirPantallaHoraenAnio(){
        System.out.println("TABLA DE EVENTOS POR HORA \n");
        for (int i = 0; i<24; i++){
            int cantidadEventos= tablaeventos.getOrDefault(i,0);
            System.out.println("Hora " + i + ":00 - " + (i+1) + ":00: " + cantidadEventos + " eventos");
        }
    }
}

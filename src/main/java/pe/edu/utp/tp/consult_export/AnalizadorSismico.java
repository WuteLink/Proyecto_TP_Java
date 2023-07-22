package pe.edu.utp.tp.consult_export;
import pe.edu.utp.tp.audit.LoggerUtil;
import pe.edu.utp.tp.data.Sismo;
import pe.edu.utp.tp.log.Acceso;

import java.sql.Time;
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
        double porcentajeTotal = 0;

        try {
            tablaeventos.clear(); // Reiniciar el mapa antes de generar la tabla
            int totalEventos = 0;

            for (Sismo sismo : datos) {
                Date fecha = sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int año = calendar.get(Calendar.YEAR);

                if (año >= anioIni && año <= anioFin) {
                    tablaeventos.put(año, tablaeventos.getOrDefault(año, 0) + 1);
                    totalEventos++;
                }
            }

            // Tabla como cadena de texto
            constructor.append("Tabla de eventos para los años: ").append(anioIni).append("-").append(anioFin).append(System.lineSeparator());
            constructor.append("Nº  AÑO   FREC  PORC").append(System.lineSeparator());
            constructor.append("===============================").append(System.lineSeparator());

            int numero = 1; // Inicializar el número para contar las filas
            for (int año = anioIni; año <= anioFin; año++) {
                int cantidadEventos = tablaeventos.getOrDefault(año, 0);
                double porcentaje = (double) cantidadEventos / totalEventos * 100;
                porcentajeTotal = porcentaje + porcentajeTotal;
                constructor.append(String.format("%02d  %d   %d  %.2f%%", numero, año, cantidadEventos, porcentaje)).append(System.lineSeparator());
                numero++;
            }

            constructor.append("===============================").append(System.lineSeparator());
            //constructor.append("TOTAL ").append("  ").append(totalEventos).append("  100.00%").append(System.lineSeparator());
            constructor.append(String.format("TOTAL  %-9d  %.2f%%", totalEventos, porcentajeTotal)).append(System.lineSeparator());

        } catch (Exception e) {
            if (anioIni < AÑO_INICIAL || anioFin > AÑO_FINAL || anioIni > anioFin) {
                System.out.println("Rango de años inválido");
            }
            LoggerUtil.logException(Acceso.getCodIngresado(), e);
        }
        return constructor.toString();
    }

    //Método para generar tabla de eventos por mes
    public String generarTablasEventosPorMes(Sismo[] datos, int anio){
        StringBuilder constructor = new StringBuilder();
        double porcentajeTotal = 0;

        try {
            tablaeventos.clear(); // Reiniciar el mapa antes de generar la tabla
            int totalEventos = 0;

            for (Sismo sismo : datos) {
                Date fecha = sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int sismoAño = calendar.get(Calendar.YEAR);
                int sismoMes = calendar.get(Calendar.MONTH) + 1;

                if (sismoAño == anio) {
                    tablaeventos.put(sismoMes, tablaeventos.getOrDefault(sismoMes, 0) + 1);
                    totalEventos++;
                }
            }

            // Tabla como cadena de texto

            constructor.append("(en este caso los datos corresponden al año ").append(anio).append(")").append(System.lineSeparator());
            constructor.append("Nº  MES       FREC       PORC").append(System.lineSeparator());
            constructor.append("===============================").append(System.lineSeparator());

            for (int mes = 1; mes <= 12; mes++) {
                int cantidadEventos = tablaeventos.getOrDefault(mes, 0);
                double porcentaje = (double) cantidadEventos / totalEventos * 100;
                porcentajeTotal = porcentaje + porcentajeTotal;
                constructor.append(String.format("%02d  %-9s  %-9d  %.2f%%", mes, obtenerNombreMes(mes), cantidadEventos, porcentaje)).append(System.lineSeparator());
            }

            constructor.append("===============================").append(System.lineSeparator());
            constructor.append(String.format("TOTAL  %-9d  %.2f%%", totalEventos, porcentajeTotal)).append(System.lineSeparator());

        } catch (Exception e) {
            if (anio < AÑO_INICIAL || anio > AÑO_FINAL) {
                System.out.println("Año fuera del rango");
            }
        }
        return constructor.toString();
    }

    //Método para generar tabla de eventos pro mes dado un rango de magnitudes
    public String generarTablaEventosPorMesRango(Sismo[] datos, int anio, float magnitudInicial, float magnitudFinal){
        StringBuilder constructor = new StringBuilder();
        double porcentajeTotal = 0;

        try {
            tablaeventos.clear(); // Reiniciar el mapa antes de generar la tabla
            int totalEventos = 0;

            for (Sismo sismo : datos) {
                Date fecha = sismo.getFecha_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                int sismoAño = calendar.get(Calendar.YEAR);
                int sismoMes = calendar.get(Calendar.MONTH) + 1;
                float magnitud = sismo.getMagnitud();

                if (sismoAño == anio && magnitud >= magnitudInicial && magnitud <= magnitudFinal) {
                    tablaeventos.put(sismoMes, tablaeventos.getOrDefault(sismoMes, 0) + 1);
                    totalEventos++;
                }
            }

            // Tabla como cadena de texto
            constructor.append("Reporte de eventos en el rango de magnitudes ").append(magnitudInicial).append("-").append(magnitudFinal).append(" en el año ").append(anio).append(".").append(System.lineSeparator());
            constructor.append("Nº  MES       FREC       PORC").append(System.lineSeparator());
            constructor.append("===============================").append(System.lineSeparator());

            for (int mes = 1; mes <= 12; mes++) {
                int cantidadEventos = tablaeventos.getOrDefault(mes, 0);
                double porcentaje = (double) cantidadEventos / totalEventos * 100;
                porcentajeTotal = porcentaje + porcentajeTotal;
                constructor.append(String.format("%02d  %-9s  %-9d  %.2f%%", mes, obtenerNombreMes(mes), cantidadEventos, porcentaje)).append(System.lineSeparator());
            }

            constructor.append("===============================").append(System.lineSeparator());
            constructor.append(String.format("TOTAL  %-9d  %.2f%%", totalEventos, porcentajeTotal)).append(System.lineSeparator());

        } catch (Exception e) {
            if (anio < AÑO_INICIAL || anio > AÑO_FINAL) {
                System.out.println("Año fuera del rango");
            }
        }
        return constructor.toString();
    }

    public String generarTablaEventosPorHoraenAnio(Sismo[] datos, int anio) {
        StringBuilder constructor = new StringBuilder();
        double porcentajeTotal = 0;

        try {
            tablaeventos.clear();
            //Map<Integer, Integer> tablaeventosPorAnio = new HashMap<>();

            for (Sismo sismo : datos) {
                Date fecha = sismo.getFecha_utc();
                Time horaa = sismo.getHora_utc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);

                int sismoAño = calendar.get(Calendar.YEAR);

                if (sismoAño == anio) {
                    calendar.setTime(horaa);
                    int hora = calendar.get(Calendar.HOUR_OF_DAY);
                    tablaeventos.put(hora, tablaeventos.getOrDefault(hora, 0) + 1);
                }
            }

            constructor.append("Reporte de eventos por hora en el año ").append(anio).append(".").append(System.lineSeparator());
            constructor.append("Nº  HORA         FREC       PORC").append(System.lineSeparator());
            constructor.append("===============================").append(System.lineSeparator());

            int totalEventosPorAnio = tablaeventos.values().stream().mapToInt(Integer::intValue).sum();

            for (int hora = 0; hora < 24; hora++) {
                int cantidadEventos = tablaeventos.getOrDefault(hora, 0);
                double porcentaje = (double) cantidadEventos / totalEventosPorAnio * 100;
                porcentajeTotal = porcentaje + porcentajeTotal;
                constructor.append(String.format("%2d  %2d:00-%2d:00  %6d       %.2f%%", hora+1 , hora, hora+1, cantidadEventos, porcentaje)).append(System.lineSeparator());
            }

            constructor.append("===================================").append(System.lineSeparator());
            constructor.append(String.format("TOTAL%18d    %7.2f%%", totalEventosPorAnio, porcentajeTotal)).append(System.lineSeparator());

        } catch (Exception e) {
            if (anio < AÑO_INICIAL || anio > AÑO_FINAL) {
                constructor.append("Año fuera del rango").append(System.lineSeparator());
            }
        }
        return constructor.toString();
    }

    private String obtenerNombreMes(int mes) {
        String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
        return meses[mes - 1];
    }
}
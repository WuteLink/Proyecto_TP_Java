package pe.edu.utp.tp;
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
            int año = sismo.getFecha_utc().getYear() + 1900;
            if (año >= añoIni && año <= añoFin){
                eventosPorAño.put(año, eventosPorAño.getOrDefault(año,0)+1);
            }
        }
        //mostrar la tabla
        System.out.println("TABLA DE EVENTOS SISMICOS POR AÑO ");
        for (int año = añoFin; año<=añoFin;año++){
            int cantidadEventos = eventosPorAño.getOrDefault(año,0);
        }
    }
}

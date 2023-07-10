package pe.edu.utp.tp;
import java.awt.print.PrinterGraphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class AnalizadorDatos {
    private String Archivocsv;
    private String csvSeparador;
    private Map<Integer,Integer> eventosPorAnio;
    private Map<Integer ,Integer> eventosPorMes;

    public AnalizadorDatos(String Archivocsv, String csvSeparador){
        this.Archivocsv=Archivocsv;
        this.csvSeparador= csvSeparador;
        this.eventosPorAnio= new HashMap<>();
        this.eventosPorMes = new HashMap<>();

    }

    //Sacar año

    private int obtenerAño(String fecha){
        SimpleDateFormat formato= new SimpleDateFormat("yyyyMMdd");
        try {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(formato.parse(fecha));
            return calendario.get(Calendar.YEAR);

        }catch (ParseException e){
            e.printStackTrace();
            return -1;
        }
    }

    //Sacar mess
    private String obtenerMes(String fecha){
        SimpleDateFormat formato= new SimpleDateFormat("yyyyMMdd");
        try {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(formato.parse(fecha));
            int mes = calendario.get(Calendar.MONTH)+ 1; //SE suma 1 porque los meses van de 0 a 11 con esta clase CALENDAR
            return  String.format("%02d",mes);


        }catch (ParseException e){
            e.printStackTrace();
            return "";
        }
    }

    public void analizarDatosSismicos(){
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(Archivocsv))){
            br.readLine();

            while ((linea = br.readLine())!= null){
                String[] data = linea.split(csvSeparador);
                //Sacar el año y mes de la fecha
                String fecha = data[1];
                int año= obtenerAño(fecha);
                String mes = obtenerMes(fecha);

                //Eventos por año
                eventosPorAnio.put(año,eventosPorAnio.getOrDefault(año,0)+1);
                //Eventos por mes

                eventosPorMes.put(año,eventosPorMes.getOrDefault(año,0)+1);

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void generarTablaEventosPorAño(){
        System.out.println("TABLA DE EVENTOS SISMICOS POR AÑO: ");
        System.out.println("AÑO/T/EVENTOS");
        for (Map.Entry<Integer,Integer> entry : eventosPorAnio.entrySet()){
            int año = entry.getKey();
            int eventos = entry.getValue();
            System.out.println(año+ "\t\t" + eventos);
        }
        System.out.println();
    }
    public void generarTablaEventosPorMes(int añoDeseado) {
        System.out.println("Tabla de eventos sísmicos por mes en el año " + añoDeseado + ":");
        System.out.println("Mes\t\tEventos");
        for (int i = 1; i <= 12; i++) {
            String añoMes = añoDeseado + "-" + String.format("%02d", i);
            int eventos = eventosPorMes.getOrDefault(añoMes, 0);
            System.out.println(i + "\t\t" + eventos);
        }
    }




}

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
    private Map<String ,Integer> eventosPorMes;

    public AnalizadorDatos(String Archivocsv, String csvSeparador){
        this.Archivocsv=Archivocsv;
        this.csvSeparador= csvSeparador;
        this.eventosPorAnio= new HashMap<>();
        this.eventosPorMes = new HashMap<>();
    }

    //Sacar año

    private int obtenerAnio(String fecha){
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
                int anio= obtenerAnio(fecha);
                String mes = obtenerMes(fecha);

                //Eventos por año
                eventosPorAnio.put(anio,eventosPorAnio.getOrDefault(anio,0)+1);
                //Eventos por mes
                String anioMes = anio+ "-"+ mes;
                eventosPorMes.put(anioMes,eventosPorMes.getOrDefault(anioMes,0)+1);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void generarTablaEventosPorAnio(){
        System.out.println("TABLA DE EVENTOS SISMICOS POR AÑO: ");
        System.out.println("AÑO/T/EVENTOS");
        for (Map.Entry<Integer,Integer> entry : eventosPorAnio.entrySet()){
            int anio = entry.getKey();
            int eventos = entry.getValue();
            System.out.println(anio+ "\t\t" + eventos);
        }
        System.out.println();
    }
    public void generarTablaEventosPorMes(int anioDeseado) {
        System.out.println("Tabla de eventos sísmicos por mes en el año " + anioDeseado + ":");
        System.out.println("Mes\t\tEventos");
        for (int i = 1; i <= 12; i++) {
            String anioMes = anioDeseado + "-" + String.format("%02d", i);
            int eventos = eventosPorMes.getOrDefault(anioMes, 0);
            System.out.println(i + "\t\t" + eventos);
        }
    }




}

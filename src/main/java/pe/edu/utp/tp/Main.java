package pe.edu.utp.tp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String Archivocsv = "\\C:\\Users\\USUARIO\\Documents\\College\\3er ciclo\\Taller de programación\\Proyecto docs\\Proyecto TALLER\\Proyecto_TP_Java\\archivo.csv\\";
        String csvSeparador = ",";
        //String rutaArchivo = AnalizadorDatos.class.getResource(Archivocsv).getPath();
        AnalizadorDatos analizador = new AnalizadorDatos(Archivocsv,csvSeparador);

        analizador.analizarDatosSismicos();
        analizador.generarTablaEventosPorAño();

        int añoDeseado = 2022;
        analizador.generarTablaEventosPorMes(añoDeseado);


        String msj = "prueba git";
        //Prueba de modificación

        String txtSubMenu;
        Scanner lector = new Scanner(System.in);
        OpcionPrincipal opcionPrincipal;
        OpcionSecundario opcionSecundario;
        boolean salirMenuPrincipal = false;
        String opcPrincipal = """
                --------------------------------------------------------
                MENU PRINCIPAL
                --------------------------------------------------------
                1. Número de eventos sísmicos por año dado un rango de años.
                2. Número de eventos sísmicos por mes dado un año.
                3. Número de eventos sísmicos por mes dados un rango de magnitudes y un año
                4. Número de eventos sísmicos por cada hora dado un año.
                0. FIN DEL PROGRAMA
                --------------------------------------------------------
                Ingrese opción [1 – 4]""";
        String opcSecundario = """
                --------------------------------------------------------
                %s
                --------------------------------------------------------
                1. Imprimir por pantalla.
                2. Exportar a archivo plano.
                0. Volver al Menú Principal
                --------------------------------------------------------
                Ingrese opción [1-2]
                """;

        System.out.println(opcPrincipal);

        opcionPrincipal = OpcionPrincipal.values()[lector.nextByte()];

        while (salirMenuPrincipal){
            switch (opcionPrincipal){
                case FIN_DE_PROGRAMA:
                    salirMenuPrincipal = true;
                    break;
                case RANGO_DE_ANIOS:
                    txtSubMenu = "MÓDULO 01 – EVENTOS POR RANGO DE AÑOS";
                    System.out.printf(opcSecundario, txtSubMenu);
                    opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                    switch (opcionSecundario){
                        case MENU_PRINCIPAL:
                            salirMenuPrincipal = false;
                            break;
                        case IMPRIMIR_PANTALLA:
                            salirMenuPrincipal = false;
                            break;
                        case EXPORTAR_ARCHIVO:

                            break;
                    }
                    break;
                case MES_POR_ANIO:
                    txtSubMenu = "MÓDULO 02 – EVENTOS POR MES DADO UN AÑO";
                    System.out.printf(opcSecundario,txtSubMenu);
                    opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                    break;
                case MES_POR_RANGO_MAGNITUDES_Y_ANIO:

                    txtSubMenu = "MÓDULO 03 – EVENTOS POR MES DADOS UN RANGO DE MAGNITUDES Y UN AÑO";
                    System.out.printf(opcSecundario,txtSubMenu);
                    opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                    break;
                case HORA_POR_ANIO:

                    txtSubMenu = "MÓDULO 04 – EVENTOS POR MES DADO UN AÑO";
                    System.out.printf(opcSecundario,txtSubMenu);
                    opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                    break;
                default:
                    System.out.println("Opción ingresada no es válida");
            }
        }



/*
        String linea;
        try {
            BufferedReader archivo = new BufferedReader(new FileReader("C:\\Users\\Alvar\\Documents\\Catálogo Sísmico Perú 1960-2021 (DATASET).csv"));
            linea = archivo.readLine();
            while (linea != null){
                System.out.println(linea);
                linea = archivo.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }catch (IOException e){
            System.out.println("No hay lineas para leer");
        }
        */

    }
}
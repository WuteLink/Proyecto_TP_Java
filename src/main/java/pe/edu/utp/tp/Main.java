package pe.edu.utp.tp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        /*
        String Archivocsv = "\\C:\\Users\\USUARIO\\Documents\\College\\3er ciclo\\Taller de programación\\Proyecto docs\\Proyecto TALLER\\Proyecto_TP_Java\\archivo.csv\\";
        String csvSeparador = ",";
        AnalizadorDatos analizador = new AnalizadorDatos(Archivocsv,csvSeparador);

        analizador.analizarDatosSismicos();
        analizador.generarTablaEventosPorAnio();

        int anioDeseado = 2021;
        analizador.generarTablaEventosPorMes(anioDeseado);*/

        GestorDatos gestordatos = new GestorDatos("\\C:\\Users\\USUARIO\\Documents\\College\\3er ciclo\\Taller de programación\\Proyecto docs\\Proyecto TALLER\\Proyecto_TP_Java\\src\\main\\java\\pe\\edu\\utp\\tp\\archivo.csv\\"); //La ruta debe ser relativa porque la ubicación cambia
        Sismo[] datos = gestordatos.getDatos();

        //Prueba metodos
        int añoI= 2000;
        int añoF= 2010;
        AnalizadorSismico analizador= new AnalizadorSismico();
        analizador.generarTablaEventosPorAño(datos,añoI,añoF);
        int año= 2010;
        analizador.generarTablasEventosPorMes(datos,año);

        analizador.generarTablaEventosPorMesRango(datos, año, 3, 4.5f);

        analizador.generarTablaEventosPorHoraenAño(datos, año);

        //Prueba de modificación


        //Definiendo variables
        String txtSubMenu;
        Scanner lector = new Scanner(System.in);
        OpcionPrincipal opcionPrincipal;
        OpcionSecundario opcionSecundario;
        boolean salirMenuPrincipal = true, salirMenuSecundario = true;
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

        //Generando operación repetitiva con while para el menú principal y los submenús
        while (salirMenuPrincipal){
            System.out.println(opcPrincipal);
            opcionPrincipal = OpcionPrincipal.values()[lector.nextByte()];
            switch (opcionPrincipal){
                case FIN_DE_PROGRAMA:
                    salirMenuPrincipal = false;
                    break;
                case RANGO_DE_ANIOS:
                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 01 – EVENTOS POR RANGO DE AÑOS";
                        System.out.printf(opcSecundario, txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:

                                break;
                            case EXPORTAR_ARCHIVO:

                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;
                case MES_POR_ANIO:
                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 02 – EVENTOS POR MES DADO UN AÑO";
                        System.out.printf(opcSecundario,txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:

                                break;
                            case EXPORTAR_ARCHIVO:

                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;
                case MES_POR_RANGO_MAGNITUDES_Y_ANIO:
                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 03 – EVENTOS POR MES DADOS UN RANGO DE MAGNITUDES Y UN AÑO";
                        System.out.printf(opcSecundario,txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:

                                break;
                            case EXPORTAR_ARCHIVO:

                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;
                case HORA_POR_ANIO:
                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 04 – EVENTOS POR MES DADO UN AÑO";
                        System.out.printf(opcSecundario,txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:

                                break;
                            case EXPORTAR_ARCHIVO:

                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;
                default:
                    System.out.println("Opción ingresada no es válida");
            }
        }

        //Haciendo prueba para enviar el archivo csv a un arreglo
        System.out.println(gestordatos.getDatos().length); //Muestra cantidad total de datos en las celdas
        System.out.println(gestordatos.getDatos()[2]); //Muestra la celda 3 del arreglo
        //Prueba de métodos

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
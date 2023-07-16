package pe.edu.utp.tp;

import pe.edu.utp.tp.consult_export.AnalizadorSismico;
import pe.edu.utp.tp.consult_export.Exportador;
import pe.edu.utp.tp.data.GestorDatos;
import pe.edu.utp.tp.data.Sismo;
import pe.edu.utp.tp.log.Acceso;
import pe.edu.utp.tp.opt.OpcionPrincipal;
import pe.edu.utp.tp.opt.OpcionSecundario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GestorDatos gestordatos = new GestorDatos("archivo.csv");
        //GestorDatos gestordatos = new GestorDatos("\\C:\\Users\\USUARIO\\Documents\\College\\3er ciclo\\Taller de programación\\Proyecto docs\\Proyecto TALLER\\Proyecto_TP_Java\\src\\main\\java\\pe\\edu\\utp\\tp\\archivo.csv\\"); //La ruta debe ser relativa porque la ubicación cambia
        Sismo[] datos = gestordatos.getDatos();

        //Acceso al sistema
        Acceso acceso = new Acceso();
        acceso.cargarUsuario();
        acceso.iniciarSesion();

        String txtSubMenu;
        Scanner lector = new Scanner(System.in);
        OpcionPrincipal opcionPrincipal;
        OpcionSecundario opcionSecundario;
        boolean salirMenuPrincipal = true ;
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
            boolean salirMenuSecundario = true;
            System.out.println(opcPrincipal);
            opcionPrincipal = OpcionPrincipal.values()[lector.nextByte()]; //Marca error en esta linea
            lector.nextLine();
            switch (opcionPrincipal){

                case FIN_DE_PROGRAMA:
                    salirMenuPrincipal = false;
                    break;

                case RANGO_DE_ANIOS:
                    int anioIni, anioFin;
                    System.out.println("Ingresar rango de año (límite de años entre 1960 - 2021):");
                    System.out.println("Rango inicial");
                    anioIni = lector.nextInt();
                    lector.nextLine();
                    System.out.println("Rango final");
                    anioFin = lector.nextInt();
                    lector.nextLine();

                    AnalizadorSismico analizadorSismico = new AnalizadorSismico();
                    String tabla1=analizadorSismico.generarTablaEventosPorAño(datos, anioIni, anioFin);

                    while (salirMenuSecundario) {
                        txtSubMenu = "MÓDULO 01 – EVENTOS POR RANGO DE AÑOS";
                        System.out.printf(opcSecundario, txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        lector.nextLine();
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:
                                analizadorSismico.imprimirPantallaPorAnio(anioIni, anioFin);
                                break;
                            case EXPORTAR_ARCHIVO:
                                System.out.println("¿Qué nombre le desea asignar al archivo?(será de formato .txt)");
                                String nombre = lector.nextLine();
                                Exportador.exportarTabla(tabla1,"output/"+ nombre + ".txt");
                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;

                case MES_POR_ANIO:
                    int anio;
                    System.out.println("Ingresar año dentro del rango (1960 - 2021)");
                    anio = lector.nextInt();
                    lector.nextLine();

                    AnalizadorSismico analizadorSismico1 = new AnalizadorSismico();
                    String tabla2=analizadorSismico1.generarTablasEventosPorMes(datos, anio);

                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 02 – EVENTOS POR MES DADO UN AÑO";
                        System.out.printf(opcSecundario,txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:
                                analizadorSismico1.imprimirPantallaPorMes(anio);
                                break;
                            case EXPORTAR_ARCHIVO:
                                System.out.println("¿Qué nombre le desea asignar al archivo?(será de formato .txt)");
                                String nombre = lector.nextLine();
                                Exportador.exportarTabla(tabla2,"output/"+ nombre + ".txt");

                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;

                case MES_POR_RANGO_MAGNITUDES_Y_ANIO:
                    int anioParaMagnitud;
                    float magnitudInicial, magnitudFinal;
                    System.out.println("Ingresar año dentro del rango (1960 - 2021)");
                    anioParaMagnitud = lector.nextInt();
                    lector.nextLine();
                    System.out.println("Ingresar Magnitud inicial");
                    magnitudInicial = lector.nextFloat();
                    lector.nextLine();
                    System.out.println("Ingresar Magnitud final");
                    magnitudFinal = lector.nextFloat();
                    lector.nextLine();

                    AnalizadorSismico analizadorSismico2 = new AnalizadorSismico();
                    String tabla3=analizadorSismico2.generarTablaEventosPorMesRango(datos, anioParaMagnitud, magnitudInicial, magnitudFinal);

                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 03 – EVENTOS POR MES DADOS UN RANGO DE MAGNITUDES Y UN AÑO";
                        System.out.printf(opcSecundario, txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:
                                analizadorSismico2.imprimirPantallaPorMesRango(anioParaMagnitud, magnitudInicial, magnitudFinal);
                                break;
                            case EXPORTAR_ARCHIVO:
                                System.out.println("¿Qué nombre le desea asignar al archivo?(será de formato .txt)");
                                String nombre = lector.nextLine();
                                Exportador.exportarTabla(tabla3,"output/"+ nombre + ".txt");

                                break;
                            default:
                                System.out.println("Opción inválida");
                        }
                    }
                    break;

                case HORA_POR_ANIO:
                    int anioHora;
                    System.out.println("Ingresar año dentro del rango (1960 - 2021)");
                    anioHora = lector.nextInt();
                    lector.nextLine();

                    AnalizadorSismico analizadorSismico3 = new AnalizadorSismico();
                    String tabla4=analizadorSismico3.generarTablaEventosPorHoraenAnio(datos, anioHora);

                    while (salirMenuSecundario){
                        txtSubMenu = "MÓDULO 04 – EVENTOS POR MES DADO UN AÑO";
                        System.out.printf(opcSecundario,txtSubMenu);
                        opcionSecundario = OpcionSecundario.values()[lector.nextByte()];
                        switch (opcionSecundario){
                            case MENU_PRINCIPAL:
                                salirMenuSecundario = false;
                                break;
                            case IMPRIMIR_PANTALLA:
                                analizadorSismico3.imprimirPantallaHoraenAnio();
                                break;
                            case EXPORTAR_ARCHIVO:
                                System.out.println("¿Qué nombre le desea asignar al archivo?(será de formato .txt)");
                                String nombre = lector.nextLine();
                                Exportador.exportarTabla(tabla4,"output/"+ nombre + ".txt");
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
    }
}
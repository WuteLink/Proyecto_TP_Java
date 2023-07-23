package pe.edu.utp.tp.log;
import pe.edu.utp.tp.audit.LoggerUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Acceso {
    private static final int intentosMAX = 3;
    private static final String ARCHIVO = "src/main/java/pe/edu/utp/tp/log/usuarios.txt";
    private List<Usuario> usuarios;
    private static String codIngresado;
    public Acceso(){
        usuarios= new ArrayList<>();

    }

    public void cargarUsuario(){
        try(BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))){
            String linea;
            lector.readLine();
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split("\\s+");
                String codigo = datos[0];
                String contraseña = datos[1];
                Usuario usuario = new Usuario(codigo, contraseña);
                usuarios.add(usuario);
            }
        }catch(IOException e){
            System.out.println("Error al cargar usuarios desde el archivo");

        }
    }
    public void iniciarSesion(){
        Scanner input = new Scanner(System.in);
        int intentos = 0;
        boolean sesionIniciada = false;

        while (intentos< intentosMAX && !sesionIniciada){
            System.out.println("Ingrese su código de usuario: ");
            codIngresado = input.nextLine();
            System.out.println("Ingrese su contraseña: ");
            String contraseña = input.nextLine();


            try{
                for (Usuario usuario:usuarios) {
                    if (usuario.getCodigo().equals(codIngresado)&& usuario.getContraseña().equals(contraseña)){
                        System.out.println("Sesión inciada.... \n!BIENVENIDO!");
                        sesionIniciada= true;
                        break;
                    }
                }

                if (!sesionIniciada){
                    intentos++;
                    int intentosRest = intentosMAX-intentos;
                    if (intentosRest > 0){
                        System.out.println("Contraseña y/o usuario incorrectos. Intentos restantes: "+ intentosRest);

                    }else {
                        System.out.println("Ha alcanzado los intentos máximos.\n Bloqueando el acceso...");
                        System.exit(0);
                        return;

                    }

                }
            }catch (Exception e){
                //LoggerUtil.logException(getCodIngresado(),e);

            }

        }

    }

    public static String getCodIngresado() {
        return codIngresado;
    }
}

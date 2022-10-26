import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    /**
     * Se gestiona la escritura de datos desde el cliente
     * Se lanza un hilo para controlar la recepcion de datos
     * @param args
     */
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost" , 5666)){
            //Para Leer Input
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Para devolver el input
            PrintWriter os = new PrintWriter(socket.getOutputStream(),true);
            //Para pillar el input
            Scanner sc = new Scanner(System.in);
            String input,response;
            String nombre = "null";
            HiloCliente hilocliente = new HiloCliente(socket);
            hilocliente.start();
            //Hilo Cliente parte 2
            do{
                if(nombre.equals("null")){
                    System.out.println("Introduce tu nombre");
                    input = sc.nextLine();
                    nombre = input;
                    os.println(input);
                    if(input.equals("desc")){
                        break;
                    }
                }else{
                    input = sc.nextLine();
                    String msg = ("(" + nombre + "):" + input );
                    os.println(msg);
                    if(input.equals("desc")){
                        break;
                    }
                }
            }while(!input.equals("desc"));
            os.println(nombre + " se ha desconectado");

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

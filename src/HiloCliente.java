import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCliente extends Thread{

    private Socket socket;
    private BufferedReader input;

    /**
     * Constructor del hilo para el cliente
     * @param s Socket de la conexion que se esta usando
     * @throws IOException
     */
    public HiloCliente(Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    /**
     * Funcion run de la clase HiloCliente
     * Se escucha de forma constante el input por parte del servidor,
     * asi se pueden recibir los mensajes aunque el usuario este escribiendo en la ejecución
     * de la clase Cliente
     */
    @Override
    public void run(){
        try{
            while(true){
                String respuesta = input.readLine();
                System.out.println(respuesta);
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error inesperado. Por favor, vuelve a conectarte");
            throw new RuntimeException(e);
        } finally{
            try{
                input.close();
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error inesperado. Por favor, vuelve a conectarte");
                throw new RuntimeException(e);
            }
        }
    }

}

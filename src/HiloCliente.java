import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCliente extends Thread{

    private Socket socket;
    private BufferedReader input;

    public HiloCliente(Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public void run(){
        try{
            while(true){
                String respuesta = input.readLine();
                System.out.println(respuesta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            try{
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
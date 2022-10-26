import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HiloServidor extends Thread{
    private Socket socket;
    private ArrayList<HiloServidor> listaHilos;
    private PrintWriter os;

    public HiloServidor(Socket s, ArrayList<HiloServidor> hilos){
        this.socket = s;
        this.listaHilos = hilos;
    }

    @Override
    public void run(){
        try {
            //Leer Input
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Devolver input al servidor
            os = new PrintWriter(socket.getOutputStream(),true);

            while(true){
                String outputS = is.readLine();
                if(outputS.equals("desc")){
                    break;
                }
                mensajear(outputS);
                System.out.println("Recibido:"+outputS);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mensajear(String outputS){
        for(HiloServidor h: listaHilos){
            h.os.println((outputS));
        }
    }

}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) {

        ArrayList<HiloServidor> listaHilos = new ArrayList<HiloServidor>();
        try(ServerSocket servidor = new ServerSocket(5666)){
            while(true){
                Socket s = servidor.accept();
                HiloServidor hiloServidor = new HiloServidor(s,listaHilos);
                listaHilos.add(hiloServidor);
                hiloServidor.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
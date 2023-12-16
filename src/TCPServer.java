import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

static final int MAX_CLIENTS = 250;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_CLIENTS);
        try{
            Class.forName(JDBC_DRIVER);

            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serwer działa na porcie 12345");

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nawiązano połączenie z klientem "+ clientSocket);
                executor.execute(new ClientHandler(clientSocket));
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }


    }
}

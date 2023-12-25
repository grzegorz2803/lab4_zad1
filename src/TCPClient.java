import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // Nawiązywanie połączenia z serwerem na porcie 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Nawiązano połączenie z serwerem: " + socket);

            // Strumień do odczytu danych
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Odczytywanie danych od serwera
            String receivedData;
            while ((receivedData = in.readLine()) != null) {
                System.out.println( receivedData);
            }

            // Zamknięcie strumienia i gniazda
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Nawiązywanie połączenia z serwerem na porcie 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Nawiązano połączenie z serwerem: " + socket);


            // Strumień do odczytu danych
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Odczytywanie danych od serwera
            String receivedData;
            while ((receivedData = in.readLine()) != null) {
                System.out.println( receivedData);
                if(receivedData.contains("Twój wynik"))
                    break;
                System.out.println("Twoja odpoiwedź: ");
                String answer = sc.nextLine();
                out.println(answer);
            }

            // Zamknięcie strumienia i gniazda
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

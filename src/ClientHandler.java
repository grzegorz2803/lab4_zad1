import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler  implements Runnable{
private final Socket clientSocket;
private Statement statement;
    static final  String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final  String PASS = "";
    ArrayList<Question> questions;
    ArrayList<String> correctAnswers;
    String name;
public ClientHandler(Socket clientSocket, ArrayList<Question> questions){
    this.clientSocket = clientSocket;
    statement=null;
    correctAnswers = new ArrayList<>();
    this.questions = questions;
}
    @Override
    public void run() {
    try {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        String useDatabaseSQL = "USE kolokwium";
        statement = connection.createStatement();
        statement.executeUpdate(useDatabaseSQL);

        String name = "Podaj imię i nazwisko";
        out.println(name);
        name  = in.readLine();
        AtomicInteger result= new AtomicInteger();
        String finalName = name;
        questions.forEach(question -> {
           out.println(question.toString());
            String clientAnswer = null;
            try {
                clientAnswer = in.readLine();
                if(question.isCorrect(clientAnswer))
                    result.getAndIncrement();
                String insertAnswer = "Insert into answer (user, questionID, answer) values (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertAnswer);
                preparedStatement.setString(1, finalName);
                preparedStatement.setInt(2, question.getId());
                preparedStatement.setString(3, clientAnswer);
                preparedStatement.executeUpdate();
            } catch (IOException | SQLException e) {
               e.printStackTrace();
            }

        });

        out.println("Twój wynik: "+result+"/10");
        String insert = "INSERT INTO result (user, result) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, result.get());
        preparedStatement.executeUpdate();
        connection.close();
        in.close();
        out.close();
        clientSocket.close();
    }catch (IOException | SQLException e){
        e.printStackTrace();
    }
    }
}

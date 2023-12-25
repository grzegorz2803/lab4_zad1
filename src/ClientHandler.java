import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ResponseCache;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class ClientHandler  implements Runnable{
private final Socket clientSocket;
private Statement statement;
    static final  String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final  String PASS = "";
    ArrayList<String> answers;
    ArrayList<String> correctAnswers;
    String name;
public ClientHandler(Socket clientSocket){
    this.clientSocket = clientSocket;
    statement=null;
    answers = new ArrayList<>();
    correctAnswers = new ArrayList<>();
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
        String query = "Select * from question";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        String name = "Podaj imię i nazwisko";
        out.println(name);
        name  = in.readLine();
        System.out.println(name);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String question = resultSet.getString("question");
            String answerA = resultSet.getString("answerA");
            String answerB = resultSet.getString("answerB");
            String answerC = resultSet.getString("answerC");
            String answerD = resultSet.getString("answerD");
            String correctAnswer = resultSet.getString("correctAnswer");
            correctAnswers.add(correctAnswer);
            String serverQuestion = question + "\t" +
                    answerA + "\t" +
                    answerB + "\t" +
                    answerC + "\t" +
                    answerD;
            out.println(serverQuestion);
           String clientAnswer = in.readLine();
            answers.add(clientAnswer);

        }
        int result=0;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(correctAnswers.get(i)))
                result++;

        }
        out.println("Twój wynik: "+result+"/10");

        connection.close();
        in.close();
        out.close();
        clientSocket.close();
    }catch (IOException | SQLException e){
        e.printStackTrace();
    }
    }
}

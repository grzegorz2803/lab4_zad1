import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final  String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final  String PASS = "";


static final int MAX_CLIENTS = 250;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_CLIENTS);
        ArrayList<Question> questions = new ArrayList<>();
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String useDatabaseSQL = "USE kolokwium";
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseSQL);
            String query = "Select * from question";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String question = resultSet.getString("question");
                String answerA = resultSet.getString("answerA");
                String answerB = resultSet.getString("answerB");
                String answerC = resultSet.getString("answerC");
                String answerD = resultSet.getString("answerD");
                String correctAnswer = resultSet.getString("correctAnswer");
              Question q = new Question(question,answerA,answerB,answerC,answerD,correctAnswer);
              questions.add(q);
            }


            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serwer działa na porcie 12345");

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nawiązano połączenie z klientem "+ clientSocket);
                executor.execute(new ClientHandler(clientSocket,questions));
            }
        }catch (IOException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }


    }
}

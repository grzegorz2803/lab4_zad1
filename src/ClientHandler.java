import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ResponseCache;
import java.net.Socket;
import java.sql.*;

public class ClientHandler  implements Runnable{
private final Socket clientSocket;
private Statement statement;
    static final  String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final  String PASS = "";
public ClientHandler(Socket clientSocket){
    this.clientSocket = clientSocket;
    statement=null;
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
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String question = resultSet.getString("question");
            String answerA = resultSet.getString("answerA");
            String answerB = resultSet.getString("answerB");
            String answerC = resultSet.getString("answerC");
            String answerD = resultSet.getString("answerD");
            out.println(id+"."+question);
            out.println(answerA);
            out.println(answerB);
            out.println(answerC);
            out.println(answerD);
        }
        connection.close();
        in.close();
        out.close();
        clientSocket.close();
    }catch (IOException | SQLException e){
        e.printStackTrace();
    }
    }
}

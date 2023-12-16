import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseTable {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final  String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final  String PASS = "";

    public static void main(String[] args) {


        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Łączenie z bazą danych ");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS kolokwium";
            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Baza danych utworzona");

            String useDatabaseSQL = "USE kolokwium";
            statement.executeUpdate(useDatabaseSQL);
            System.out.println("Użyto bazy danych kolokwium");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS question " +
                    "(id int not null auto_increment primary key, " +
                    "question varchar(255) not null," +
                    "answerA varchar(255) not null," +
                    "answerB varchar(255) not null," +
                    "answerC varchar(255) not null," +
                    "answerD varchar(255) not null," +
                    "correctAnswer varchar(1) not null)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Stworzono tabelę question");

            createTableSQL = "CREATE TABLE IF NOT EXISTS user " +
                    "(id int not null auto_increment primary key, " +
                    "name varchar(255) not null," +
                    "lastName varchar(255) not null)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Stworzono tabelę user");

            createTableSQL = "CREATE TABLE IF NOT EXISTS result " +
                    "(id int not null auto_increment primary key, " +
                    "userID int  not null," +
                    "result int not null," +
                    "Foreign key (userID) References user(id))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Stworzono tabelę result");

            createTableSQL = "CREATE TABLE IF NOT EXISTS answer " +
                    "(id int not null auto_increment primary key, " +
                    "userID int  not null," +
                    "question varchar(255) not null," +
                    "answer varchar(1) not null," +
                    "foreign key (userID) references user(id))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Stworzono tabelę answer");

            String insert = "Insert into question (question, answerA, answerB, answerC, answerD, correctAnswer) values ('1. Jakie jest stolica Francji?**', 'a) Berlin', 'b) Paryż', 'c) Londyn', 'd) Madryt', 'b')" ;
            statement.executeUpdate(insert);
            insert = "Insert into question (question, answerA, answerB, answerC, answerD, correctAnswer) values ('ASSJakie jest stolica Francji?**', 'a) Berlin', 'b) Paryż', 'c) Londyn', 'd) Madryt', 'b')" ;
            statement.executeUpdate(insert);
            insert = "Insert into question (question, answerA, answerB, answerC, answerD, correctAnswer) values ('ERTRJakie jest stolica Francji?**', 'a) Berlin', 'b) Paryż', 'c) Londyn', 'd) Madryt', 'b')" ;
            statement.executeUpdate(insert);
            insert = "Insert into question (question, answerA, answerB, answerC, answerD, correctAnswer) values ('POLJakie jest stolica Francji?**', 'a) Berlin', 'b) Paryż', 'c) Londyn', 'd) Madryt', 'b')" ;
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try{
                if (statement!=null)statement.close();
                if(connection!=null)connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

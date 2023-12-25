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


            createTableSQL = "CREATE TABLE IF NOT EXISTS result " +
                    "(id int not null auto_increment primary key, " +
                    "user varchar(255) not null," +
                    "result int not null)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Stworzono tabelę result");

            createTableSQL = "CREATE TABLE IF NOT EXISTS answer " +
                    "(id int not null auto_increment primary key, " +
                    "user varchar(255) not null," +
                    "question varchar(255) not null," +
                    "answer varchar(1) not null)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Stworzono tabelę answer");
            String insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('1. Jakie jest stolica Francji?', 'a. Berlin', 'b. Paryż', 'c. Londyn', 'd. Madryt', 'b');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('2. Który pierwiastek chemiczny reprezentowany jest symbolem \"O\"?', 'a. Tlen', 'b. Wodór', 'c. Azot', 'd. Sód', 'a');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('3. Kto napisał \"Romeo i Julia\"?', 'a. Charles Dickens', 'b. William Shakespeare', 'c. Jane Austen', 'd. Fiodor Dostojewski', 'b');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('4. Jaki kolor ma tradycyjnie skrzynka na listy?', 'a. Czerwony', 'b. Zielony', 'c. Niebieski', 'd. Żółty', 'a');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('5. Ile kontynentów jest na świecie?', 'a. 5', 'b. 6', 'c. 7', 'd. 8', 'b');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('6. Która planeta jest znana jako \"Czerwona Planeta\"?', 'a. Wenus', 'b. Mars', 'c. Jowisz', 'd. Saturn', 'b');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('7. Które zwierzę jest symbolem mądrości w wielu kulturach?', 'a. Sowa', 'b. Tygrys', 'c. Żyrafa', 'd. Wąż', 'a');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('8. Jak nazywa się proces, podczas którego roślina przetwarza światło na energię?', 'a. Fotosynteza', 'b. Respiracja', 'c. Fermentacja', 'd. Dekompozycja', 'a');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('9. Które państwo jest największe pod względem powierzchni?', 'a. Rosja', 'b. Stany Zjednoczone', 'c. Kanada', 'd. Australia', 'a');";
            statement.executeUpdate(insert);
            System.out.println("dodano: "+insert);
            insert = "INSERT INTO question (question, answerA, answerB, answerC, answerD, correctAnswer) VALUES ('10. Co oznacza skrót HTML w kontekście informatyki?', 'a. HyperText Markup Language', 'b. HighTech Modern Language', 'c. Hotmail', 'd. HyperTransfer Machine Language', 'a');";
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

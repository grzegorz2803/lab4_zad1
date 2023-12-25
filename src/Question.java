public class Question {
    private  final int id;
    private final String  question;
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final String answerD;
    private final String correctAnswer;

    public Question(int id,String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return question + "\t"+answerA+"\t"+answerB+"\t"+answerC+"\t"+answerD;
    }
    public boolean isCorrect(String answer){
        return answer.equals(correctAnswer);
    }


    public int getId() {
        return id;
    }
}

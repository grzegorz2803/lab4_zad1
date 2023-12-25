public class Question {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;

    public Question(String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
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
}

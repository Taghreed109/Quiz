package tagreed.app.quiz.objects;

public class Question {

    // Question class make questions for user
    private  String question;
    private int question_id;
    private String answer1;
    private String answer2;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int numberOfAnswers;

    public Question(String question, String answer1, String answer2, String choice1, String choice2, String choice3, String choice4, int numberOfAnswers, int question_id) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.numberOfAnswers = numberOfAnswers;
        this.question_id = question_id;
    }

    public Question(String question, String answer1, String choice1, String choice2, String choice3, String choice4, int numberOfAnswers, int question_id) {
        this.question = question;
        this.answer1 = answer1;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.numberOfAnswers = numberOfAnswers;
        this.question_id = question_id;

    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }
    public String getChoice2() {
        return choice2;
    }
    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }
    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }
    public String getChoice4() {
        return choice4;
    }
    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }
}

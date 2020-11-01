package tagreed.app.quiz.objects;

import java.util.ArrayList;

public class Result {

    // Result class have answers , results , score and date  of game to show it in history
    private ArrayList<String> Answers;
    private ArrayList<String> Results;
    private int score;
    private String Date;

    public Result(ArrayList<String> answers, ArrayList<String> results, int score, String date) {
        Answers = answers;
        Results = results;
        this.score = score;
        Date = date;
    }

    public ArrayList<String> getAnswers() {
        return Answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        Answers = answers;
    }

    public ArrayList<String> getResults() {
        return Results;
    }

    public void setResults(ArrayList<String> results) {
        Results = results;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

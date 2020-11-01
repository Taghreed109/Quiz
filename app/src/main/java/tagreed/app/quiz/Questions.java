package tagreed.app.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tagreed.app.quiz.objects.Question;

public class Questions extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextView tv_timer;
    TextView tv_question;
    CheckBox rb_c1;
    CheckBox rb_c2;
    CheckBox rb_c3;
    CheckBox rb_c4;
    Button bt_submit;

    CountDownTimer countDownTimer; // timer count down
    int counter = 0; // counter for count second (30) second for the timer
    int QuestionsNumber = 0; // count 5 questions for the user
    int r_ans = 0; // right answers counter
    int question_id = 0; // question id to know what is the question and its answer
    int numberofAnswers = 0; //
    int checkedNumber = 0; // number of checkboxes that are check, to let user just check only  2 check boxes for the questions need 2 answers


    ArrayList<Question> questions; // array save questions
    // array save the quesions number, because the app show quesions randomly, i dont need to show question twice
    // so i add quesion number to this array list and ensure the next random number of quesion isnt on this arraylist
    ArrayList<Integer> q_numbers;
    ArrayList<String> Answers; // array save user answers for every question
    ArrayList<String> Results; // array save the result of question ( true , false )

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        restartTimer();
        init();
        loadQuestions();

        bt_submit.setOnClickListener(e -> {
            Confirmation();
        });


    }

    void init() {

        tv_timer = findViewById(R.id.timer);
        tv_question = findViewById(R.id.q_text);
        rb_c1 = findViewById(R.id.choice1);
        rb_c2 = findViewById(R.id.choice2);
        rb_c3 = findViewById(R.id.choice3);
        rb_c4 = findViewById(R.id.choice4);
        bt_submit = findViewById(R.id.submit);

        rb_c1.setOnCheckedChangeListener(this);
        rb_c2.setOnCheckedChangeListener(this);
        rb_c3.setOnCheckedChangeListener(this);
        rb_c4.setOnCheckedChangeListener(this);

        Answers = new ArrayList<>();
        Results = new ArrayList<>();
    }


    void Confirmation() {

        // ensure there is one answer checked
        if (!rb_c1.isChecked() && !rb_c2.isChecked() && !rb_c3.isChecked() && !rb_c4.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please Choose one answer at least!", Toast.LENGTH_SHORT).show();
        } else {
            // make confirmation alert dialog
            new AlertDialog.Builder(this).setTitle("Quiz Answer Confirmation")
                    .setMessage("Are you sure from your answer ?")
                    .setPositiveButton("Yes", (dialog, whichButton) -> {
                        NextQuestion();
                    })
                    .setNegativeButton("No", null).show();
        }


    }


    // save question answer and go to the next question
    void NextQuestion() {
        countDownTimer.cancel(); // stop the timer

        SaveAndCheckAnswer(); // save the question with its false or true

        restartTimer(); // make new timer
        QuestionsNumber++;


        if (QuestionsNumber > 4) { // we show 5 question so we stop
            countDownTimer.cancel();
            Intent intent = new Intent(getApplicationContext(), Results.class);
            intent.putExtra("TrueAnswers", r_ans);
            intent.putExtra("answers", Answers);
            intent.putExtra("results", Results);
            intent.putExtra("newdata", true);

            startActivity(intent);
            finish();
        } else {
            rb_c1.setChecked(false);
            rb_c2.setChecked(false);
            rb_c3.setChecked(false);
            rb_c4.setChecked(false);
            checkedNumber = 0;
            getRandomQuestion(); // get new random question
        }

    }

    void SaveAndCheckAnswer() {
        Question question = questions.get(question_id);
        if (question.getNumberOfAnswers() == 1) { // question with only one answer
            if (rb_c1.isChecked()) {
                Answers.add(rb_c1.getText().toString());
                if (rb_c1.getText().toString().equals(question.getAnswer1())) {
                    addAnswer(true);
                } else {
                    addAnswer(false);
                }
            } else if (rb_c2.isChecked()) {
                Answers.add(rb_c2.getText().toString());
                if (rb_c2.getText().toString().equals(question.getAnswer1())) {
                    addAnswer(true);
                } else {
                    addAnswer(false);
                }
            } else if (rb_c3.isChecked()) {
                Answers.add(rb_c3.getText().toString());
                if (rb_c3.getText().toString().equals(question.getAnswer1())) {
                    addAnswer(true);
                } else {
                    addAnswer(false);
                }
            } else if (rb_c4.isChecked()) {
                Answers.add(rb_c4.getText().toString());
                if (rb_c4.getText().toString().equals(question.getAnswer1())) {
                    addAnswer(true);
                } else {
                    addAnswer(false);
                }
            } else {
                // no one is checked
                Answers.add("Empty");
                Results.add("False Answer");
            }
        } else {
            // question with  two answer
            int answers = 0; // number of right answers
            int fanswers = 0;// number of false answers
            boolean empty = false; // if there is no check box checked (time ends before answer)
            String ans = ""; // save the two answers

            if (rb_c1.isChecked()) {
                ans = ans + " || " + rb_c1.getText().toString();
                if (rb_c1.getText().toString().equals(question.getAnswer1()) || rb_c1.getText().toString().equals(question.getAnswer2())) {
                    answers++;
                } else {
                    fanswers++;
                }
            }
            if (rb_c2.isChecked()) {
                ans = ans + " || " + rb_c2.getText().toString();
                if (rb_c2.getText().toString().equals(question.getAnswer1()) || rb_c2.getText().toString().equals(question.getAnswer2())) {
                    answers++;
                } else {
                    fanswers++;
                }
            }
            if (rb_c3.isChecked()) {
                ans = ans + " || " + rb_c3.getText().toString();
                if (rb_c3.getText().toString().equals(question.getAnswer1()) || rb_c3.getText().toString().equals(question.getAnswer2())) {
                    answers++;
                } else {
                    fanswers++;
                }
            }
            if (rb_c4.isChecked()) {
                ans = ans + " || " + rb_c4.getText().toString();
                if (rb_c4.getText().toString().equals(question.getAnswer1()) || rb_c4.getText().toString().equals(question.getAnswer2())) {
                    answers++;
                } else {
                    fanswers++;
                }


            }
            if (!rb_c1.isChecked() && !rb_c2.isChecked() && !rb_c3.isChecked() && !rb_c4.isChecked()) {
                empty = true;
                // no one is checked
                Answers.add("Empty");
            }

            if (!empty) {
                Answers.add(ans);
            }

            if (answers == 2 && fanswers == 0) {
                addAnswer(true);
            } else {
                addAnswer(false);
            }

        }

    }

    void addAnswer(boolean right) {

        if (right) {
            r_ans++;
            Results.add("Right Answer");
        } else {
            Results.add("False Answer");
        }
    }

    void restartTimer() {
        counter = 0;
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText((30 - counter) + "");
                counter++;
            }

            @Override
            public void onFinish() {
                NextQuestion();
            }
        }.start();
    }

    void loadQuestions() {
        // questions are here
        questions = new ArrayList<>();
        q_numbers = new ArrayList<>();
        questions.add(new Question("Question1", "choice1", "choice1", "choice2", "choice3", "choice4", 1, 0));
        questions.add(new Question("Question2", "choice2", "choice1", "choice2", "choice3", "choice4", 1, 1));
        questions.add(new Question("Question3", "choice3", "choice1", "choice2", "choice3", "choice4", 1, 2));
        questions.add(new Question("Question4", "choice4", "choice1", "choice2", "choice3", "choice4", 1, 3));
        questions.add(new Question("Question5", "choice1", "choice2", "choice1", "choice2", "choice3", "choice4", 2, 4));

        getRandomQuestion();

    }

    void getRandomQuestion() {
        // we get random questions here
        int number = (int) (Math.random() * 5);
        while (true) {
            if (!q_numbers.contains(number)) {
                tv_question.setText(questions.get(number).getQuestion());
                rb_c1.setText(questions.get(number).getChoice1());
                rb_c2.setText(questions.get(number).getChoice2());
                rb_c3.setText(questions.get(number).getChoice3());
                rb_c4.setText(questions.get(number).getChoice4());
                question_id = number;
                numberofAnswers = questions.get(number).getNumberOfAnswers();
                Toast.makeText(getApplicationContext(), "Please Choose  " + numberofAnswers + " Answer", Toast.LENGTH_SHORT).show();

                q_numbers.add(number);
                break;
            } else {
                number = (int) (Math.random() * 5);
            }

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            checkedNumber ++;

            if (numberofAnswers == 1) {
                // here i  make only one answer for question

                switch (buttonView.getId()) {
                    case R.id.choice1:
                        rb_c2.setChecked(false);
                        rb_c3.setChecked(false);
                        rb_c4.setChecked(false);

                        rb_c1.setChecked(true);
                        break;
                    case R.id.choice2:
                        rb_c1.setChecked(false);
                        rb_c3.setChecked(false);
                        rb_c4.setChecked(false);

                        rb_c2.setChecked(true);
                        break;
                    case R.id.choice3:
                        rb_c2.setChecked(false);
                        rb_c1.setChecked(false);
                        rb_c4.setChecked(false);

                        rb_c3.setChecked(true);
                        break;
                    case R.id.choice4:
                        rb_c2.setChecked(false);
                        rb_c3.setChecked(false);
                        rb_c1.setChecked(false);

                        rb_c4.setChecked(true);
                        break;
                }
            } else if (checkedNumber > 2) {
                // here i  make only two answer for question
                rb_c2.setChecked(false);
                rb_c3.setChecked(false);
                rb_c1.setChecked(false);
                rb_c4.setChecked(false);

                Toast.makeText(getApplicationContext(), "Please Choose 2 Answers Only", Toast.LENGTH_SHORT).show();
                checkedNumber = 0;
            }
        }

    }
}
package tagreed.app.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tagreed.app.quiz.adapters.ResultAdapter;
import tagreed.app.quiz.objects.Result;

public class Results extends AppCompatActivity {

    TextView tv_score;
    ListView Answers;
    ListView Results;
    Button bt_goMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        init();

        // go back to Main
        bt_goMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }


    void init() {
        tv_score = findViewById(R.id.score);
        Answers = findViewById(R.id.answers);
        Results = findViewById(R.id.results);
        bt_goMain = findViewById(R.id.b_menu);


        // get results and show them
        int score = getIntent().getIntExtra("TrueAnswers", 0);
        boolean newdata = getIntent().getBooleanExtra("newdata", false);
        ArrayList<String> arr_Answers = getIntent().getStringArrayListExtra("answers");
        ArrayList<String> arr_Result = getIntent().getStringArrayListExtra("results");

        if (newdata) { // write the data only if its new, i dont want to write old data many times
            // get game end date
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            // make result object to save it
            Result result = new Result(arr_Answers, arr_Result, score, date);


            // save results on phone SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("quizapp", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            Gson gson = new Gson(); // convert my data to String json to save it on SharedPreferences

            String results_data = sharedPreferences.getString("results_data", null);
            if (results_data != null) {
                // there is previos data, i get it then add new data and save
                ArrayList<Result> resultArrayList = gson.fromJson(results_data,
                        new TypeToken<java.util.List<Result>>() {
                        }.getType());

                resultArrayList.add(result);

                String data = gson.toJson(resultArrayList);

                editor.putString("results_data", data);
            } else {
                // there is no previos data

                ArrayList<Result> resultArrayList = new ArrayList<>();

                resultArrayList.add(result);

                String data = gson.toJson(resultArrayList);
                editor.putString("results_data", data);
            }


            editor.apply();

        }


        ArrayAdapter ans_adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, arr_Answers);

        ArrayAdapter res_adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, arr_Result);


        Answers.setAdapter(ans_adapter);
        Results.setAdapter(res_adapter);

        tv_score.setText("Your Final Score is " + score + "\\5");
    }
}
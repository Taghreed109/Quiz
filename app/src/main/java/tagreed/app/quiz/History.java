package tagreed.app.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import tagreed.app.quiz.adapters.ResultAdapter;
import tagreed.app.quiz.objects.Result;

public class History extends AppCompatActivity {

    ListView List;

    /* Here i show all games played before for the spesic user*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();

        SharedPreferences sharedPreferences = getSharedPreferences("quizapp",MODE_PRIVATE);

        // get all games history saved
        Gson gson = new Gson();
        String results_data= sharedPreferences.getString("results_data" , null);

        if (results_data != null){
            ArrayList<Result> resultArrayList = gson.fromJson(results_data,
                    new TypeToken<java.util.List<Result>>(){}.getType());


            ResultAdapter adapter = new ResultAdapter(resultArrayList);
            List.setAdapter(adapter);
        }

    }

    void  init(){
        List = findViewById(R.id.list);
    }
}
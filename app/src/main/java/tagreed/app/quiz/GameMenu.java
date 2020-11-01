package tagreed.app.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMenu extends AppCompatActivity implements View.OnClickListener {

    Button bt_start;
    Button bt_guide;
    Button bt_history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);


        init();

        // set click listeners
        bt_start.setOnClickListener(this);
        bt_guide.setOnClickListener(this);
        bt_history.setOnClickListener(this);
    }

    void init(){
        bt_guide = findViewById(R.id.guide);
        bt_start = findViewById(R.id.start);
        bt_history = findViewById(R.id.history);
    }

    // all clicks on views will come here
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.start:
                Intent intent1 = new Intent(getApplicationContext(),Questions.class);
                startActivity(intent1);
                break;
            case  R.id.guide:
                Intent intent2 = new Intent(getApplicationContext(),GameExplain.class);
                startActivity(intent2);
                break;
            case  R.id.history:
                Intent intent3 = new Intent(getApplicationContext(),History.class);
                startActivity(intent3);
                break;
        }

    }
}
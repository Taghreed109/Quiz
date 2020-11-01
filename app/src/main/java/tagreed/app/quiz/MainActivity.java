package tagreed.app.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_email;
    EditText ed_password;
    Button bt_login;
    TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);

    }

    void init() {
        ed_email = findViewById(R.id.email);
        ed_password = findViewById(R.id.password);
        bt_login = findViewById(R.id.b_login);
        tv_register = findViewById(R.id.register);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.b_login:

                SharedPreferences sharedPreferences = getSharedPreferences("quizapp", Context.MODE_PRIVATE);
                String Email = sharedPreferences.getString("Email", null);
                String Password = sharedPreferences.getString("Password", null);

                if (Email != null && Password != null) {
                    if (ed_email.getText().toString().equals(Email) && ed_password.getText().toString().equals(Password)) {
                        Intent intent = new Intent(getApplicationContext(), GameMenu.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"Email or Password is wrong",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Please Register on the application.",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register:
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                break;
        }

    }
}
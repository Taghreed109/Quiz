package tagreed.app.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText ed_fname;
    EditText ed_lname;
    EditText ed_email;
    EditText ed_password;
    DatePicker datePicker;
    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        bt_register.setOnClickListener(e -> {
            if (validate()) {

                // get date from date picker as String
                long dateTime = datePicker.getCalendarView().getDate();
                Date date = new Date(dateTime);
                DateFormat d_format = new SimpleDateFormat("dd-MM-yyyy");
                String Date = d_format.format(date);

                // save user data on shared Preferences to ensure of him when he login next time.
                SharedPreferences sharedPreferences = getSharedPreferences("quizapp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email", ed_email.getText().toString());
                editor.putString("Password", ed_password.getText().toString());
                editor.putString("F_name", ed_fname.getText().toString());
                editor.putString("L_name", ed_lname.getText().toString());
                editor.putString("Date", Date);

                // delete the old history data
                editor.remove("results_data");
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void init() {
        ed_email = findViewById(R.id.email);
        ed_password = findViewById(R.id.password);
        ed_fname = findViewById(R.id.f_name);
        ed_lname = findViewById(R.id.l_name);
        bt_register = findViewById(R.id.register);
        datePicker = findViewById(R.id.date);
    }


    boolean validate() {
        // here i validate the inputs are not empty and right
        if (validateEmpty() && validateFields()) {
            return true;
        } else {
            return false;
        }

    }

    boolean validateEmpty() {

        // validate inputs not empty
        if (ed_email.getText().toString().isEmpty() || ed_password.getText().toString().isEmpty()
                || ed_fname.getText().toString().isEmpty() || ed_lname.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Fill all Data", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    boolean validateFields() {
        // validate email is right format , and first and last name is right
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        final Pattern e_pattern = Pattern.compile(emailRegex);

        if (e_pattern.matcher(ed_email.getText().toString()).matches()) {
            if (ed_fname.getText().toString().length() < 3 || ed_lname.getText().toString().length() < 3) {
                Toast.makeText(getApplicationContext(), "First Name and last name should be more than 3 characters", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
package com.se.onlinequizsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "=== Login ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling super, disables back button in current screen.
    }

    public void LoginButton(View view) {
        EditText emailAddress = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.input_password);

        int[] ret = Login.loginUser(emailAddress.getText().toString(), password.getText().toString());

        if (ret[0] == 1) {
            if (ret[1] == 0) {
                // TODO: 28-Dec-20 start appropriate user activity
                Intent intent = new Intent(this, InstructorQuizListActivity.class);
                startActivity(intent);
            } else if (ret[1] == 1) {
                // TODO: 28-Dec-20 start appropriate user activity
                Intent intent = new Intent(this, StudentQuizListActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
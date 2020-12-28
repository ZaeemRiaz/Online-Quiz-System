package com.se.onlinequizsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
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

        bogusEntries();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling super, disables back button in current screen.
    }

    public void LoginButton(View view) {
        EditText emailAddress = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.input_password);

        int userType = Login.loginUser(this, emailAddress.getText().toString(), password.getText().toString());

        if (userType == -1) {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        } else if (userType == 0) {
            Intent intent = new Intent(this, StudentQuizListActivity.class);
            startActivity(intent);
        } else if (userType == 1) {
            Intent intent = new Intent(this, InstructorQuizListActivity.class);
            startActivity(intent);
        }

    }


    public void bogusEntries() {
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            String sql = "insert into users values(?,?,?,?);";
            SQLiteStatement prep = db.compileStatement(sql);

            prep.bindLong(1, 1);
            prep.bindString(2, "Dev student #1");
            prep.bindString(3, "password");
            prep.bindLong(4, 0);
            prep.execute();
            long rowId = prep.executeInsert();
            Log.d(TAG, "bogusEntries: first statement");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql = "insert into users values(?,?,?,?);";
            SQLiteStatement prep = db.compileStatement(sql);
            prep.bindLong(1, 2);
            prep.bindString(2, "Dev instructor");
            prep.bindString(3, "password");
            prep.bindLong(4, 1);
            prep.execute();
            Log.d(TAG, "bogusEntries: second statement");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql = "insert into quiz (quizID, quizName, difficulty, openTime, closeTime, totalTimeInSeconds, totalQuestions, totalMarks, instructions)" +
                    "values (1, 'sample quiz 1', 3, 1609030194, 1609030194, 3600, 3, 3, 'Attempt all questions')";
            db.execSQL(sql);
            Log.d(TAG, "bogusEntries: third statement");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql = "insert into quizQuestions(quizID, questionID)" + "values (1, 1)";
            db.execSQL(sql);
            Log.d(TAG, "bogusEntries: fourth statement");

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql = "insert into question (qID, qType, qText, qMarks, posAns1, posAns2, posAns3, posAns4, valAns1, valAns2, valAns3, valAns4) "
                    + "values (1, 1, 'Which signs show that a plant has been grown in soil deficient in magnesium ions', 10, 'no flowers and poor root growth','small leaves and ore roots','white upper leaves and no flowers','yellow stem and yellow leaves',0,0,0,1)";
            db.execSQL(sql);
            Log.d(TAG, "bogusEntries: fifth statement");

        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
    }
}
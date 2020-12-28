package com.se.onlinequizsystem;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Quiz implements Serializable {
    int quizID;
    String quizName;
    int difficulty;
    long openTime;
    long closeTime;
    int totalTime;
    int totalQuestions;
    int totalMarks;
    String instructions;
    ArrayList<Question> listOfQuestions;

    public Quiz(int quizID_, String quizName_, int difficulty_, Long openTime_, Long closeTime_, int totalTime_,
                int totalQuestions_, int totalMarks_, String instructions_, ArrayList<Question> listOfQuestions_) {
        quizID = quizID_;
        quizName = quizName_;
        difficulty = difficulty_;
        openTime = openTime_;
        closeTime = closeTime_;
        totalTime = totalTime_;
        totalQuestions = totalQuestions_;
        totalMarks = totalMarks_;
        instructions = instructions_;
        listOfQuestions = listOfQuestions_;
    }

    public static ArrayList<Quiz> getAllQuiz() {
        Connection c = null;
        PreparedStatement stmt = null;
        String sql;
        int quizID;
        String quizName;
        int difficulty;
        long openTime_epoch;
        long closeTime_epoch;
        int TotalTime;
        int totalQuestions;
        int totalMarks;
        int questionID;
        String instructions;
        ArrayList<Quiz> listOfQuiz = new ArrayList<Quiz>();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:testq.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            sql = "select * from quiz";
            stmt = c.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                quizID = res.getInt("quizID");
                quizName = res.getString("quizName");
                difficulty = res.getInt("difficulty");
                openTime_epoch = res.getLong("openTime");
                closeTime_epoch = res.getLong("closeTime");
                TotalTime = res.getInt("totalTimeInSeconds");
                totalQuestions = res.getInt("totalQuestions");
                totalMarks = res.getInt("totalMarks");
                instructions = res.getString("instructions");


                //get all questions
                PreparedStatement prep = c.prepareStatement("select * from quizQuestions where quizID = ?;");
                prep.setInt(1, quizID);
                ResultSet quizQuestionIDs = prep.executeQuery();
                ArrayList<Question> listOfQuestions = new ArrayList<Question>();

                while (quizQuestionIDs.next()) {
                    questionID = quizQuestionIDs.getInt("questionID");
                    listOfQuestions.add(new Question(questionID));
                }

                listOfQuiz.add(new Quiz(quizID, quizName, difficulty, openTime_epoch, closeTime_epoch, TotalTime,
                        totalQuestions, totalMarks, instructions, listOfQuestions));


            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfQuiz;

    }
}

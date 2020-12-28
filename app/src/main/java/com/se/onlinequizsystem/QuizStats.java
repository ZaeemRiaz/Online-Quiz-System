package com.se.onlinequizsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuizStats {
    int totalStudents;
    int quizAttempted;
    Double averageTime;
    ArrayList<Double> averageQuestionTime;

    public QuizStats(int quizID) {
        int idx = 0;
        this.averageQuestionTime = new ArrayList<Double>();

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            PreparedStatement prep = c.prepareStatement(
                    "select quizID, questionID, AVG(timeTaken) as avgTimeQuestion, AVG(marksScored) as avgMarks from studentAttempt where quizID = ? GROUP by quizID, questionID;");
            prep.setInt(1, 1);
            ResultSet res = prep.executeQuery();

            while (res.next()) {
                idx += 1;
                this.averageTime += res.getDouble("avgTimeQuestion");
                this.averageQuestionTime.add(res.getDouble("avgTimeQuestion"));
            }
            prep.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            PreparedStatement prep = c.prepareStatement("select COUNT(userID) as cnt from users where uType = 0;");
            ResultSet res = prep.executeQuery();
            while (res.next()) {
                this.totalStudents = res.getInt("cnt");
            }
            prep.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.quizAttempted = idx;

    }
}

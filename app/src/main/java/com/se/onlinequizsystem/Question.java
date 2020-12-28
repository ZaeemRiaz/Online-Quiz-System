package com.se.onlinequizsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Question {
    public Integer qId;
    public Integer qType; // 1 MCQ single, 2 MCQ multiple, 3 TF, 4 Obj, 5 Sbj
    public String qText;
    public Integer qMarks;
    public List<String> qAnsPossible = new ArrayList<String>(); // Assuming all these sizes 4, discuss with Murad
    public List<Integer> trueAnswers = new ArrayList<Integer>();
    // public List<Integer> qAnsSelected = new ArrayList<Integer>();
    // public String qAnswer; // Subjective or Objective

    public Question() {

    }

    //takes questionID as int, gets and populates object from DB
    public Question(int questionID) {
        Connection c = null;
        PreparedStatement stmt = null;
        String sql;
        // Question q = new Question();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            sql = "select * from Question where qID=?";
            stmt = c.prepareStatement(sql);
            // Parameters
            stmt.setInt(1, questionID);
            // Execute
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                this.qId = res.getInt("qID");
                this.qType = res.getInt("qType");
                this.qText = res.getString("qText");
                this.qMarks = res.getInt("qMarks");
                // Now check the type of question and proceed accordingly
                // 1 MCQ single, 2 MCQ multiple, 3 TF, 4 Obj, 5 Sbj
                if (this.qType == 1 || this.qType == 2) {
                    this.qAnsPossible.add(res.getString("posAns1"));
                    this.qAnsPossible.add(res.getString("posAns2"));
                    this.qAnsPossible.add(res.getString("posAns3"));
                    this.qAnsPossible.add(res.getString("posAns4"));
                    this.trueAnswers.add(res.getInt("valAns1"));
                    this.trueAnswers.add(res.getInt("valAns2"));
                    this.trueAnswers.add(res.getInt("valAns3"));
                    this.trueAnswers.add(res.getInt("valAns4"));
                } else if (this.qType == 3) {
                    this.qAnsPossible.add(res.getString("posAns1"));
                    this.qAnsPossible.add(res.getString("posAns2"));
                    this.trueAnswers.add(res.getInt("valAns1"));
                    this.trueAnswers.add(res.getInt("valAns2"));
                }


            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Question(Integer qId, Integer qType, String qText, Integer qMarks) {
        this.qId = qId;
        this.qType = qType;
        this.qText = qText;
        this.qMarks = qMarks;
        // Add possible answers incase of an MCQ/TF
    }

    public void AddOptions(List<String> temp) {
        for (int i = 0; i < temp.size(); i++) {
            this.qAnsPossible.add(temp.get(i));
        }
    }

    public void AddAnswers(List<Integer> temp) {
        for (int i = 0; i < temp.size(); i++) {
            this.trueAnswers.add(temp.get(i));
        }
    }
}
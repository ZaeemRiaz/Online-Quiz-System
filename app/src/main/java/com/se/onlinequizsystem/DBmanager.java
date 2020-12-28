package com.se.onlinequizsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBmanager {
    static void createDB() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql;

            sql = "create table users(userID integer not null," + "userName varchar(100) not null,"
                    + "uPassword varchar(100) not null," + "uType integer not null," + "primary key(userID));";
            stmt.executeUpdate(sql);

            sql = "create table quiz(quizID integer not null," + "quizName varchar(100)," + "difficulty integer,"
                    + "openTime integer," + "closeTime integer," + "totalTimeInSeconds integer," + "totalQuestions integer,"
                    + "totalMarks integer," + "instructions varchar(1000)," + "primary key(quizID));";
            stmt.executeUpdate(sql);

            // sql = "create table MCQanswers(MCQanswerid integer primary key," +
            // "answerText varchar(1000)," + "correctAnswer integer);";
            // stmt.executeUpdate(sql);

            // sql = "create table Questions(questionID integer primary key," +
            // "questionType integer," + "questionText varchar(1000)," +
            // "questionMarks integer," + "mcqAns1ID integer," +
            // "mcqAns2ID integer," + "mcqAns3ID integer," +
            // "mcqAns4ID integer," + "TFanswer integer," +
            // "objectiveAnswer varchar(100)," + "subjectiveKeywords varchar(1000)," +
            // "foreign key (mcqAns1ID) references MCQanswers(MCQanswerID)," +
            // "foreign key (mcqAns2ID) references MCQanswers(MCQanswerID)," +
            // "foreign key (mcqAns3ID) references MCQanswers(MCQanswerID)," +
            // "foreign key (mcqAns4ID) references MCQanswers(MCQanswerID));";
            // stmt.executeUpdate(sql);

            sql = "create table Question(qID integer primary key," + "qType integer," + "qText varchar(1000),"
                    + "qMarks integer," + "qAnswer varchar(1000)," + "posAns1 varchar(1000)," + "posAns2 varchar(1000),"
                    + "posAns3 varchar(1000)," + "posAns4 varchar(1000)," + "valAns1 integer," + "valAns2 integer,"
                    + "valAns3 integer," + "valAns4 integer," + "sel1 integer," + "sel2 integer," + "sel3 integer,"
                    + "sel4 integer);";
            stmt.executeUpdate(sql);

            sql = "create table quizQuestions(quizID integer," + "questionID integer,"
                    + "foreign key (quizID) references Quiz(quizID),"
                    + "foreign key (QuestionID) references Questions(questionID)," + "primary key(quizID, questionID));";
            stmt.executeUpdate(sql);

            sql = "create table studentAttempt(" + "userID integer," + "QuizID integer," + "QuestionID integer,"
                    + "timeTaken integer," + "marksScored integer," + "foreign key (userID) references users(userID),"
                    + "foreign key (quizID) references Quiz(quizID),"
                    + "foreign key (QuestionID) references Questions(questionID),"
                    + "primary key (userID, quizID, questionID)" + ")";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void bogusEntries() {
        Connection c = null;
        Statement stmt = null;
        String sql;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            PreparedStatement prep = c.prepareStatement("insert into users values(?,?,?,?);");

            prep.setInt(1, 1);
            prep.setString(2, "Dev student #1");
            prep.setString(3, "password");
            prep.setInt(4, 0);
            prep.execute();

            prep.setInt(1, 2);
            prep.setString(2, "Dev instructor");
            prep.setString(3, "password");
            prep.setInt(4, 1);
            prep.execute();

            sql = "insert into quiz (quizID, quizName, difficulty, openTime, closeTime, totalTimeInSeconds, totalQuestions, totalMarks, instructions)" +
                    "values (1, 'sample quiz 1', 3, 1609030194, 1609030194, 3600, 3, 3, 'Attempt all questions');";
            stmt.executeUpdate(sql);

            sql = "insert into quizQuestions(quizID, questionID)" + "values (1, 1)";
            stmt.executeUpdate(sql);

            sql = "insert into question (qID, qType, qText, qMarks, posAns1, posAns2, posAns3, posAns4, valAns1, valAns2, valAns3, valAns4) "
                    + "values (1, 1, 'Which signs show that a plant has been grown in soil deficient in magnesium ions', 10, 'no flowers and poor root growth','small leaves and ore roots','white upper leaves and no flowers','yellow stem and yellow leaves',0,0,0,1);";

            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
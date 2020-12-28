package com.se.onlinequizsystem;

import java.sql.*;

public class Login {
   static int[] loginUser(String id, String password) {
      Connection c = null;
      PreparedStatement stmt = null;
      String sql;
      int exists = 0;
      int type = -1;

      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");

         sql = "select * from users where userName=? and uPassword=?";
         stmt = c.prepareStatement(sql);
         // Parameters
         stmt.setString(1, id);
         stmt.setString(2, password);
         // Execute
         ResultSet res = stmt.executeQuery();
         if (res.next()) {
//            System.out.println("Login successful!");
            type = res.getInt("uType");

            exists = 1;
         }
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return new int[] { exists, type };
   }

   static Question fetchMCQ(Integer id) {
      Connection c = null;
      PreparedStatement stmt = null;
      String sql;
      Question q = new Question();
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");

         sql = "select * from Question where qID=?";
         stmt = c.prepareStatement(sql);
         // Parameters
         stmt.setInt(1, id);
         // Execute
         ResultSet res = stmt.executeQuery();

         if (res.next()) {
            q.qId = res.getInt("qID");
            q.qType = res.getInt("qType");
            q.qText = res.getString("qText");
            q.qMarks = res.getInt("qMarks");
            q.qAnsPossible.add(res.getString("posAns1"));
            q.qAnsPossible.add(res.getString("posAns2"));
            q.qAnsPossible.add(res.getString("posAns3"));
            q.qAnsPossible.add(res.getString("posAns4"));
            q.trueAnswers.add(res.getInt("valAns1"));
            q.trueAnswers.add(res.getInt("valAns2"));
            q.trueAnswers.add(res.getInt("valAns3"));
            q.trueAnswers.add(res.getInt("valAns4"));

         }
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return q;
   }

   static boolean checkSingularMCQ(Integer qId, Integer choice) // options 1, 2, 3, 4 selected corresond to 0, 1, 2, 3
   {
      Connection c = null;
      PreparedStatement stmt = null;
      String sql;
      boolean check = false;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");

         sql = "select * from Question where qID=?";
         stmt = c.prepareStatement(sql);
         // Parameters
         stmt.setInt(1, qId);
         // Execute
         ResultSet res = stmt.executeQuery();

         if (res.next()) // Question fetched
         {
            if (choice.equals(1) && res.getInt("valAns1") == 1) {
               check = true;
            }
            if (choice.equals(2) && res.getInt("valAns2") == 1) {
               check = true;
            }
            if (choice.equals(3) && res.getInt("valAns3") == 1) {
               check = true;
            }
            if (choice.equals(4) && res.getInt("valAns4") == 1) {
               check = true;
            }
         }
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return check;
   }

   static boolean checkObjective(Integer qId, int[] choices) // options 1, 2, 3, 4 selected corresond to 0, 1, 2, 3
   {
      Connection c = null;
      PreparedStatement stmt = null;
      String sql;
      boolean check = true;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
//         System.out.println("Opened database successfully");

         sql = "select * from Question where qID=?";
         stmt = c.prepareStatement(sql);
         // Parameters
         stmt.setInt(1, qId);
         // Execute
         ResultSet res = stmt.executeQuery();

         if (res.next()) // Question fetched
         {
            int totalAns = choices.length;
            for (int i = 0; i < totalAns; i++) {
               if (choices[i] == 1 && res.getInt("valAns1") != 1) {
                  check = false;
               } else if (choices[i] == 2 && res.getInt("valAns2") != 1) {
                  check = false;
               } else if (choices[i] == 3 && res.getInt("valAns3") != 1) {
                  check = false;
               } else if (choices[i] == 4 && res.getInt("valAns4") != 1) {
                  check = false;
               }
            }
         }
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return check;
   }

   static void addQScore(Integer userID, Integer quizID, Integer questionID, Integer TimeTaken, Integer marksScored) {
      Connection c = null;
      Statement stmt = null;
      String sql = "insert into studentAttempt values(?,?,?,?,?);";
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);

         stmt = c.createStatement();

         PreparedStatement prep = c.prepareStatement(sql);

         prep.setInt(1, userID);
         prep.setInt(2, quizID);
         prep.setInt(3, questionID);
         prep.setInt(4, TimeTaken);
         prep.setInt(5, marksScored);
         prep.execute();

         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      // DBmanager.createDB();
      // DBmanager.bogusEntries();
      Question temp = fetchMCQ(1);
//      System.out.println(temp.qText);
      int[] arr = { 4, 2 };
//      System.out.println(checkObjective(temp.qId, arr));
      // System.out.println("Hello, World!");
      // String loginID = "Dev student #1";
      // String loginPassword = "password";
      // int [] res = loginUser(loginID, loginPassword);
      // System.out.println(res[0]); //exists (0/1)
      // System.out.println(res[1]); //userType (0-> student/1-> instructor)
   }
}
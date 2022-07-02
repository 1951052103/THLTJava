/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.services;

import com.bth.englishapp.JdbcUtils;
import com.bth.englishapp.JdbcUtils;
import com.bth.pojo.Choice;
import com.bth.pojo.Question;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionServices {
    public void addQuestion(Question q, List<Choice> choices) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("INSERT INTO question(id, content, category_id) VALUES(?, ?, ?)");
            stm.setString(1, q.getId());
            stm.setString(2, q.getContent());
            stm.setInt(3, q.getCategoryId());
            
            conn.setAutoCommit(false);
            try {
                if (stm.executeUpdate() > 0) {
                    PreparedStatement stm2 = conn.prepareCall("INSERT INTO choice(id, content, is_correct, question_id) VALUES(?, ?, ?, ?)");

                    for (Choice c: choices) {
                        stm2.setString(1, c.getId());
                        stm2.setString(2, c.getContent());
                        stm2.setBoolean(3, c.isIsCorrect());
                        stm2.setString(4, c.getQuestionId());
                        stm2.executeUpdate();
                    }
                }
                conn.commit();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
                conn.rollback();
            }
        }
    }
    
    public List<Question> getQuestions(String kw) throws SQLException {
        List<Question> questions = new ArrayList<>();
         try (Connection conn = JdbcUtils.getConn()) {
             CallableStatement stm = conn.prepareCall("{ call getQuestions(?) }");
             stm.setString(1, kw);
             
             ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                 Question q = new Question(rs.getString("id"), rs.getString("content"), rs.getInt("category_id"));
                 questions.add(q);
             }
         }
         
         return questions;
    }
}

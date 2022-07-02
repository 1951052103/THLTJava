/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.bth.englishapp;

import com.bth.pojo.Choice;
import com.bth.pojo.Question;
import com.bth.services.QuestionServices;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author admin
 */
public class EnglishApp {
    public static void main(String[] args) throws SQLException {
//        Question q = new Question(UUID.randomUUID().toString(), "This is ... engineer.", 1);
//        List<Choice> choices = new ArrayList<>();
//        choices.add(new Choice(UUID.randomUUID().toString(), "a", false, q.getId()));
//        choices.add(new Choice(UUID.randomUUID().toString(), "an", true, q.getId()));
//        choices.add(new Choice(UUID.randomUUID().toString(), "the", false, q.getId()));
//        choices.add(new Choice(UUID.randomUUID().toString(), "empty", false, q.getId()));
//        
//        QuestionServices s = new QuestionServices();
//        s.addQuestion(q, choices);
//        s.getQuestions("is").forEach(q -> System.out.printf("%s - %s\n", q.getId(), q.getContent()));

        JdbcTemplate t = JdbcTemplateUtils.getT();
        List<Question> questions =  t.query("SELECT * FROM question WHERE content like concat('%', ?, '%')", (rs, rowNum) -> {
            Question q = new Question(rs.getString("id"), rs.getString("content"), rs.getInt("category_id"));
            
            return q;
        }, "is");
        questions.forEach(q -> System.out.printf("%s - %s\n", q.getId(), q.getContent()));
    }
}
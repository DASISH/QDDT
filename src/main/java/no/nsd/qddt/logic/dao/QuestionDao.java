package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.QuestionOrm;
import no.nsd.qddt.model.Question;

public class QuestionDao {

   private final Connection conn;
   
   public QuestionDao(Connection conn) {
      this.conn = conn;
   }

   
   public Question getQuestion(Integer questionId) throws SQLException {
      String sql = "select * from question where question_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(questionId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return QuestionOrm.getQuestionFromFirstRow(rows);
   }
   
   
   public List<Question> getQuestionsForScheme(Integer questionSchemeId) throws SQLException {
      String sql = "select q.* from "
              + "question as q inner join question_in_scheme as qis "
              + "on q.question_id = qis.question_id "
              + "where question_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(questionSchemeId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return QuestionOrm.getQuestionList(rows);
   }

   public List<Question> getQuestionsForConceptScheme(Integer concept, Integer conceptSchemeId) throws SQLException {
      String sql = "select q.* from "
              + "question as q inner join concept_in_question as ciq "
              + "on q.question_id = ciq.question_id "
              + "where ciq.concept_id = ? and ciq.concept_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(concept);
      values.add(conceptSchemeId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return QuestionOrm.getQuestionList(rows);
   }
   
   
}

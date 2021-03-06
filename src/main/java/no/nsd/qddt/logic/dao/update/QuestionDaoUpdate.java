package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Question;

public class QuestionDaoUpdate {

   private final Connection conn;
   
   public QuestionDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   
   
   public Integer registerNewQuestion(Question question) throws SQLException {
      String sql = "insert into "
              + "question(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "question_intent, "
              + "question_text, "
              + "question_text_2, "
              + "version_description, "
              + "version_updated) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(question.getUrn().getAgency().getId());
      values.add(question.getModuleVersionId());
      values.add(question.getUrn().getId());
      values.add(question.getUrn().getVersion());

      values.add(question.getName());
      values.add(question.getQuestionIntent());
      values.add(question.getQuestionText());
      values.add(question.getQuestionText2());
      values.add(question.getVersionDescription());
      
      values.add(question.getVersionUpdated());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer questionId = sqlCommand.executeAndReturnGeneratedKey();
      return questionId;
   }

   public void updateQuestion(Question question) throws SQLException {
      String sql = "update question set "
              + "name = ?, "
              + "question_intent = ?, "
              + "question_text = ?, "
              + "question_text_2 = ?, "
              + "version_description = ?, "
              + "version_updated = ? "
              + "where question_id = ?";

      List values = new ArrayList();
      values.add(question.getName());
      values.add(question.getQuestionIntent());
      values.add(question.getQuestionText());
      values.add(question.getQuestionText2());
      values.add(question.getVersionDescription());
      values.add(question.getVersionUpdated());

      values.add(question.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateResponseCardinality(Question question) throws SQLException {
      String sql = "update question set "
              + "minimum_responses = ?, "
              + "maximum_responses = ? "
              + "where question_id = ?";

      List values = new ArrayList();
      values.add(question.getMinimumResponses());
      values.add(question.getMaximumResponses());

      values.add(question.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   

   public void deleteQuestion(Question question) throws SQLException {
      String sql = "delete from question where question_id = ?";

      List values = new ArrayList();
      values.add(question.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }


   public void updateCodeListForQuestion(Question question) throws SQLException {
      String sql = "update question set "
              + "code_list_id = ? "
              + "where question_id = ?";

      List values = new ArrayList();
      values.add(question.getCodeListId());
      values.add(question.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   
}

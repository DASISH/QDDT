package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.model.QuestionScheme;

public class QuestionSchemeDaoUpdate {

   private final Connection conn;

   public QuestionSchemeDaoUpdate(Connection conn) {
      this.conn = conn;
   }

   public void registerNewQuestionScheme(QuestionScheme questionScheme) throws SQLException {
      String sql = "insert into "
              + "question_scheme(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "label, "
              + "description, "
              + "version_description, "
              + "version_updated) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(questionScheme.getUrn().getAgency().getId());
      values.add(questionScheme.getModuleVersionId());
      values.add(questionScheme.getUrn().getId());
      values.add(questionScheme.getUrn().getVersion());
      values.add(questionScheme.getName());
      values.add(questionScheme.getLabel());
      values.add(questionScheme.getDescription());
      values.add(questionScheme.getVersionDescription());
      values.add(questionScheme.getVersionUpdated());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer questionSchemeId = sqlCommand.executeAndReturnGeneratedKey();
      questionScheme.setId(questionSchemeId);
   }


   public void updateQuestionScheme(QuestionScheme questionScheme) throws SQLException {
      String sql = "update question_scheme set "
              + "name = ?, "
              + "label = ?, "
              + "description = ?, "
              + "version_description = ?, "
              + "version_updated = ? "
              + "where question_scheme_id = ?";

      List values = new ArrayList();
      values.add(questionScheme.getName());
      values.add(questionScheme.getLabel());
      values.add(questionScheme.getDescription());
      values.add(questionScheme.getVersionDescription());
      values.add(questionScheme.getVersionUpdated());

      values.add(questionScheme.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public Integer addQuestionToScheme(Question question) throws SQLException {
      String sql = "insert into "
              + "question_in_scheme(question_scheme_id, "
              + "question_id, "
              + "question_order) "
              + "values (?, ?, ?)";

      List values = new ArrayList();
      values.add(question.getQuestionSchemeId());
      values.add(question.getId());
      values.add(question.getQuestionOrder());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer questionId = sqlCommand.executeAndReturnGeneratedKey();
      return questionId;
   }

   
   public void deleteQuestionFromScheme(Question question) throws SQLException {
      String sql = "delete from question_in_scheme where question_id = ? and question_scheme_id = ?";

      List values = new ArrayList();
      values.add(question.getId());
      values.add(question.getQuestionSchemeId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   public void setQuestionSchemeUpdated(Integer questionSchemeId) throws SQLException {
      String sql = "update question_scheme set "
              + "version_updated = ? "
              + "where question_scheme_id = ?";

      List values = new ArrayList();
      values.add(Boolean.TRUE);
      values.add(questionSchemeId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

}

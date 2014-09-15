package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptSchemeDaoUpdate {

   private final Connection conn;

   public ConceptSchemeDaoUpdate(Connection conn) {
      this.conn = conn;
   }

   public void registerNewConceptScheme(ConceptScheme conceptScheme) throws SQLException {
      String sql = "insert into "
              + "concept_scheme(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "label, "
              + "description, "
              + "version_description, "
              + "version_change_code, "
              + "version_date) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(conceptScheme.getUrn().getAgency().getId());
      values.add(conceptScheme.getModuleVersionId());
      values.add(conceptScheme.getUrn().getId());
      values.add(conceptScheme.getUrn().getVersion());
      values.add(conceptScheme.getName());
      values.add(conceptScheme.getLabel());
      values.add(conceptScheme.getDescription());
      values.add(conceptScheme.getVersionDescription());
      values.add(conceptScheme.getVersionChangeCode());
      values.add(SqlUtil.getDateNow());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer conceptSchemeId = sqlCommand.executeAndReturnGeneratedKey();
      conceptScheme.setId(conceptSchemeId);
   }

   public void updateConceptScheme(ConceptScheme conceptScheme) throws SQLException {
      String sql = "update concept_scheme set "
              + "name = ?, "
              + "label = ?, "
              + "description = ?, "
              + "version_description = ?, "
              + "version_change_code = ?, "
              + "version_date = ? "
              + "where concept_scheme_id = ?";

      List values = new ArrayList();
      values.add(conceptScheme.getName());
      values.add(conceptScheme.getLabel());
      values.add(conceptScheme.getDescription());
      values.add(conceptScheme.getVersionDescription());
      values.add(conceptScheme.getVersionChangeCode());
      values.add(SqlUtil.getDateNow());

      values.add(conceptScheme.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void addConceptToScheme(Concept concept) throws SQLException {
      String sql = "insert into "
              + "concept_in_scheme(concept_scheme_id, "
              + "concept_id, "
              + "parent_concept_id, "
              + "concept_order) "
              + "values (?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(concept.getConceptSchemeId());
      values.add(concept.getId());
      values.add(concept.getParentConceptId());
      values.add(concept.getConceptOrder());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }


   public void addQuestionToConcept(Integer questionId, Integer conceptId, Integer conceptSchemeId) throws SQLException {
      String sql = "insert into concept_in_question ("
              + "question_id, "
              + "concept_id, "
              + "concept_scheme_id) "
              + "values (?, ?, ?)";

      List values = new ArrayList();
      values.add(questionId);
      values.add(conceptId);
      values.add(conceptSchemeId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   public void removeQuestionFromConcept(Integer questionId, Concept concept) throws SQLException {
      String sql = "delete from concept_in_question where question_id = ? and concept_id = ? and concept_scheme_id = ?";

      List values = new ArrayList();
      values.add(questionId);
      values.add(concept.getId());
      values.add(concept.getConceptSchemeId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   public void setConceptSchemeUpdated(Integer conceptSchemeId) throws SQLException {
      String sql = "update concept_scheme set "
              + "version_updated = ? "
              + "where concept_scheme_id = ?";

      List values = new ArrayList();
      values.add(Boolean.TRUE);
      values.add(conceptSchemeId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

}

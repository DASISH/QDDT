package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Concept;

public class ConceptDaoUpdate {

   private final Connection conn;
   
   public ConceptDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   
   
   public Integer registerNewConcept(Concept concept) throws SQLException {
      String sql = "insert into "
              + "concept(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "label, "
              + "description, "
              + "relationship_concept, "
              + "version_description, "
              + "version_updated) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(concept.getUrn().getAgency().getId());
      values.add(concept.getModuleVersionId());
      values.add(concept.getUrn().getId());
      values.add(concept.getUrn().getVersion());

      values.add(concept.getName());
      values.add(concept.getLabel());
      values.add(concept.getDescription());
      values.add(concept.getRelationshipConcept());
      values.add(concept.getVersionDescription());
      
      values.add(concept.getVersionUpdated());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer conceptId = sqlCommand.executeAndReturnGeneratedKey();
      return conceptId;
   }

   public void updateConcept(Concept concept) throws SQLException {
      String sql = "update concept set "
              + "name = ?, "
              + "label = ?, "
              + "description = ?, "
              + "relationship_concept = ?, "
              + "version_description = ?, "
              + "version_updated = ? "
              + "where concept_id = ?";

      List values = new ArrayList();
      values.add(concept.getName());
      values.add(concept.getLabel());
      values.add(concept.getDescription());
      values.add(concept.getRelationshipConcept());
      values.add(concept.getVersionDescription());
      values.add(concept.getVersionUpdated());

      values.add(concept.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   

   public void deleteConcept(Concept concept) throws SQLException {
      String sql = "delete from concept where concept_id = ?";

      List values = new ArrayList();
      values.add(concept.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void deleteConceptFromScheme(Concept concept) throws SQLException {
      String sql = "delete from concept_in_scheme where concept_id = ? and concept_scheme_id = ?";

      List values = new ArrayList();
      values.add(concept.getId());
      values.add(concept.getConceptSchemeId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
}

package no.nsd.qddt.logic.orm.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Concept;

public class ConceptPersistenceLogic {

   private final Connection conn;
   
   public ConceptPersistenceLogic(Connection conn) {
      this.conn = conn;
   }
   
   
   
   public Integer registerNewConcept(Concept concept) throws SQLException {
      String sql = "insert into "
              + "concept(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "version_updated) "
              + "values (?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(concept.getUrn().getAgency().getId());
      values.add(concept.getModuleVersionId());
      values.add(concept.getUrn().getId());
      values.add(concept.getUrn().getVersion());
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
   
   
   
}

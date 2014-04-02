package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.Urn;

public final class ConceptOrm {

   private ConceptOrm() {
   }

   public static List<Concept> getConceptList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Concept> concepts = new ArrayList<Concept>();
      for (SortedMap row : rows) {
         concepts.add(getConcept(row));
      }
      return concepts;
   }

   
   public static Concept getConcept(Map map) throws SQLException {
      Concept concept = new Concept();
      
      Urn urn = UrnOrm.getUrn(map);
      concept.setUrn(urn);
      
      concept.setId((Integer) map.get("concept_id"));
      concept.setModuleVersionId((Integer) map.get("module_version_id"));
      concept.setParentConceptId((Integer) map.get("parent_concept_id"));
      concept.setName(SqlUtil.getString("name", map));
      concept.setLabel(SqlUtil.getString("label", map));
      concept.setDescription(SqlUtil.getString("description", map));
      concept.setRelationshipConcept(SqlUtil.getString("relationship_concept", map));
      concept.setVersionDescription(SqlUtil.getString("version_description", map));
      concept.setVersionUpdated((Boolean) map.get("version_updated"));
      concept.setConceptOrder((Integer) map.get("concept_order"));
      
      return concept;
   }      
   
}

package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.Urn;

public class ConceptLogic {

   private final Connection conn;
   
   public ConceptLogic(Connection conn) {
      this.conn = conn;
   }

   public Concept getConcept(Integer id) throws SQLException {
      String sql = "select * from concept where concept_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      List<Concept> list = this.getConceptList(rows);
      if (list == null || list.isEmpty()) {
         return null;
      }
      return list.get(0);
   }
   


   public void getConceptsForScheme(ConceptScheme cs) throws SQLException {
      String sql = "select "
              + "c.*, "
              + "cis.parent_concept_id, "
              + "cis.concept_order "
              + "from concept as c inner join concept_in_scheme as cis on c.concept_id = cis.concept_id "
              + "where cis.concept_scheme_id = ? order by cis.concept_order";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(cs.getId());
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      
      List<Concept> concepts = this.getConceptList(rows);
      if (concepts == null) {
         return;
      }
      for (Concept c : concepts) {
         if (c.getParentConceptId() == null) {
            cs.addConcept(c);
         } else {
            Concept parent = cs.getConcept(c.getParentConceptId());
            parent.addSubConcept(c);
         }
      }
   }
   
   
   
   private List<Concept> getConceptList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Concept> concepts = new ArrayList<Concept>();
      for (SortedMap row : rows) {
         concepts.add(this.getConcept(row));
      }
      return concepts;
   }

   
   private Concept getConcept(Map map) throws SQLException {
      Concept concept = new Concept();
      
      Urn urn = UrnOrmUtil.getUrn(map);
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

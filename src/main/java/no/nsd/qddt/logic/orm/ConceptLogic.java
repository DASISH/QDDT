package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;

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
   


   public ConceptScheme getConceptsForScheme(Integer schemeId) throws SQLException {
      String sql = "select c.concept_id, c.concept_name, cis.parent_concept_id "
              + "from concept as c inner join concept_in_scheme as cis on c.concept_id = cis.concept_id "
              + "where cis.concept_scheme_id = ? order by cis.concept_order";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(schemeId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      
      ConceptScheme cs = new ConceptScheme();
      cs.setId(schemeId);
      
      List<Concept> concepts = this.getConceptList(rows);
      if (concepts == null) {
         return null;
      }
      for (Concept c : concepts) {
         if (c.getParentConceptId() == null) {
            cs.addConcept(c);
         } else {
            Concept parent = cs.getConcept(c.getParentConceptId());
            parent.addSubConcept(c);
         }
      }
      
      return cs;
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
      concept.setId((Integer) map.get("concept_id"));
      concept.setParentConceptId((Integer) map.get("parent_concept_id"));
      concept.setName(SqlUtil.getString("concept_name", map));
      concept.setLabel(SqlUtil.getString("label", map));
      concept.setDescription(SqlUtil.getString("description", map));
      concept.setRelationshipConcept(SqlUtil.getString("relationship_concept", map));
      return concept;
   }   
   
   
   
}
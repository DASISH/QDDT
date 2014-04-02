package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.ConceptOrm;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptDao {

   private final Connection conn;
   
   public ConceptDao(Connection conn) {
      this.conn = conn;
   }

   public Concept getConcept(Integer id) throws SQLException {
      String sql = "select * from concept where concept_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      List<Concept> list = ConceptOrm.getConceptList(rows);
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
      
      List<Concept> concepts = ConceptOrm.getConceptList(rows);
      
      if (concepts == null) {
         return;
      }
      
      SortedMap<Integer, Concept> map = new TreeMap<Integer, Concept>();
      for (Concept c : concepts) {
         map.put(c.getId(), c);
      }
      
      for (Concept c : concepts) {
         if (c.getParentConceptId() == null) {
            cs.addConcept(c);
         } else {
            Concept parent = map.get(c.getParentConceptId());
            if (parent != null) {
               parent.addSubConcept(c);
            }
         }
      }
   }

   
   

   
   
   
}

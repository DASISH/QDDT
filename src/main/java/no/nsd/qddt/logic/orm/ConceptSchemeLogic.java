package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptSchemeLogic {

   private final Connection conn;
   
   public ConceptSchemeLogic(Connection conn) {
      this.conn = conn;
   }

   public ConceptScheme getConceptScheme(Integer id) throws SQLException {
      String sql = "select * from concept_scheme where concept_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getConceptSchemeFromFirstRow(rows);
   }
   
   private ConceptScheme getConceptSchemeFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getConceptScheme(rows[0]);
   }
   
   
   private List<ConceptScheme> getConceptSchemeList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<ConceptScheme> list = new ArrayList<ConceptScheme>();
      for (SortedMap row : rows) {
         list.add(this.getConceptScheme(row));
      }
      return list;
   }

   
   private ConceptScheme getConceptScheme(Map map) throws SQLException {
      ConceptScheme cs = new ConceptScheme();
      cs.setId((Integer) map.get("concept_scheme_id"));
      cs.setName(SqlUtil.getString("name", map));
      cs.setLabel(SqlUtil.getString("label", map));
      cs.setDescription(SqlUtil.getString("description", map));
      cs.setVersionDescription(SqlUtil.getString("version_description", map));
      return cs;
   }   
   
   
   
}

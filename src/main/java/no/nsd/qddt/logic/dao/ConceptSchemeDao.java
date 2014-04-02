package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.ConceptSchemeOrm;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptSchemeDao {

   private final Connection conn;
   
   public ConceptSchemeDao(Connection conn) {
      this.conn = conn;
   }

   public ConceptScheme getConceptScheme(Integer id) throws SQLException {
      String sql = "select * from concept_scheme where concept_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return ConceptSchemeOrm.getConceptSchemeFromFirstRow(rows);
   }
   
   
}

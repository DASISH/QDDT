package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.CategorySchemeOrm;
import no.nsd.qddt.model.CategoryScheme;

public class CategorySchemeDao {

   private final Connection conn;
   
   public CategorySchemeDao(Connection conn) {
      this.conn = conn;
   }

   public CategoryScheme getCategoryScheme(Integer id) throws SQLException {
      String sql = "select * from category_scheme where category_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CategorySchemeOrm.getCategorySchemeFromFirstRow(rows);
   }

   
   
}

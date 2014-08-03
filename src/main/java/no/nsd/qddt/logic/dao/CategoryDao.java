package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.CategoryOrm;
import no.nsd.qddt.model.Category;

public class CategoryDao {

   private final Connection conn;
   
   public CategoryDao(Connection conn) {
      this.conn = conn;
   }

   
   public Category getCategory(Integer categoryId) throws SQLException {
      String sql = "select * from category where category_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(categoryId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CategoryOrm.getCategoryFromFirstRow(rows);
   }
   
   public List<Category> getCategoriesForScheme(Integer categorySchemeId) throws SQLException {
      String sql = "select c.* from "
              + "category as c inner join category_in_scheme as cis "
              + "on c.category_id = cis.category_id "
              + "where category_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(categorySchemeId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CategoryOrm.getCategoryList(rows);
   }
   
   
}

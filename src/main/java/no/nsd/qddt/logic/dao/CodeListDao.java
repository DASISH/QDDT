package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.CodeListOrm;
import no.nsd.qddt.model.CodeList;

public class CodeListDao {

   private final Connection conn;
   
   public CodeListDao(Connection conn) {
      this.conn = conn;
   }

   
   public CodeList getCodeList(Integer codeListId) throws SQLException {
      String sql = "select * from code_list where code_list_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(codeListId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeListOrm.getCodeListFromFirstRow(rows);
   }
   
   
   public List<CodeList> getCodeListsForModule(Integer moduleId) throws SQLException {
      String sql = "select cl.* from code_list as cl "
              + "inner join module_version as mv on cl.module_version_id = mv.module_version_id "
              + "where mv.module_id = ? "
              + "order by cl.code_list_type, cl.label";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeListOrm.getCodeLists(rows);
   }
   
   public Integer getMaxSortOrderForCodeList(Integer codeListId) throws SQLException {
      String sql = "select max(sort_order) as max_sort_order from code_in_code_list where code_list_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(codeListId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      if (rows == null || rows.length == 0) {
         return 0;
      }
      SortedMap firstRow = rows[0];
      Integer max = (Integer) firstRow.get("max_sort_order");
      
      if (max == null) {
         return 0;
      }
      return max;
   }
   
   
   
}

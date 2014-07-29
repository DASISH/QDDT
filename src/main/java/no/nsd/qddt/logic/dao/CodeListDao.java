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
   
   
   public List<CodeList> getCodeListsForModuleVersion(Integer moduleVersionId) throws SQLException {
      String sql = "select * from code_list where module_version_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleVersionId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeListOrm.getCodeLists(rows);
   }
   
   
}

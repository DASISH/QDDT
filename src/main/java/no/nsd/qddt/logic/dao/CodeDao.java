package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.CodeOrm;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.Module;

public class CodeDao {

   private final Connection conn;
   
   public CodeDao(Connection conn) {
      this.conn = conn;
   }

   
   public Code getCode(Integer codeId) throws SQLException {
      String sql = "select * from code where code_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(codeId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeOrm.getCodeFromFirstRow(rows);
   }

   
   public List<Code> getAllCodes() throws SQLException {
      String sql = "select * from code";
      
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return CodeOrm.getCodeList(rows);
   }
   

   // mapping: codeid --> code.
   public Map<Integer, Code> getAllCodesWithCategory() throws SQLException {
      String sql = "select c.*, cat.label as cat_label from code as c inner join category as cat on c.category_id = cat.category_id";
      
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      List<Code> codes = CodeOrm.getCodeList(rows);
      if (codes == null) {
         return null;
      }
      Map<Integer, Code> map = new HashMap<Integer, Code>();
      for (Code c : codes) {
         map.put(c.getId(), c);
      }
      return map;
   }
   
   public List<Code> getCodesForCodeList(Integer codeListId) throws SQLException {
      String sql = "select c.*, cat.label as cat_label, cicl.sort_order from code as c "
              + "inner join code_in_code_list as cicl on c.code_id = cicl.code_id "
              + "inner join category as cat on c.category_id = cat.category_id "
              + "where cicl.code_list_id = ? "
              + "order by cicl.sort_order, cicl.code_id";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(codeListId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeOrm.getCodeList(rows);
   }
   

   // mapping: code-list-id --> list of codes.
   public Map<Integer, List<Code>> getCodesForAllCodeLists() throws SQLException {
      String sql = "select * from code_in_code_list";
      
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      List<Code> codes = CodeOrm.getCodeList(rows);
      
      if (codes == null) {
         return null;
      }
      
      Map<Integer, List<Code>> map = new HashMap<Integer, List<Code>>();
      for (Code c : codes) {
         List<Code> list = map.get(c.getCodeListId());
         if (list == null) {
            list = new ArrayList<Code>();
            map.put(c.getCodeListId(), list);
         }
         list.add(c);
      }
      return map;
   }
   
   
   public List<Code> getCodesForModule(Module module) throws SQLException {
      String sql = "select c.*, cat.label as cat_label from "
              + "code as c inner join module_version as mv on c.module_version_id = mv.module_version_id "
              + "inner join module as m on mv.module_id = m.module_id "
              + "inner join category as cat on c.category_id = cat.category_id "
              + "where m.module_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(module.getId());
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeOrm.getCodeList(rows);
   }
   
}

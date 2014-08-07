package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
   
   
   public List<Code> getCodesForCodeList(Integer codeListId) throws SQLException {
      String sql = "select c.* from "
              + "code as c inner join code_in_list as cil "
              + "on c.code_id = cil.code_id "
              + "where code_list_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(codeListId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeOrm.getCodeList(rows);
   }
   
   public List<Code> getCodesForModule(Module module) throws SQLException {
      String sql = "select c.* from "
              + "code as c inner join module_version as mv on c.module_version_id = mv.module_version_id "
              + "inner join module as m on mv.module_id = m.module_id "
              + "where m.module_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(module.getId());
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CodeOrm.getCodeList(rows);
   }
   
}

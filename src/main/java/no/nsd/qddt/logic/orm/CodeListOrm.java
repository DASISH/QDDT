package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.Urn;

public final class CodeListOrm {

   private CodeListOrm() {
   }
   
   public static CodeList getCodeListFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getCodeList(rows[0]);
   }
   
   public static List<CodeList> getCodeLists(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<CodeList> list = new ArrayList<CodeList>();
      for (SortedMap row : rows) {
         list.add(getCodeList(row));
      }
      return list;
   }

   public static CodeList getCodeList(Map map) throws SQLException {
      CodeList cl = new CodeList();
      
      Urn urn = UrnOrm.getUrn(map);
      cl.setUrn(urn);
      
      cl.setId((Integer) map.get("code_list_id"));
      cl.setModuleVersionId((Integer) map.get("module_version_id"));
      cl.setName(SqlUtil.getString("name", map));
      cl.setLabel(SqlUtil.getString("label", map));
      cl.setDescription(SqlUtil.getString("description", map));
      cl.setCodeListType((Integer) map.get("code_list_type"));
      cl.setValidCodeListId((Integer) map.get("valid_code_list_id"));
      cl.setMissingCodeListId((Integer) map.get("missing_code_list_id"));
      cl.setVersionDescription(SqlUtil.getString("version_description", map));
      return cl;
   }
   
}

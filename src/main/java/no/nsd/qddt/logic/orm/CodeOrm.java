package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.Urn;

public final class CodeOrm {

   private CodeOrm() {
   }
   
   public static Code getCodeFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getCode(rows[0]);
   }
   
   public static List<Code> getCodeList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Code> list = new ArrayList<Code>();
      for (SortedMap row : rows) {
         list.add(getCode(row));
      }
      return list;
   }

   public static Code getCode(Map map) throws SQLException {
      Code c = new Code();
      
      Urn urn = UrnOrm.getUrn(map);
      c.setUrn(urn);
      
      Category cat = new Category();
      cat.setLabel(SqlUtil.getString("cat_label", map));
      c.setCategory(cat);
      
      c.setId((Integer) map.get("code_id"));
      c.setCategoryId((Integer) map.get("category_id"));
      c.setValue(SqlUtil.getString("value", map));
      c.setSortOrder((Integer) map.get("sort_order"));
      c.setCodeListId((Integer) map.get("code_list_id"));
      c.setModuleVersionId((Integer) map.get("module_version_id"));
      c.setVersionDescription(SqlUtil.getString("version_description", map));
      return c;
   }
   
}

package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.Urn;

public final class CategoryOrm {

   private CategoryOrm() {
   }
   
   public static Category getCategoryFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getCategory(rows[0]);
   }
   
   public static List<Category> getCategoryList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Category> list = new ArrayList<Category>();
      for (SortedMap row : rows) {
         list.add(getCategory(row));
      }
      return list;
   }

   public static Category getCategory(Map map) throws SQLException {
      Category c = new Category();
      
      Urn urn = UrnOrm.getUrn(map);
      c.setUrn(urn);
      
      c.setId((Integer) map.get("category_id"));
      c.setModuleVersionId((Integer) map.get("module_version_id"));
      c.setLabel(SqlUtil.getString("label", map));
      c.setLabelShort(SqlUtil.getString("label_short", map));
      c.setDescription(SqlUtil.getString("description", map));
      c.setVersionDescription(SqlUtil.getString("version_description", map));
      return c;
   }
   
}

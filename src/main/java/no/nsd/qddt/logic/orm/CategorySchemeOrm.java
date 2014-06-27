package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.CategoryScheme;
import no.nsd.qddt.model.Urn;

public final class CategorySchemeOrm {

   private CategorySchemeOrm() {
   }
   
   public static CategoryScheme getCategorySchemeFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getCategoryScheme(rows[0]);
   }
   
   public static List<CategoryScheme> getCategorySchemeList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<CategoryScheme> list = new ArrayList<CategoryScheme>();
      for (SortedMap row : rows) {
         list.add(getCategoryScheme(row));
      }
      return list;
   }

   public static CategoryScheme getCategoryScheme(Map map) throws SQLException {
      CategoryScheme qs = new CategoryScheme();
      
      Urn urn = UrnOrm.getUrn(map);
      qs.setUrn(urn);
      
      qs.setId((Integer) map.get("category_scheme_id"));
      qs.setName(SqlUtil.getString("name", map));
      qs.setDescription(SqlUtil.getString("description", map));
      qs.setVersionDescription(SqlUtil.getString("version_description", map));
      qs.setVersionPublished((Boolean) map.get("version_published"));
      qs.setSurveyDefault((Boolean) map.get("survey_default_scheme"));
      return qs;
   }
   
}

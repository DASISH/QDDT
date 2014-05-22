package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.QuestionScheme;
import no.nsd.qddt.model.Urn;

public final class QuestionSchemeOrm {

   private QuestionSchemeOrm() {
   }
   
   public static QuestionScheme getQuestionSchemeFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getQuestionScheme(rows[0]);
   }
   
   public static List<QuestionScheme> getQuestionSchemeList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<QuestionScheme> list = new ArrayList<QuestionScheme>();
      for (SortedMap row : rows) {
         list.add(getQuestionScheme(row));
      }
      return list;
   }

   public static QuestionScheme getQuestionScheme(Map map) throws SQLException {
      QuestionScheme qs = new QuestionScheme();
      
      Urn urn = UrnOrm.getUrn(map);
      qs.setUrn(urn);
      
      qs.setId((Integer) map.get("question_scheme_id"));
      qs.setModuleVersionId((Integer) map.get("module_version_id"));
      qs.setName(SqlUtil.getString("name", map));
      qs.setLabel(SqlUtil.getString("label", map));
      qs.setDescription(SqlUtil.getString("description", map));
      qs.setVersionDescription(SqlUtil.getString("version_description", map));
      qs.setVersionUpdated((Boolean) map.get("version_updated"));
      return qs;
   }
   
}

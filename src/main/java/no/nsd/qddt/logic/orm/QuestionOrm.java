package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.model.Urn;

public final class QuestionOrm {

   private QuestionOrm() {
   }
   
   public static Question getQuestionFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getQuestion(rows[0]);
   }
   
   public static List<Question> getQuestionList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Question> list = new ArrayList<Question>();
      for (SortedMap row : rows) {
         list.add(getQuestion(row));
      }
      return list;
   }

   public static Question getQuestion(Map map) throws SQLException {
      Question q = new Question();
      
      Urn urn = UrnOrm.getUrn(map);
      q.setUrn(urn);
      
      q.setId((Integer) map.get("question_id"));
      q.setModuleVersionId((Integer) map.get("module_version_id"));
      q.setName(SqlUtil.getString("name", map));
      q.setLabel(SqlUtil.getString("label", map));
      q.setDescription(SqlUtil.getString("description", map));
      q.setQuestionText(SqlUtil.getString("question_text", map));
      q.setQuestionIntent(SqlUtil.getString("question_intent", map));
      q.setVersionDescription(SqlUtil.getString("version_description", map));
      q.setVersionUpdated((Boolean) map.get("version_updated"));
      return q;
   }
   
}

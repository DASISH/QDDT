package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Survey;

public final class SurveyOrm {

   private SurveyOrm() {
   }
   
   public static List<Survey> getSurveyList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Survey> list = new ArrayList<Survey>();
      for (SortedMap row : rows) {
         list.add(getSurvey(row));
      }
      return list;
   }

   public static Survey getSurveyFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getSurvey(rows[0]);
   }
   
   public static Survey getSurvey(Map map) throws SQLException {
      Survey survey = new Survey();
      
      survey.setId((Integer) map.get("survey_id"));
      survey.setName(SqlUtil.getString("name", map));
      survey.setShortName(SqlUtil.getString("short_name", map));
      
      return survey;
   }   
   
   
}

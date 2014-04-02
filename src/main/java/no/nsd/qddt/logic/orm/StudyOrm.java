package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.model.Survey;

public final class StudyOrm {

   private StudyOrm() {
   }
   
   public static List<Study> getStudyList(SortedMap[] rows,Map<Integer, Survey> surveys) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Study> list = new ArrayList<Study>();
      for (SortedMap row : rows) {
         list.add(getStudy(row, surveys));
      }
      return list;
   }

   public static Study getStudyFromFirstRow(SortedMap[] rows, Map<Integer, Survey> surveys) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getStudy(rows[0], surveys);
   }
   
   public static Study getStudy(Map map, Map<Integer, Survey> surveys) throws SQLException {
      Study study = new Study();
      
      study.setId((Integer) map.get("study_id"));
      
      Integer surveyId = (Integer) map.get("survey_id");
      study.setSurvey(surveys.get(surveyId));
      
      study.setTitle(SqlUtil.getString("title", map));
      study.setDescription(SqlUtil.getString("abstract", map));      
      return study;
   }   
   
}

package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.model.Survey;

public class StudyLogic {

   private final Connection conn;
   private final Map<Integer, Survey> surveys;
   
   public StudyLogic(Connection conn) throws SQLException {
      this.conn = conn;
      SurveyLogic logic = new SurveyLogic(conn);
      this.surveys = logic.getSurveyMap();
   }
   
   public List<Study> getStudies() throws SQLException {
      String sql = "select * from study order by title"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return this.getStudyList(rows);
   }

   public Map<Integer, Study> getStudyMap() throws SQLException {
      List<Study> list = getStudies();
      Map<Integer, Study> map = new HashMap<Integer, Study>();
      if (list == null) {
         return null;
      }
      for (Study s : list) {
         map.put(s.getId(), s);
      }
      return map;
   }
   
   public Study getStudy(Integer id) throws SQLException {
      String sql = "select * from study where study_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getStudyFromFirstRow(rows);
   }
   
   
   
   private List<Study> getStudyList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Study> list = new ArrayList<Study>();
      for (SortedMap row : rows) {
         list.add(this.getStudy(row));
      }
      return list;
   }

   private Study getStudyFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getStudy(rows[0]);
   }
   
   private Study getStudy(Map map) throws SQLException {
      Study study = new Study();
      
      study.setId((Integer) map.get("study_id"));
      
      Integer surveyId = (Integer) map.get("survey_id");
      study.setSurvey(this.surveys.get(surveyId));
      
      study.setTitle(SqlUtil.getString("title", map));
      study.setDescription(SqlUtil.getString("abstract", map));      
      return study;
   }   
   
   
   
}

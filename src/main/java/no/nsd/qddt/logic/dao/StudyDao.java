package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.StudyOrm;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.model.Survey;

public class StudyDao {

   private final Connection conn;
   private final Map<Integer, Survey> surveys;
   
   public StudyDao(Connection conn) throws SQLException {
      this.conn = conn;
      SurveyDao logic = new SurveyDao(conn);
      this.surveys = logic.getSurveyMap();
   }
   
   public List<Study> getStudies() throws SQLException {
      String sql = "select * from study order by title"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return StudyOrm.getStudyList(rows, surveys);
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
      return StudyOrm.getStudyFromFirstRow(rows, surveys);
   }
   
   
}

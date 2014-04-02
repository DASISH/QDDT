package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.SurveyOrm;
import no.nsd.qddt.model.Survey;

public class SurveyDao {

   private final Connection conn;
   
   public SurveyDao(Connection conn) {
      this.conn = conn;
   }
   
   public List<Survey> getSurveys() throws SQLException {
      String sql = "select * from survey"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return SurveyOrm.getSurveyList(rows);
   }

   public Map<Integer, Survey> getSurveyMap() throws SQLException {
      List<Survey> list = getSurveys();
      Map<Integer, Survey> map = new HashMap<Integer, Survey>();
      if (list == null) {
         return null;
      }
      for (Survey s : list) {
         map.put(s.getId(), s);
      }
      return map;
   }
   
   public Survey getSurvey(Integer id) throws SQLException {
      String sql = "select * from survey where survey_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return SurveyOrm.getSurveyFromFirstRow(rows);
   }
   
   
   

   
   
   
}

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
import no.nsd.qddt.model.Survey;

public class SurveyLogic {

   private final Connection conn;
   
   public SurveyLogic(Connection conn) {
      this.conn = conn;
   }
   
   public List<Survey> getSurveys() throws SQLException {
      String sql = "select * from survey"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return this.getSurveyList(rows);
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
      return this.getSurveyFromFirstRow(rows);
   }
   
   
   
   private List<Survey> getSurveyList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Survey> list = new ArrayList<Survey>();
      for (SortedMap row : rows) {
         list.add(this.getSurvey(row));
      }
      return list;
   }

   private Survey getSurveyFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getSurvey(rows[0]);
   }
   
   private Survey getSurvey(Map map) throws SQLException {
      Survey survey = new Survey();
      
      survey.setId((Integer) map.get("survey_id"));
      survey.setName(SqlUtil.getString("name", map));
      survey.setShortName(SqlUtil.getString("short_name", map));
      
      return survey;
   }   
   
   
   
}

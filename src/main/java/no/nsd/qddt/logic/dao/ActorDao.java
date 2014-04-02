package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.ActorOrm;
import no.nsd.qddt.model.Actor;

public class ActorDao {

   private final Connection conn;

   public ActorDao(Connection conn) {
      this.conn = conn;
   }
   
   
   public Actor getActorForUserAndModule(Integer userId, Integer moduleId) throws SQLException {
      String sql = "select a.actor_id, a.name from admin_user_module_actor as u "
              + "inner join admin_actor as a on u.actor_id = a.actor_id "
              + "where user_id = ? and module_id = ?";
      List<Integer> values = new ArrayList<Integer>();
      values.add(userId);
      values.add(moduleId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return ActorOrm.getFirstActor(rows);
   }
   
   public Actor getActorForUserSurveyAndAgency(Integer userId, Integer surveyId, Integer agencyId) throws SQLException {
      String sql = "select a.actor_id, a.name "
              + "from admin_user_actor as ua inner join admin_actor as a on ua.actor_id = a.actor_id "
              + "where ua.user_id = ? and a.survey_id = ? and a.agency_id = ?";

      List<Integer> values = new ArrayList<Integer>();
      values.add(userId);
      values.add(surveyId);
      values.add(agencyId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return ActorOrm.getFirstActor(rows);
   }
   
   
   public List<Actor> getActors() throws SQLException {
      String sql = "select * from admin_actor order by name"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return ActorOrm.getActorList(rows);
   }

   public Map<Integer, Actor> getActorMap() throws SQLException {
      List<Actor> list = getActors();
      Map<Integer, Actor> map = new HashMap<Integer, Actor>();
      if (list == null) {
         return null;
      }
      for (Actor a : list) {
         map.put(a.getId(), a);
      }
      return map;
   }
   
   
}

package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Actor;

public class ActorLogic {

   private final Connection conn;
   
   public ActorLogic(Connection conn) {
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
      return this.getFirstActor(rows);
   }
   
   
   public List<Actor> getActors() throws SQLException {
      String sql = "select * from admin_actor order by name"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return this.getActorList(rows);
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
   
   
   
   private List<Actor> getActorList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Actor> list = new ArrayList<Actor>();
      for (SortedMap row : rows) {
         list.add(this.getActor(row));
      }
      return list;
   }
   
   
   
   private Actor getFirstActor(SortedMap[] rows) {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getActor(rows[0]);
   }
   
   private Actor getActor(SortedMap row) {
      Actor actor = new Actor();
      actor.setId((Integer) row.get("actor_id"));
      actor.setName((String) row.get("name"));
      return actor;
   }   
   
}

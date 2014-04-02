package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.model.Actor;

public final class ActorOrm {

   private ActorOrm() {
   }
   
   public static List<Actor> getActorList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Actor> list = new ArrayList<Actor>();
      for (SortedMap row : rows) {
         list.add(getActor(row));
      }
      return list;
   }
   
   public static Actor getFirstActor(SortedMap[] rows) {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getActor(rows[0]);
   }
   
   public static Actor getActor(SortedMap row) {
      Actor actor = new Actor();
      actor.setId((Integer) row.get("actor_id"));
      actor.setName((String) row.get("name"));
      return actor;
   }   
   
   
}

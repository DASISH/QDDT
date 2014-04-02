package no.nsd.qddt.logic.orm;

import java.util.SortedMap;
import no.nsd.qddt.model.User;

public final class UserOrm {

   private UserOrm() {
   }
   
   public static User getFirstUser(SortedMap[] rows) {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getUser(rows[0]);
   }
   
   public static User getUser(SortedMap row) {
      User user = new User();
      user.setId((Integer) row.get("user_id"));
      user.setUsername((String) row.get("username"));
      return user;
   }   
   
   
}

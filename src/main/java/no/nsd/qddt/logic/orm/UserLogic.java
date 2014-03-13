package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.User;

public class UserLogic {

   private final Connection conn;
   
   public UserLogic(Connection conn) {
      this.conn = conn;
   }
   
   public User getUser(String username, String password) throws SQLException {
      String sql = "select * from admin_user where username = ? and password = ?";
      List values = new ArrayList();
      values.add(username);
      values.add(password);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getFirstUser(rows);
   }
   
   private User getFirstUser(SortedMap[] rows) {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getUser(rows[0]);
   }
   
   private User getUser(SortedMap row) {
      User user = new User();
      user.setId((Integer) row.get("user_id"));
      user.setUsername((String) row.get("username"));
      return user;
   }   
   
}

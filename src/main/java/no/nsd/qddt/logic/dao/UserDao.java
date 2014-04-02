package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.UserOrm;
import no.nsd.qddt.model.User;

public class UserDao {

   private final Connection conn;
   
   public UserDao(Connection conn) {
      this.conn = conn;
   }
   
   public User getUser(String username, String password) throws SQLException {
      String sql = "select * from admin_user where username = ? and password = ?";
      List values = new ArrayList();
      values.add(username);
      values.add(password);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return UserOrm.getFirstUser(rows);
   }
   

}

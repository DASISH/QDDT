package no.nsd.qddt.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import javax.servlet.jsp.jstl.sql.Result;
import no.nsd.qddt.model.User;

public class UserLogic {

   private final Connection conn;
   
   public UserLogic(Connection conn) {
      this.conn = conn;
   }
   
   public User getUser(String username, String password) throws SQLException {
      String sqlSelect = "select * from admin_user where username = ? and password = ?";
      List values = new ArrayList();
      values.add(username);
      values.add(password);
      
      SortedMap[] rows = this.executeQuery(sqlSelect, values);
      return this.getFirstUser(rows);
   }
   
   private SortedMap[] executeQuery(String sql, List values) throws SQLException {
      SqlCommand sqlCB = new SqlCommand(conn);
      sqlCB.setSqlString(sql);
      sqlCB.setValues(values);
      Result res = sqlCB.executeQuery();
      SortedMap[] rows = res.getRows();
      return rows;
   }
   
   private User getFirstUser(SortedMap[] rows) {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getUser(rows[0]);
   }
   
   private User getUser(SortedMap rad) {
      User user = new User();
      user.setId((Integer) rad.get("id"));
      user.setUsername((String) rad.get("username"));
      return user;
   }   
   
}

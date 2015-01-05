package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
   
   
   public Set<Integer> getModuleIdsForUser(Integer userId) throws SQLException {
      String sql = "select module_id from dbo.admin_user_module_actor where user_id = ?";
      List values = new ArrayList();
      values.add(userId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      
      if (rows == null || rows.length == 0) {
         return null;
      }
      
      Set<Integer> moduleIds = new HashSet<Integer>();
      
      for (SortedMap row : rows) {
         Integer moduleId = (Integer) row.get("module_id");
         moduleIds.add(moduleId);
      }
      
      return moduleIds;
   }
   

}

package no.nsd.qddt.logic.orm.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;

public class UserPersistenceLogic {

   private final Connection conn;
   
   public UserPersistenceLogic(Connection conn) {
      this.conn = conn;
   }
   
   public void registerNewUserModuleActor(Integer userId, Integer moduleId, Integer actorId) throws SQLException {
      String sql = "insert into "
              + "admin_user_module_actor(user_id, module_id, actor_id) "
              + "values (?, ?, ?)";

      List values = new ArrayList();
      values.add(userId);
      values.add(moduleId);
      values.add(actorId);

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      sqlCommand.executeUpdate();
   }

   
   
}

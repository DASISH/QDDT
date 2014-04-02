package no.nsd.qddt.service;

import java.sql.Connection;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.UserDao;
import no.nsd.qddt.model.User;

public class UserService {
   
   private UserService() {
   }
   
   public static User getUser(String username, String password) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         return getUserFromdb(conn, username, password);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   private static User getUserFromdb(Connection conn, String username, String password) throws Exception {
      UserDao logic = new UserDao(conn);
      return logic.getUser(username, password);
   }

}

package no.nsd.qddt.factories;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class DatabaseConnectionFactory {

   private DatabaseConnectionFactory() {
   }
   
   public static Connection getConnection() throws Exception {
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/qddt");
      return ds.getConnection();
   }
   
}

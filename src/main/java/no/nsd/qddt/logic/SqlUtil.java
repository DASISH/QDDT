package no.nsd.qddt.logic;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public final class SqlUtil {

   private SqlUtil() {
   }
   
   public static void close(Connection conn) {
      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException e) {
         }
      }
   }

   public static void close(Statement stmt) {
      if (stmt != null) {
         try {
            stmt.close();
         } catch (SQLException e) {
         }
      }
   }
   
   public static void close(ResultSet rs) {
      if (rs != null) {
         try {
            rs.close();
         } catch (SQLException e) {
         }
      }
   }

   public static void rollback(Connection conn) {
      if (conn != null) {
         try {
            conn.rollback();
         } catch (SQLException e) {
         }
      }
   }

   public static void setAutoCommitTrue(Connection conn) {
      if (conn != null) {
         try {
            conn.setAutoCommit(true);
         } catch (SQLException e) {
         }
      }
   }
   
   public static String getString(String key, Map map) throws SQLException {
      Object o = map.get(key);
      return clobToString(o);
   }
   
   public static String clobToString(Object o) throws SQLException {
      if (o == null) {
         return null;
      }
      if (o instanceof String) {
         return o.toString();
      }
      if (o instanceof Clob) {
         Clob clob = (Clob) o;
         long length = clob.length();
         return clob.getSubString(1, (int) length);
      }
      return null;
   }

   
}

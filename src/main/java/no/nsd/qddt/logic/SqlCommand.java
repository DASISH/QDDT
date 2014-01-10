package no.nsd.qddt.logic;

import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class SqlCommand {

   private final Connection conn;
   private String sqlString;
   private List values;

   public SqlCommand(Connection conn) {
      this.conn = conn;
   }

   /**
    * Set the SQL string, possibly with question mark placeholders for values
    * set by setValues().
    */
   public void setSqlString(String sqlString) {
      this.sqlString = sqlString;
   }

   /**
    * Sets the values to use for the place holders in the SQL string. The List
    * must contain one Object for each place holder, suitable for use with the
    * PreparedStatement setObject() method.
    */
   public void setValues(List values) {
      this.values = values;
   }

   /**
    * Executes the specified SQL string as a query and returns a Result object.
    *
    * @return a javax.servlet.jsp.jstl.sql.Result object
    * @throws SQLException
    */
   public Result executeQuery() throws SQLException {
      Result result = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Statement stmt = null;
      try {
         if (this.values != null && this.values.size() > 0) {
            // Use a PreparedStatement and set all values
            pstmt = this.conn.prepareStatement(this.sqlString);
            this.setValues(pstmt);
            rs = pstmt.executeQuery();
         } else {
            // Use a regular Statement
            stmt = conn.createStatement();
            rs = stmt.executeQuery(this.sqlString);
         }
         result = ResultSupport.toResult(rs);
      } finally {
         SqlUtil.close(rs);
         SqlUtil.close(stmt);
         SqlUtil.close(pstmt);
      }
      return result;
   }

   /**
    * Executes the specified SQL string (any statement except SELECT, such as
    * UPDATE, INSERT, DELETE or CREATE TABLE) and returns the number of rows
    * affected by the statement, or 0 if none.
    *
    * @return the number of rows affected
    * @throws SQLException
    */
   public int executeUpdate() throws SQLException {
      int noOfRows = 0;
      PreparedStatement pstmt = null;
      Statement stmt = null;
      try {
         if (this.values != null && this.values.size() > 0) {
            // Use a PreparedStatement and set all values
            pstmt = conn.prepareStatement(this.sqlString);
            this.setValues(pstmt);
            noOfRows = pstmt.executeUpdate();
         } else {
            // Use a regular Statement
            stmt = conn.createStatement();
            noOfRows = stmt.executeUpdate(this.sqlString);
         }
      } finally {
         SqlUtil.close(stmt);
         SqlUtil.close(pstmt);
      }
      return noOfRows;
   }

   /**
    * Executes the specified SQL string and returns a Result object.
    *
    * @return a javax.servlet.jsp.jstl.sql.Result object
    * @throws SQLException
    */
   public Result execute() throws SQLException {
      Result result = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Statement stmt = null;
      try {
         if (this.values != null && this.values.size() > 0) {
            // Use a PreparedStatement and set all values
            pstmt = this.conn.prepareStatement(this.sqlString);
            this.setValues(pstmt);
            pstmt.execute();
            while (pstmt.getMoreResults()) {
               rs = pstmt.getResultSet();
               break;
            }
         } else {
            // Use a regular Statement
            stmt = conn.createStatement();
            stmt.execute(this.sqlString);
            rs = stmt.getResultSet();
            while (stmt.getMoreResults()) {
               rs = stmt.getResultSet();
               break;
            }
         }
         if (rs != null) {
            result = ResultSupport.toResult(rs);
         }
      } finally {
         SqlUtil.close(rs);
         SqlUtil.close(stmt);
         SqlUtil.close(pstmt);
      }
      return result;
   }

   /**
    * Calls setObject() method on the PreparedStatement for all objects in the
    * values list of this bean.
    *
    * @param pstmt the PreparedStatement
    * @throws SQLException
    */
   private void setValues(PreparedStatement pstmt) throws SQLException {
      for (int i = 0; i < this.values.size(); i++) {
         Object v = this.values.get(i);
         // Set the value using the method corresponding to the type.
         // Note! Set methods are indexed from 1, so we add 1 to i
         pstmt.setObject(i + 1, v);
      }
   }
}

package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.CommentSourceOrm;
import no.nsd.qddt.model.CommentSource;

public class CommentSourceDao {

   private final Connection conn;
   
   public CommentSourceDao(Connection conn) {
      this.conn = conn;
   }
   
   public List<CommentSource> getCommentSources(Integer surveyId) throws SQLException {
      String sql = "select * from comment_source where survey_id = ? order by sort_order"; 
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(surveyId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CommentSourceOrm.getCommentSourceList(rows);
   }

   public SortedMap<Integer, CommentSource> getCommentSourceMap(Integer surveyId) throws SQLException {
      List<CommentSource> list = getCommentSources(surveyId);
      SortedMap<Integer, CommentSource> map = new TreeMap<Integer, CommentSource>();
      if (list == null) {
         return null;
      }
      for (CommentSource cs : list) {
         map.put(cs.getId(), cs);
      }
      return map;
   }
   
   public CommentSource getCommentSource(Integer id) throws SQLException {
      String sql = "select * from comment_source where comment_source_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return CommentSourceOrm.getCommentSourceFromFirstRow(rows);
   }
   
   

   
   
}

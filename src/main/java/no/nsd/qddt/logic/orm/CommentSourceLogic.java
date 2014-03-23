package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.CommentSource;

public class CommentSourceLogic {

   private final Connection conn;
   
   public CommentSourceLogic(Connection conn) {
      this.conn = conn;
   }
   
   public List<CommentSource> getCommentSources(Integer surveyId) throws SQLException {
      String sql = "select * from comment_source where survey_id = ? order by sort_order"; 
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(surveyId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getCommentSourceList(rows);
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
      return this.getCommentSourceFromFirstRow(rows);
   }
   
   
   private List<CommentSource> getCommentSourceList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<CommentSource> list = new ArrayList<CommentSource>();
      for (SortedMap row : rows) {
         list.add(this.getCommentSource(row));
      }
      return list;
   }

   private CommentSource getCommentSourceFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getCommentSource(rows[0]);
   }
   
   private CommentSource getCommentSource(Map map) throws SQLException {
      CommentSource cs = new CommentSource();
      
      cs.setId((Integer) map.get("comment_source_id"));
      cs.setText(SqlUtil.getString("source_text", map));
      cs.setDescription(SqlUtil.getString("source_description", map));
      cs.setSortOrder((Integer) map.get("sort_order"));
      
      return cs;
   }   
   
   
   
}

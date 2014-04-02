package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.CommentSource;

public final class CommentSourceOrm {

   private CommentSourceOrm() {
   }
   
   public static List<CommentSource> getCommentSourceList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<CommentSource> list = new ArrayList<CommentSource>();
      for (SortedMap row : rows) {
         list.add(getCommentSource(row));
      }
      return list;
   }

   public static CommentSource getCommentSourceFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getCommentSource(rows[0]);
   }
   
   public static CommentSource getCommentSource(Map map) throws SQLException {
      CommentSource cs = new CommentSource();
      
      cs.setId((Integer) map.get("comment_source_id"));
      cs.setText(SqlUtil.getString("source_text", map));
      cs.setDescription(SqlUtil.getString("source_description", map));
      cs.setSortOrder((Integer) map.get("sort_order"));
      
      return cs;
   }   
   
   
}

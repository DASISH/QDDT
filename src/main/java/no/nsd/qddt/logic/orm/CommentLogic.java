package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Urn;

public class CommentLogic {

   private final Connection conn;
   
   public CommentLogic(Connection conn) {
      this.conn = conn;
   }

   public List<Comment> getComments(Integer moduleId, Integer conceptId) throws SQLException {
      String sql = "select * from comment where module_id = ? and element_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleId);
      values.add(conceptId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      List<Comment> list = this.getCommentList(rows);
      return list;
   }
   
   private List<Comment> getCommentList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Comment> comments = new ArrayList<Comment>();
      for (SortedMap row : rows) {
         comments.add(this.getComment(row));
      }
      return comments;
   }
   
   private Comment getComment(Map map) throws SQLException {
      Comment comment = new Comment();
      comment.setId((Integer) map.get("comment_id"));
      comment.setText(SqlUtil.getString("comment_text", map));
      comment.setSource(SqlUtil.getString("comment_source", map));
      return comment;
   }   
   
   private Urn getUrn(Map map) throws SQLException {
      Urn urn = new Urn();
      urn.setAgency(SqlUtil.getString("urn_agency", map));
      urn.setId(SqlUtil.getString("urn_id", map));
      urn.setVersion(SqlUtil.getString("urn_version", map));
      return urn;
   }   
   
   
}

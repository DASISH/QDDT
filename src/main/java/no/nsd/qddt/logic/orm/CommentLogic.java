package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Urn;

public class CommentLogic {

   private final Connection conn;
   private final Map<Integer, Actor> actorMap;
   
   public CommentLogic(Connection conn) throws SQLException {
      this.conn = conn;
      ActorLogic logic = new ActorLogic(conn);
      this.actorMap = logic.getActorMap();
   }

   public List<Comment> getCommentsForElementVersionAndModuleVersion(Urn elementUrn, Integer elementId, Integer moduleVersionId) throws SQLException {
      String sql = "select * from comment where "
              + "element_agency_id = ? and "
              + "element_urn_id = ? and "
              + "element_id = ? and "
              + "module_version_id = ? "
              + "order by comment_date";
      
      List values = new ArrayList();
      values.add(elementUrn.getAgency().getId());
      values.add(elementUrn.getId());
      values.add(elementId);
      values.add(moduleVersionId);
      
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
      Urn urn = new Urn();
      Agency agency = new Agency();
      urn.setAgency(agency);
      comment.setElementUrn(urn);
      
      comment.setId((Integer) map.get("comment_id"));
      comment.setText(SqlUtil.getString("comment_text", map));
      comment.setDate((Date) map.get("comment_date"));
      urn.setId(SqlUtil.getString("element_urn_id", map));
      agency.setId((Integer) map.get("element_agency_id"));
      comment.setElementId((Integer) map.get("element_id"));
      comment.setSourceId((Integer) map.get("comment_source_id"));
      comment.setModuleVersionId((Integer) map.get("module_version_id"));
      
      Integer actorId = (Integer) map.get("actor_id");
      Actor actor = actorMap.get(actorId);
      comment.setActor(actor);
      
      return comment;
   }   
   
   
   
}

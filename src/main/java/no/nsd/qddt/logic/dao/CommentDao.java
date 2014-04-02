package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.CommentOrm;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Urn;

public class CommentDao {

   private final Connection conn;
   private final Map<Integer, Actor> actorMap;
   
   public CommentDao(Connection conn) throws SQLException {
      this.conn = conn;
      ActorDao logic = new ActorDao(conn);
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
      List<Comment> list = CommentOrm.getCommentList(rows, actorMap);
      return list;
   }
   

   
   
}

package no.nsd.qddt.logic.dao.persist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Comment;

public class CommentDaoPersist {

   private final Connection conn;
   
   public CommentDaoPersist(Connection conn) {
      this.conn = conn;
   }
   
   public void registerNewComment(Comment comment) throws SQLException {
      String sql = "insert into "
              + "comment(element_agency_id, "
              + "element_urn_id, "
              + "element_id, "
              + "comment_source_id, "
              + "comment_text, "
              + "comment_date, "
              + "actor_id, "
              + "user_id, "
              + "module_version_id) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(comment.getElementUrn().getAgency().getId());
      values.add(comment.getElementUrn().getId());
      values.add(comment.getElementId());
      values.add(comment.getSourceId());
      values.add(comment.getText());
      values.add(comment.getSqlDate());
      values.add(comment.getActor().getId());
      values.add(comment.getUser().getId());
      values.add(comment.getModuleVersionId());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      sqlCommand.executeUpdate();
   }

   
}

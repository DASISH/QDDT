package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Urn;

public class CommentService {
   
   private final DaoManager daoManager;
   
   public CommentService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }
   
   public List<Comment> getCommentsForElementVersionAndModuleVersion(Urn elementUrn, Integer elementId, Integer moduleVersionId) throws SQLException {
      return daoManager.getCommentDao().getCommentsForElementVersionAndModuleVersion(elementUrn, elementId, moduleVersionId);
   }


   public void registerNewComment(Comment comment) throws SQLException {
      daoManager.getCommentDaoUpdate().registerNewComment(comment);
   }
   
   
}

package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.SortedMap;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.CommentSource;

public class CommentSourceService {
   
   private final DaoManager daoManager;
   
   public CommentSourceService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }
   
   public SortedMap<Integer, CommentSource> getCommentSourceMap(Integer surveyId) throws SQLException {
      return daoManager.getCommentSourceDao().getCommentSourceMap(surveyId);
   }


}

package no.nsd.qddt.service;

import java.sql.SQLException;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.QuestionScheme;

public class QuestionSchemeService {

   private final DaoManager daoManager;

   public QuestionSchemeService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public QuestionScheme getQuestionScheme(Integer questionSchemeId) throws SQLException {
      QuestionScheme questionScheme = daoManager.getQuestionSchemeDao().getQuestionScheme(questionSchemeId);
      
      if (questionScheme != null) {
         //daoManager.getConceptDao().getConceptsForScheme(conceptScheme);
      }
      
      return questionScheme;
   }


}

package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Survey;

public class SurveyService {

   private final DaoManager daoManager;
   
   public SurveyService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public List<Survey> getSurveys() throws SQLException {
      return daoManager.getSurveyDao().getSurveys();
   }

   public Survey getSurvey(Integer surveyId) throws SQLException {
      return daoManager.getSurveyDao().getSurvey(surveyId);
   }
   
   

}

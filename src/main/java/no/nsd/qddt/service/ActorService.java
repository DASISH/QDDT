package no.nsd.qddt.service;

import java.sql.SQLException;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Actor;

public class ActorService {

   private final DaoManager daoManager;
   
   public ActorService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public Actor getActorForUserAndModule(Integer userId, Integer moduleId) throws SQLException {
      return daoManager.getActorDao().getActorForUserAndModule(userId, moduleId);
   }

   public Actor getActorForUserSurveyAndAgency(Integer userId, Integer surveyId, Integer agencyId) throws SQLException {
      return daoManager.getActorDao().getActorForUserSurveyAndAgency(userId, surveyId, agencyId);
   }

}

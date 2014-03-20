package no.nsd.qddt.service;

import java.sql.Connection;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.ActorLogic;
import no.nsd.qddt.model.Actor;

public class ActorService {

   private ActorService() {
   }

   public static Actor getActorForUserAndModule(Integer userId, Integer moduleId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ActorLogic logic = new ActorLogic(conn);
         return logic.getActorForUserAndModule(userId, moduleId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static Actor getActorForUserSurveyAndAgency(Integer userId, Integer surveyId, Integer agencyId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ActorLogic logic = new ActorLogic(conn);
         return logic.getActorForUserSurveyAndAgency(userId, surveyId, agencyId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

}

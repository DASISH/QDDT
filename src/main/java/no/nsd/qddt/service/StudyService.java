package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Study;

public class StudyService {

   private final DaoManager daoManager;
   
   public StudyService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public List<Study> getStudies() throws SQLException {
      return daoManager.getStudyDao().getStudies();
   }

   public Study getStudy(Integer studyId) throws SQLException {
      return daoManager.getStudyDao().getStudy(studyId);
   }

}

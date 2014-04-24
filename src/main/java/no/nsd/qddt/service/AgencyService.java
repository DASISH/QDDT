package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Agency;

public class AgencyService {

   private final DaoManager daoManager;
   
   public AgencyService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public List<Agency> getAgencies() throws SQLException {
      return daoManager.getAgencyDao().getAgencies();
   }

}

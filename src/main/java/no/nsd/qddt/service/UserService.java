package no.nsd.qddt.service;

import java.sql.SQLException;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.User;

public class UserService {
   
   private final DaoManager daoManager;
   
   public UserService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }
   
   public User getUser(String username, String password) throws SQLException {
      return daoManager.getUserDao().getUser(username, password);
   }
   

}

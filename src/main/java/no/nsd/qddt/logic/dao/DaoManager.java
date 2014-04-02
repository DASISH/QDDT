package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.persist.ConceptDaoPersist;
import no.nsd.qddt.logic.dao.persist.ConceptSchemeDaoPersist;

public class DaoManager {

   private final DataSource dataSource;
   private Connection connection;

   private ActorDao actorDao;
   private ConceptDao conceptDao;
   private ConceptDaoPersist conceptDaoPersist;
   private ConceptSchemeDao conceptSchemeDao;
   private ConceptSchemeDaoPersist conceptSchemeDaoPersist;

   
   public DaoManager(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public static DaoManager createDaoManager() throws Exception {
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/qddt");
      DaoManager daoManager = new DaoManager(ds);
      return daoManager;
   }
   
   
   public ActorDao getActorDao() throws SQLException {
      if (actorDao == null) {
         actorDao = new ActorDao(this.getConnection());
      }
      return actorDao;
   }

   public ConceptDao getConceptDao() throws SQLException {
      if (conceptDao == null) {
         conceptDao = new ConceptDao(this.getConnection());
      }
      return conceptDao;
   }

   public ConceptDaoPersist getConceptDaoPersist() throws SQLException {
      if (conceptDaoPersist == null) {
         conceptDaoPersist = new ConceptDaoPersist(this.getConnection());
      }
      return conceptDaoPersist;
   }

   public ConceptSchemeDao getConceptSchemeDao() throws SQLException {
      if (conceptSchemeDao == null) {
         conceptSchemeDao = new ConceptSchemeDao(this.getConnection());
      }
      return conceptSchemeDao;
   }

   public ConceptSchemeDaoPersist getConceptSchemeDaoPersist() throws SQLException {
      if (conceptSchemeDaoPersist == null) {
         conceptSchemeDaoPersist = new ConceptSchemeDaoPersist(this.getConnection());
      }
      return conceptSchemeDaoPersist;
   }


   public void beginTransaction() throws SQLException {
      this.getConnection().setAutoCommit(false);
   }
   public void endTransaction() throws SQLException {
      connection.commit();
      connection.setAutoCommit(true);
   }
   public void abortTransaction() {
      SqlUtil.rollback(connection);
      SqlUtil.setAutoCommitTrue(connection);
   }
   
   public void close() {
      SqlUtil.close(connection);
   }
   

   private Connection getConnection() throws SQLException {
      if (connection == null) {
         connection = dataSource.getConnection();
      }
      return connection;
   }
   
   
}

package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.update.CategoryDaoUpdate;
import no.nsd.qddt.logic.dao.update.CategorySchemeDaoUpdate;
import no.nsd.qddt.logic.dao.update.CodeDaoUpdate;
import no.nsd.qddt.logic.dao.update.CodeListDaoUpdate;
import no.nsd.qddt.logic.dao.update.CommentDaoUpdate;
import no.nsd.qddt.logic.dao.update.ConceptDaoUpdate;
import no.nsd.qddt.logic.dao.update.ConceptSchemeDaoUpdate;
import no.nsd.qddt.logic.dao.update.ModuleDaoUpdate;
import no.nsd.qddt.logic.dao.update.ModuleVersionDaoUpdate;
import no.nsd.qddt.logic.dao.update.QuestionDaoUpdate;
import no.nsd.qddt.logic.dao.update.QuestionSchemeDaoUpdate;
import no.nsd.qddt.logic.dao.update.UserDaoUpdate;

public class DaoManager {

   private final DataSource dataSource;
   private Connection connection;
   
   public DaoManager(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public static DaoManager createDaoManager() throws Exception {
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/qddt");
      return new DaoManager(ds);
   }
   
   
   public ActorDao getActorDao() throws SQLException {
      return new ActorDao(this.getConnection());
   }

   public ConceptDao getConceptDao() throws SQLException {
      return new ConceptDao(this.getConnection());
   }
   
   public ConceptDaoUpdate getConceptDaoUpdate() throws SQLException {
      return new ConceptDaoUpdate(this.getConnection());
   }
   
   public ConceptSchemeDao getConceptSchemeDao() throws SQLException {
      return new ConceptSchemeDao(this.getConnection());
   }

   public ConceptSchemeDaoUpdate getConceptSchemeDaoUpdate() throws SQLException {
      return new ConceptSchemeDaoUpdate(this.getConnection());
   }

   public ModuleVersionDao getModuleVersionDao() throws SQLException {
      return new ModuleVersionDao(this.getConnection());
   }

   public ModuleVersionDaoUpdate getModuleVersionDaoUpdate() throws SQLException {
      return new ModuleVersionDaoUpdate(this.getConnection());
   }

   public ModuleDao getModuleDao() throws SQLException {
      return new ModuleDao(this.getConnection());
   }

   public ModuleDaoUpdate getModuleDaoUpdate() throws SQLException {
      return new ModuleDaoUpdate(this.getConnection());
   }

   public UserDao getUserDao() throws SQLException {
      return new UserDao(this.getConnection());
   }

   public UserDaoUpdate getUserDaoUpdate() throws SQLException {
      return new UserDaoUpdate(this.getConnection());
   }

   public AgencyDao getAgencyDao() throws SQLException {
      return new AgencyDao(this.getConnection());
   }

   public StudyDao getStudyDao() throws SQLException {
      return new StudyDao(this.getConnection());
   }

   public CommentSourceDao getCommentSourceDao() throws SQLException {
      return new CommentSourceDao(this.getConnection());
   }

   public CommentDao getCommentDao() throws SQLException {
      return new CommentDao(this.getConnection());
   }

   public CommentDaoUpdate getCommentDaoUpdate() throws SQLException {
      return new CommentDaoUpdate(this.getConnection());
   }

   public QuestionSchemeDao getQuestionSchemeDao() throws SQLException {
      return new QuestionSchemeDao(this.getConnection());
   }

   public QuestionDao getQuestionDao() throws SQLException {
      return new QuestionDao(this.getConnection());
   }

   public QuestionSchemeDaoUpdate getQuestionSchemeDaoUpdate() throws SQLException {
      return new QuestionSchemeDaoUpdate(this.getConnection());
   }

   public QuestionDaoUpdate getQuestionDaoUpdate() throws SQLException {
      return new QuestionDaoUpdate(this.getConnection());
   }

   public SurveyDao getSurveyDao() throws SQLException {
      return new SurveyDao(this.getConnection());
   }

   public CategoryDao getCategoryDao() throws SQLException {
      return new CategoryDao(this.getConnection());
   }

   public CategoryDaoUpdate getCategoryDaoUpdate() throws SQLException {
      return new CategoryDaoUpdate(this.getConnection());
   }

   public CategorySchemeDao getCategorySchemeDao() throws SQLException {
      return new CategorySchemeDao(this.getConnection());
   }

   public CategorySchemeDaoUpdate getCategorySchemeDaoUpdate() throws SQLException {
      return new CategorySchemeDaoUpdate(this.getConnection());
   }
   
   public CodeListDao getCodeListDao() throws SQLException {
      return new CodeListDao(this.getConnection());
   }

   public CodeListDaoUpdate getCodeListDaoUpdate() throws SQLException {
      return new CodeListDaoUpdate(this.getConnection());
   }

   public CodeDao getCodeDao() throws SQLException {
      return new CodeDao(this.getConnection());
   }

   public CodeDaoUpdate getCodeDaoUpdate() throws SQLException {
      return new CodeDaoUpdate(this.getConnection());
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

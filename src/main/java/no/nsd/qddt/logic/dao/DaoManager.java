package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.update.CategoryDaoUpdate;
import no.nsd.qddt.logic.dao.update.CategorySchemeDaoUpdate;
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

   private ActorDao actorDao;
   private ConceptDao conceptDao;
   private ConceptDaoUpdate conceptDaoUpdate;
   private ConceptSchemeDao conceptSchemeDao;
   private ConceptSchemeDaoUpdate conceptSchemeDaoUpdate;
   private ModuleVersionDao moduleVersionDao;
   private ModuleVersionDaoUpdate moduleVersionDaoUpdate;
   private ModuleDao moduleDao;
   private ModuleDaoUpdate moduleDaoUpdate;
   private UserDao userDao;
   private UserDaoUpdate userDaoUpdate;
   private AgencyDao agencyDao;
   private StudyDao studyDao;
   private CommentSourceDao commentSourceDao;
   private CommentDao commentDao;
   private CommentDaoUpdate commentDaoUpdate;
   private QuestionSchemeDao questionSchemeDao;
   private QuestionDao questionDao;
   private QuestionSchemeDaoUpdate questionSchemeDaoUpdate;
   private QuestionDaoUpdate questionDaoUpdate;
   private SurveyDao surveyDao;
   private CategoryDao categoryDao;
   private CategoryDaoUpdate categoryDaoUpdate;
   private CategorySchemeDao categorySchemeDao;
   private CategorySchemeDaoUpdate categorySchemeDaoUpdate;

   
   public DaoManager(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public static DaoManager createDaoManager() throws Exception {
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/qddt");
      return new DaoManager(ds);
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

   public ConceptDaoUpdate getConceptDaoUpdate() throws SQLException {
      if (conceptDaoUpdate == null) {
         conceptDaoUpdate = new ConceptDaoUpdate(this.getConnection());
      }
      return conceptDaoUpdate;
   }

   public ConceptSchemeDao getConceptSchemeDao() throws SQLException {
      if (conceptSchemeDao == null) {
         conceptSchemeDao = new ConceptSchemeDao(this.getConnection());
      }
      return conceptSchemeDao;
   }

   public ConceptSchemeDaoUpdate getConceptSchemeDaoUpdate() throws SQLException {
      if (conceptSchemeDaoUpdate == null) {
         conceptSchemeDaoUpdate = new ConceptSchemeDaoUpdate(this.getConnection());
      }
      return conceptSchemeDaoUpdate;
   }

   public ModuleVersionDao getModuleVersionDao() throws SQLException {
      if (moduleVersionDao == null) {
         moduleVersionDao = new ModuleVersionDao(this.getConnection());
      }
      return moduleVersionDao;
   }

   public ModuleVersionDaoUpdate getModuleVersionDaoUpdate() throws SQLException {
      if (moduleVersionDaoUpdate == null) {
         moduleVersionDaoUpdate = new ModuleVersionDaoUpdate(this.getConnection());
      }
      return moduleVersionDaoUpdate;
   }

   public ModuleDao getModuleDao() throws SQLException {
      if (moduleDao == null) {
         moduleDao = new ModuleDao(this.getConnection());
      }
      return moduleDao;
   }

   public ModuleDaoUpdate getModuleDaoUpdate() throws SQLException {
      if (moduleDaoUpdate == null) {
         moduleDaoUpdate = new ModuleDaoUpdate(this.getConnection());
      }
      return moduleDaoUpdate;
   }

   public UserDao getUserDao() throws SQLException {
      if (userDao == null) {
         userDao = new UserDao(this.getConnection());
      }
      return userDao;
   }

   public UserDaoUpdate getUserDaoUpdate() throws SQLException {
      if (userDaoUpdate == null) {
         userDaoUpdate = new UserDaoUpdate(this.getConnection());
      }
      return userDaoUpdate;
   }

   public AgencyDao getAgencyDao() throws SQLException {
      if (agencyDao == null) {
         agencyDao = new AgencyDao(this.getConnection());
      }
      return agencyDao;
   }

   public StudyDao getStudyDao() throws SQLException {
      if (studyDao == null) {
         studyDao = new StudyDao(this.getConnection());
      }
      return studyDao;
   }

   public CommentSourceDao getCommentSourceDao() throws SQLException {
      if (commentSourceDao == null) {
         commentSourceDao = new CommentSourceDao(this.getConnection());
      }
      return commentSourceDao;
   }

   public CommentDao getCommentDao() throws SQLException {
      if (commentDao == null) {
         commentDao = new CommentDao(this.getConnection());
      }
      return commentDao;
   }

   public CommentDaoUpdate getCommentDaoUpdate() throws SQLException {
      if (commentDaoUpdate == null) {
         commentDaoUpdate = new CommentDaoUpdate(this.getConnection());
      }
      return commentDaoUpdate;
   }

   public QuestionSchemeDao getQuestionSchemeDao() throws SQLException {
      if (questionSchemeDao == null) {
         questionSchemeDao = new QuestionSchemeDao(this.getConnection());
      }
      return questionSchemeDao;
   }

   public QuestionDao getQuestionDao() throws SQLException {
      if (questionDao == null) {
         questionDao = new QuestionDao(this.getConnection());
      }
      return questionDao;
   }

   public QuestionSchemeDaoUpdate getQuestionSchemeDaoUpdate() throws SQLException {
      if (questionSchemeDaoUpdate == null) {
         questionSchemeDaoUpdate = new QuestionSchemeDaoUpdate(this.getConnection());
      }
      return questionSchemeDaoUpdate;
   }

   public QuestionDaoUpdate getQuestionDaoUpdate() throws SQLException {
      if (questionDaoUpdate == null) {
         questionDaoUpdate = new QuestionDaoUpdate(this.getConnection());
      }
      return questionDaoUpdate;
   }

   public SurveyDao getSurveyDao() throws SQLException {
      if (surveyDao == null) {
         surveyDao = new SurveyDao(this.getConnection());
      }
      return surveyDao;
   }

   public CategoryDao getCategoryDao() throws SQLException {
      if (categoryDao == null) {
         categoryDao = new CategoryDao(this.getConnection());
      }
      return categoryDao;
   }

   public CategoryDaoUpdate getCategoryDaoUpdate() throws SQLException {
      if (categoryDaoUpdate == null) {
         categoryDaoUpdate = new CategoryDaoUpdate(this.getConnection());
      }
      return categoryDaoUpdate;
   }

   public CategorySchemeDao getCategorySchemeDao() throws SQLException {
      if (categorySchemeDao == null) {
         categorySchemeDao = new CategorySchemeDao(this.getConnection());
      }
      return categorySchemeDao;
   }

   public CategorySchemeDaoUpdate getCategorySchemeDaoUpdate() throws SQLException {
      if (categorySchemeDaoUpdate == null) {
         categorySchemeDaoUpdate = new CategorySchemeDaoUpdate(this.getConnection());
      }
      return categorySchemeDaoUpdate;
   }
   
   public CodeListDao getCodeListDao() throws SQLException {
      return new CodeListDao(this.getConnection());
   }

   public CodeListDaoUpdate getCodeListDaoUpdate() throws SQLException {
      return new CodeListDaoUpdate(this.getConnection());
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

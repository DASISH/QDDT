package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.CategoryScheme;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.QuestionScheme;

public class ModuleVersionDaoUpdate {

   private final Connection conn;
   
   public ModuleVersionDaoUpdate(Connection conn) {
      this.conn = conn;
   }

   
   public Integer registerNewModuleVersion(ModuleVersion mv) throws SQLException {
      String sql = "insert into "
              + "module_version(module_id, "
              + "actor_id, "
              + "module_status, "
              + "urn_version, "
              + "module_title, "
              + "module_authors, "
              + "module_authors_affiliation, "
              + "module_abstract, "
              + "concept_scheme_id, "
              + "question_scheme_id, "
              + "category_scheme_id) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(mv.getModule().getId());
      values.add(mv.getActor().getId());
      values.add(mv.getStatus());
      values.add(mv.getUrnVersion());
      values.add(mv.getTitle());
      values.add(mv.getAuthors());
      values.add(mv.getAuthorsAffiliation());
      values.add(mv.getModuleAbstract());
      values.add(mv.getConceptSchemeId());
      values.add(mv.getQuestionSchemeId());
      values.add(mv.getCategorySchemeId());

      
      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer moduleVersionId = sqlCommand.executeAndReturnGeneratedKey();
      return moduleVersionId;
   }
   

   public void updateTitle(ModuleVersion mv) throws SQLException {
      String sql = "update module_version set "
              + "module_title = ?, "
              + "module_authors = ?, "
              + "module_authors_affiliation = ?, "
              + "module_abstract = ? "
              + "where module_version_id = ?";

      List values = new ArrayList();
      values.add(mv.getTitle());
      values.add(mv.getAuthors());
      values.add(mv.getAuthorsAffiliation());
      values.add(mv.getModuleAbstract());
      values.add(mv.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateVersionInfo(ModuleVersion mv) throws SQLException {
      String sql = "update module_version set "
              + "version_number = ?, "
              + "version_description = ?, "
              + "module_status = ? "
              + "where module_version_id = ?";

      List values = new ArrayList();
      values.add(mv.getVersionNumber());
      values.add(mv.getVersionDescription());
      values.add(mv.getStatus());
      values.add(mv.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   

   public void updateConceptScheme(ConceptScheme conceptScheme) throws SQLException {
      String sql = "update module_version set "
              + "concept_scheme_id = ? "
              + "where module_version_id = ?";

      List values = new ArrayList();
      values.add(conceptScheme.getId());
      values.add(conceptScheme.getModuleVersionId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   public void updateQuestionScheme(QuestionScheme questionScheme) throws SQLException {
      String sql = "update module_version set "
              + "question_scheme_id = ? "
              + "where module_version_id = ?";

      List values = new ArrayList();
      values.add(questionScheme.getId());
      values.add(questionScheme.getModuleVersionId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateCategoryScheme(CategoryScheme categoryScheme) throws SQLException {
      String sql = "update module_version set "
              + "category_scheme_id = ? "
              + "where module_version_id = ?";

      List values = new ArrayList();
      values.add(categoryScheme.getId());
      values.add(categoryScheme.getModuleVersionId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
}

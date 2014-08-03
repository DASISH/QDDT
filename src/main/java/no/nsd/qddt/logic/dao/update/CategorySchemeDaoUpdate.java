package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.CategoryScheme;

public class CategorySchemeDaoUpdate {

   private final Connection conn;

   public CategorySchemeDaoUpdate(Connection conn) {
      this.conn = conn;
   }

   public void registerNewCategoryScheme(CategoryScheme categoryScheme) throws SQLException {
      String sql = "insert into "
              + "category_scheme(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "description, "
              + "version_description, "
              + "module_default_scheme, "
              + "version_published) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(categoryScheme.getUrn().getAgency().getId());
      values.add(categoryScheme.getModuleVersionId());
      values.add(categoryScheme.getUrn().getId());
      values.add(categoryScheme.getUrn().getVersion());
      values.add(categoryScheme.getName());
      values.add(categoryScheme.getDescription());
      values.add(categoryScheme.getVersionDescription());
      values.add(categoryScheme.isModuleDefaultScheme());
      values.add(categoryScheme.isVersionPublished());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer generatedId = sqlCommand.executeAndReturnGeneratedKey();
      categoryScheme.setId(generatedId);
   }


   public void updateCategoryScheme(CategoryScheme categoryScheme) throws SQLException {
      String sql = "update avtegory_scheme set "
              + "name = ?, "
              + "description = ?, "
              + "version_description = ?, "
              + "survey_default_scheme = ?, "
              + "version_published = ? "
              + "where category_scheme_id = ?";

      List values = new ArrayList();
      values.add(categoryScheme.getName());
      values.add(categoryScheme.getDescription());
      values.add(categoryScheme.getVersionDescription());
      values.add(categoryScheme.isModuleDefaultScheme());
      values.add(categoryScheme.isVersionPublished());

      values.add(categoryScheme.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void addCategoryToScheme(Category category, Integer categorySchemeId) throws SQLException {
      String sql = "insert into "
              + "category_in_scheme(category_scheme_id, "
              + "category_id) "
              + "values (?, ?)";

      List values = new ArrayList();
      values.add(categorySchemeId);
      values.add(category.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   public void deleteCategoryFromScheme(Category category, Integer categorySchemeId) throws SQLException {
      String sql = "delete from category_in_scheme where category_id = ? and category_scheme_id = ?";

      List values = new ArrayList();
      values.add(category.getId());
      values.add(categorySchemeId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   public void setCategorySchemeUpdated(Integer categorySchemeId) throws SQLException {
      String sql = "update category_scheme set "
              + "version_updated = ? "
              + "where category_scheme_id = ?";

      List values = new ArrayList();
      values.add(Boolean.TRUE);
      values.add(categorySchemeId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

}

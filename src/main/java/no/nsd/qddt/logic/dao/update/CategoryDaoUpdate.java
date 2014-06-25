package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Category;

public class CategoryDaoUpdate {

   private final Connection conn;
   
   public CategoryDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   
   public Integer registerNewCategory(Category category) throws SQLException {
      String sql = "insert into "
              + "category(agency_id, "
              + "urn_id, "
              + "urn_version, "
              + "label, "
              + "label_short, "
              + "description, "
              + "version_description, "
              + "version_updated) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(category.getUrn().getAgency().getId());
      values.add(category.getUrn().getId());
      values.add(category.getUrn().getVersion());

      values.add(category.getLabel());
      values.add(category.getLabelShort());
      values.add(category.getDescription());
      values.add(category.getVersionDescription());
      values.add(category.getVersionUpdated());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer categoryId = sqlCommand.executeAndReturnGeneratedKey();
      return categoryId;
   }

   public void updateCategory(Category category) throws SQLException {
      String sql = "update category set "
              + "label = ?, "
              + "label_short = ?, "
              + "description = ?, "
              + "version_description = ?, "
              + "version_updated = ? "
              + "where category_id = ?";

      List values = new ArrayList();
      values.add(category.getLabel());
      values.add(category.getLabelShort());
      values.add(category.getDescription());
      values.add(category.getVersionDescription());
      values.add(category.getVersionUpdated());

      values.add(category.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   

   public void deleteCategory(Category category) throws SQLException {
      String sql = "delete from category where category_id = ?";

      List values = new ArrayList();
      values.add(category.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   
}

package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Code;

public class CodeDaoUpdate {

   private final Connection conn;
   
   public CodeDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   
   public Integer registerNewCode(Code code) throws SQLException {
      String sql = "insert into "
              + "code(agency_id, "
              + "urn_id, "
              + "urn_version, "
              + "category_id, "
              + "value, "
              + "module_version_id, "
              + "version_date, "
              + "version_description) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(code.getUrn().getAgency().getId());
      values.add(code.getUrn().getId());
      values.add(code.getUrn().getVersion());

      values.add(code.getCategoryId());
      values.add(code.getValue());
      values.add(code.getModuleVersionId());
      values.add(SqlUtil.getDateNow());
      values.add(code.getVersionDescription());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer newId = sqlCommand.executeAndReturnGeneratedKey();
      return newId;
   }

   public void updateCode(Code code) throws SQLException {
      String sql = "update code set "
              + "value = ?, "
              + "version_date = ?, "
              + "version_description = ? "
              + "where code_id = ?";

      List values = new ArrayList();
      values.add(code.getValue());
      values.add(SqlUtil.getDateNow());
      values.add(code.getVersionDescription());

      values.add(code.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   

   public void updateCategoryForCode(Code code) throws SQLException {
      String sql = "update code set "
              + "category_id = ?, "
              + "version_date = ? "
              + "where code_id = ?";

      List values = new ArrayList();

      values.add(code.getCategoryId());
      values.add(SqlUtil.getDateNow());

      values.add(code.getId());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   public void deleteCode(Code code) throws SQLException {
      String sql = "delete from code where code_id = ?";

      List values = new ArrayList();
      values.add(code.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   
}

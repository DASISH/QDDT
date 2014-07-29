package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.CodeList;

public class CodeListDaoUpdate {

   private final Connection conn;
   
   public CodeListDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   public Integer registerNewCodeList(CodeList codeList) throws SQLException {
      String sql = "insert into "
              + "code_list(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "label, "
              + "description, "
              + "version_description) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(codeList.getUrn().getAgency().getId());
      values.add(codeList.getModuleVersionId());
      values.add(codeList.getUrn().getId());
      values.add(codeList.getUrn().getVersion());

      values.add(codeList.getName());
      values.add(codeList.getLabel());
      values.add(codeList.getDescription());
      values.add(codeList.getVersionDescription());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer generatedId = sqlCommand.executeAndReturnGeneratedKey();
      return generatedId;
   }

   public void updateCodeList(CodeList codeList) throws SQLException {
      String sql = "update code_list set "
              + "name = ?, "
              + "label = ?, "
              + "description = ?, "
              + "version_description = ? "
              + "where code_list_id = ?";

      List values = new ArrayList();
      values.add(codeList.getName());
      values.add(codeList.getLabel());
      values.add(codeList.getDescription());
      values.add(codeList.getVersionDescription());

      values.add(codeList.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void deleteCodeList(CodeList codeList) throws SQLException {
      String sql = "delete from code_list where code_list_id = ?";

      List values = new ArrayList();
      values.add(codeList.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
}

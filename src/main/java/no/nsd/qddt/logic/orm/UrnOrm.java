package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.Map;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Urn;

public final class UrnOrm {

   private UrnOrm() {
   }
   
   public static Urn getUrn(Map map) throws SQLException {
      Urn urn = new Urn();
      Agency a = new Agency();
      urn.setAgency(a);
      a.setId((Integer) map.get("agency_id"));
      urn.setId(SqlUtil.getString("urn_id", map));
      urn.setVersion(SqlUtil.getString("urn_version", map));
      return urn;
   }   
   
   
}

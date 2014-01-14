package no.nsd.qddt.logic;

import java.util.UUID;
import no.nsd.qddt.model.Urn;

public final class UrnUtil {

   public static String agency = "no.nsd";
   public static String firstDraftVersion = "0.0.1";
   
   private UrnUtil() {
   }

   public static Urn createNewUrn() {
      Urn urn = new Urn();
      urn.setAgency(agency);
      urn.setId(createNewId());
      urn.setVersion(firstDraftVersion);
      
      return urn;
   }
   
   
   public static String createNewId() {
      UUID uuid = UUID.randomUUID();
      return uuid.toString();
   }

   
}

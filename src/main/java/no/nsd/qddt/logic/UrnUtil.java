package no.nsd.qddt.logic;

import java.util.UUID;
import no.nsd.qddt.model.Urn;

public final class UrnUtil {

   
   private UrnUtil() {
   }

   public static Urn createNewUrn() {
      Urn urn = new Urn();
      urn.setId(createNewId());
      
      return urn;
   }
   
   
   public static String createNewId() {
      UUID uuid = UUID.randomUUID();
      return uuid.toString();
   }

   
}

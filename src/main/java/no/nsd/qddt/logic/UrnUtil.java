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

   
   public static String createNewVersion(int levels) {
      String id = "1";
      for (int i = 1; i < levels; i++) {
         id += ".0";
      }
      return id;
   }
   
   
   public static String computeNewVersion(String oldVersion, int levelChange, int levels) {
      if (levelChange > levels) {
         throw new RuntimeException("Change of version level greater than number of levels.");
      }

      String[] split = oldVersion.split("\\.");
      String id = "";

      for (int i = 0; i < levels; i++) {
         int v = 0;
         if (i < split.length && i < levelChange) {
            v = Integer.parseInt(split[i]);
         }

         if (i == levelChange - 1) {
            id += v + 1;
         } else {
            id += v;
         }

         if (i < levels - 1) {
            id += ".";
         }
      }

      return id;
   }
   
}

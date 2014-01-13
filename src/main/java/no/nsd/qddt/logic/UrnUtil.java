package no.nsd.qddt.logic;

import java.util.UUID;

public final class UrnUtil {

   public static String agency = "no.nsd";
   public static String firstDraftVersion = "0.0.1";
   
   private UrnUtil() {
   }

   public static String createNewUrn() {
      return agency  + ":" + createNewId() + ":" + firstDraftVersion;
   }
   
   
   public static String createNewId() {
      UUID uuid = UUID.randomUUID();
      return uuid.toString();
   }

   
}

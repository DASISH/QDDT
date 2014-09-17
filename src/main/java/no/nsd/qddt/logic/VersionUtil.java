package no.nsd.qddt.logic;

public final class VersionUtil {
   
   
   
   public static String getVersionPublishText(Integer code) {
      if (code == null || code == 0) {
         return "Not published";
      }
      if (code == 1) {
         return "Published internal";
      }
      if (code == 2) {
         return "Published external";
      }
      return "";
   }
   
   
   
}

package no.nsd.qddt.model;

import java.io.Serializable;

public class Urn implements Serializable {
   
   private String agency;
   private String id;
   private String version;

   public String getAgency() {
      return agency;
   }

   public void setAgency(String agency) {
      this.agency = agency;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getVersion() {
      return version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   
   
   @Override
   public String toString() {
      return this.agency + ":" + this.id + ":" + this.version;
   }
   
}

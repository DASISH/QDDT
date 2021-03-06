package no.nsd.qddt.model;

import java.io.Serializable;

public class Urn implements Serializable {
   
   private Agency agency;
   private String id;
   private String version;

   public Agency getAgency() {
      return agency;
   }

   public void setAgency(Agency agency) {
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
      return this.agency.getUrnId() + ":" + this.id + ":" + this.version;
   }
   
}

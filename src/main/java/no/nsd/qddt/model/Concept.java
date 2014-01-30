package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Concept implements Serializable {
   
   private Integer id;
   private String name;
   private Integer parentConceptId;
   private List<Concept> subConcepts;
   
   public Concept() {
      this.subConcepts = new ArrayList<Concept>();
   }
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Concept> getSubConcepts() {
      return subConcepts;
   }

   public void setSubConcepts(List<Concept> subConcepts) {
      this.subConcepts = subConcepts;
   }

   public Integer getParentConceptId() {
      return parentConceptId;
   }

   public void setParentConceptId(Integer parentConceptId) {
      this.parentConceptId = parentConceptId;
   }

   public void addSubConcept(Concept c) {
      this.subConcepts.add(c);
   }
   
   
   @Override
   public int hashCode() {
      if (this.id == null) {
         return 11;
      } else {
         return this.id;
      }
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Concept other = (Concept) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.name;
   }
   
}

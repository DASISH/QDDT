package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Concept extends Element implements Serializable {
   
   private String relationshipConcept;
   private Integer parentConceptId;
   private Integer conceptSchemeId;
   private Integer conceptOrder;
   private List<Concept> subConcepts;
   
   public Concept() {
      this.subConcepts = new ArrayList<Concept>();
   }
   

   public String getRelationshipConcept() {
      return relationshipConcept;
   }

   public void setRelationshipConcept(String relationshipConcept) {
      this.relationshipConcept = relationshipConcept;
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

   public Integer getConceptSchemeId() {
      return conceptSchemeId;
   }

   public void setConceptSchemeId(Integer conceptSchemeId) {
      this.conceptSchemeId = conceptSchemeId;
   }

   public Integer getConceptOrder() {
      return conceptOrder;
   }

   public void setConceptOrder(Integer conceptOrder) {
      this.conceptOrder = conceptOrder;
   }
   
   
}

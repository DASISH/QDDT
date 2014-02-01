package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConceptScheme implements Serializable {
   
   private Integer id;
   // mapping: concept-id --> concept.
   private SortedMap<Integer, Concept> conceptMap;

   
   public ConceptScheme() {
      this.conceptMap = new TreeMap<Integer, Concept>();
   }
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public SortedMap<Integer, Concept> getConceptMap() {
      return conceptMap;
   }

   public void setConceptMap(SortedMap<Integer, Concept> conceptMap) {
      this.conceptMap = conceptMap;
   }

   public Collection<Concept> getConcepts() {
      return this.conceptMap.values();
   }

   public void addConcept(Concept c) {
      this.conceptMap.put(c.getId(), c);
   }

   public Concept getConcept(Integer conceptId) {
      return this.conceptMap.get(conceptId);
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
      final ConceptScheme other = (ConceptScheme) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return "" + this.id;
   }
   
}

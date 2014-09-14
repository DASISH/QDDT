package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConceptScheme extends Element implements Serializable {
   
   // mapping: concept-id --> concept.
   private SortedMap<Integer, Concept> conceptMap;

   
   public ConceptScheme() {
      this.conceptMap = new TreeMap<Integer, Concept>();
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

   
   
}

package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.List;

public class CodeList extends Element implements Serializable {
   
   private List<Code> codes;

   public CodeList() {
   }

   public List<Code> getCodes() {
      return codes;
   }

   public void setCodes(List<Code> codes) {
      this.codes = codes;
   }

   
   
}

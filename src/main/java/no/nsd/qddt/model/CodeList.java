package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodeList extends Element implements Serializable {
   
   public static final Integer CODE_LIST_TYPE_VALID = 1;
   public static final Integer CODE_LIST_TYPE_MISSING = 2;
   public static final Integer CODE_LIST_TYPE_COMBINED = 3;
   
   private List<Code> codes;
   private Integer codeListType;
   private Integer validCodeListId;
   private Integer missingCodeListId;
   
   private CodeList validCodeList;
   private CodeList missingCodeList;

   public CodeList() {
      codes = new ArrayList<Code>();
   }

   public List<Code> getCodes() {
      return codes;
   }

   public void setCodes(List<Code> codes) {
      this.codes = codes;
   }

   public void addCode(Code c) {
      codes.add(c);
   }

   public Integer getCodeListType() {
      return codeListType;
   }

   public void setCodeListType(Integer codeListType) {
      this.codeListType = codeListType;
   }
   
   public boolean isValid() {
      return CODE_LIST_TYPE_VALID.equals(codeListType);
   }
   public boolean isMissing() {
      return CODE_LIST_TYPE_MISSING.equals(codeListType);
   }
   public boolean isCombined() {
      return CODE_LIST_TYPE_COMBINED.equals(codeListType);
   }
   
   public String getCodeListTypeText() {
      String text = "";
      if (isValid()) {
         text = "Valid";
      }
      if (isMissing()) {
         text = "Missing";
      }
      if (isCombined()) {
         text = "Valid + Missing";
      }
      
      return text;
   }

   public Integer getValidCodeListId() {
      return validCodeListId;
   }

   public void setValidCodeListId(Integer validCodeListId) {
      this.validCodeListId = validCodeListId;
   }

   public Integer getMissingCodeListId() {
      return missingCodeListId;
   }

   public void setMissingCodeListId(Integer missingCodeListId) {
      this.missingCodeListId = missingCodeListId;
   }

   public CodeList getValidCodeList() {
      return validCodeList;
   }

   public void setValidCodeList(CodeList validCodeList) {
      this.validCodeList = validCodeList;
   }

   public CodeList getMissingCodeList() {
      return missingCodeList;
   }

   public void setMissingCodeList(CodeList missingCodeList) {
      this.missingCodeList = missingCodeList;
   }
   
}

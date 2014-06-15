<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<script type="text/javascript">
<!--
   function confirmation() {
      var answer = confirm("Delete question?")
      if (answer) {
         return true;
      }
      else {
         return false;
      }
   }
//-->
</script>

<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp" />
      <div class="tab-box">

         <h3>Edit question</h3>

         <form action="<c:url value="/u/r/savequestion" />" method="post">
            <input type="hidden" name="mvid" value="${moduleVersion.id}">
            <input type="hidden" name="cid" value="${param.cid}">
            <input type="hidden" name="qid" value="${param.qid}">

            <h4>Name:</h4>
            <input class="w10" type="text" name="name" value="${fn:escapeXml(question.name)}">

            <h4>Label:</h4>
            <input class="w10" type="text" name="label" value="${fn:escapeXml(question.label)}">

            <h4>Question intent:</h4>
            <textarea class="w10" name="question_intent" rows="5">${fn:escapeXml(question.questionIntent)}</textarea>

            <h4>Question text:</h4>
            <textarea class="w10" name="question_text" rows="5">${fn:escapeXml(question.questionText)}</textarea>

            <h4>Version description:</h4>
            <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(question.versionDescription)}</textarea>

            <div class="topmarg">
               <input class="okbutton" type="submit" name="action" value="Update">
               <input class="deletebutton" type="submit" name="action" value="Delete question" onclick="return confirmation();">
            </div>
         </form>


      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />
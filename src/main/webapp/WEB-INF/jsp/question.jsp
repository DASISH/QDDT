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

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>
         
         <div class="boxheader">Edit question</div>

         <form action="<c:url value="/u/r/savequestion" />" method="post">
            <input type="hidden" name="mvid" value="${moduleVersion.id}">
            <input type="hidden" name="cid" value="${param.cid}">
            <input type="hidden" name="qid" value="${param.qid}">

            <h4>Name/Number:</h4>
            <input class="w10" type="text" name="name" value="${fn:escapeXml(question.name)}">

            <h4>Question intent:</h4>
            <textarea class="w10" name="question_intent" rows="5">${fn:escapeXml(question.questionIntent)}</textarea>

            <h4>Question text (introduction):</h4>
            <textarea class="w10" name="question_text" rows="5">${fn:escapeXml(question.questionText)}</textarea>

            <h4>Question text (request for answer):</h4>
            <textarea class="w10" name="question_text_2" rows="5">${fn:escapeXml(question.questionText2)}</textarea>

            <h4>Version description:</h4>
            <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(question.versionDescription)}</textarea>

            <div class="topmarg">
               <input class="okbutton" type="submit" name="action" value="Update">
               <input class="deletebutton" type="submit" name="action" value="Delete question" onclick="return confirmation();">
            </div>
         </form>

            <div class="boxheader">Response domain</div>

            <c:if test="${question.codeList == null}">
               <p><a class="button" href="<c:url value="/u/questionresponsedomain?mvid=${param.mvid}&qid=${param.qid}" />">Add response domain</a></p>
            </c:if>

               
            
            <c:if test="${question.codeList != null}">
               <h3>Code list</h3>
               
               <qddt:questionResponseDomain question="${question}" />
               
               <p class="topmarg"><a class="button" href="<c:url value="/u/questionresponsedomain?mvid=${param.mvid}&qid=${param.qid}" />">Change response domain</a></p>
            
            
               <div class="boxheader">Response cardinality</div>

               <form action="<c:url value="/u/r/saveresponsecardinality" />" method="post">
                  <input type="hidden" name="mvid" value="${moduleVersion.id}">
                  <input type="hidden" name="cid" value="${param.cid}">
                  <input type="hidden" name="qid" value="${param.qid}">

                  <p class="topmarg">Minimum responses: <input class="w1" type="text" name="min" value="${question.minimumResponses}"></p>
                  <p>Maximum responses: <input class="w1" type="text" name="max" value="${question.maximumResponses}"></p>

                  <div class="topmarg">
                     <input class="okbutton" type="submit" name="action" value="Update">
                  </div>
               </form>

               
               
            </c:if>
               
            
            
            

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

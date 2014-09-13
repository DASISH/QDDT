<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp" />

      <div class="tab-box">

         <p class="helptext">Associate question to concept:</p>
         <p>${fn:escapeXml(concept.label)} - ${fn:escapeXml(concept.name)}</p>

         <c:if test="${!empty questions}">

            <div class="boxheader">Questions</div>

            <c:forEach items="${questions}" var="q">
               <div class="box topmarg">

                  <table>
                     <tbody>
                        <tr><td class="helptext">Name/Number:</td><td>${fn:escapeXml(q.name)}</td></tr>
                        <tr><td class="helptext">Question intent:</td><td>${fn:escapeXml(q.questionIntent)}</td></tr>
                        <tr><td class="helptext">Question text:</td><td>${fn:escapeXml(q.questionText)} ${fn:escapeXml(q.questionText2)}</td></tr>
                     </tbody>
                  </table>
                     <p class="topmarg helptext">Response:</p>
                  <qddt:questionResponseDomain question="${q}" />

                  <form action="<c:url value="/u/r/addquestiontoconcept" />" class="topmarg" method="post">
                     <input type="hidden" name="mvid" value="${moduleVersion.id}">
                     <input type="hidden" name="qid" value="${q.id}">
                     <input type="hidden" name="qsid" value="${param.qsid}">
                     <input class="okbutton" type="submit" value="Add this">
                  </form>

               </div>
            </c:forEach>


         </c:if>


      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

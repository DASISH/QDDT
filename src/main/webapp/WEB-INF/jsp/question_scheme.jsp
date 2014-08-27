<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="questionscheme" />
      </c:import>

      <div class="tab-box">



         <div class="boxheader">Question scheme</div>

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>

            <form action="<c:url value="/u/r/savequestionscheme" />" method="post">
            <input type="hidden" name="mvid" value="${moduleVersion.id}">
            <input type="hidden" name="qsid" value="${questionScheme.id}">

            <h4>Name:</h4>
            <input class="w10" type="text" name="name" value="${fn:escapeXml(questionScheme.name)}">

            <h4>Label:</h4>
            <input class="w10" type="text" name="label" value="${fn:escapeXml(questionScheme.label)}">

            <h4>Description:</h4>
            <textarea class="w10" name="description" rows="5">${fn:escapeXml(questionScheme.description)}</textarea>

            <h4>Version description:</h4>
            <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(questionScheme.versionDescription)}</textarea>

            <c:if test="${questionScheme == null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Create question scheme"></div></c:if>
            <c:if test="${questionScheme != null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Save"></div></c:if>
            </form>

         <c:if test="${questionScheme != null}">
            <h3>Comments</h3>

            <qddt:comments agencyId="${questionScheme.urn.agency.id}" urnId="${questionScheme.urn.id}" elementId="${questionScheme.id}" />

            <c:import url="/WEB-INF/jspf/add_comment.jsp">
               <c:param name="elementId" value="${questionScheme.id}" />
               <c:param name="agencyId" value="${questionScheme.urn.agency.id}" />
               <c:param name="urnId" value="${questionScheme.urn.id}" />
               <c:param name="fromUrl" value="/u/questionScheme?mvid=${param.mvid}" />
            </c:import>
         </c:if>


         <c:if test="${questionScheme != null}">

            <div class="boxheader">Questions</div>

            <c:forEach items="${questionScheme.questions}" var="q">
               <div class="box topmarg">
               <p>Name/Number: ${fn:escapeXml(q.name)}</p>
               <p>Question intent: ${fn:escapeXml(q.questionIntent)}</p>
               <p>Question text: ${fn:escapeXml(q.questionText)} ${fn:escapeXml(q.questionText2)}</p>
               
               <qddt:questionResponseDomain question="${q}" />
               
               <p><a href="<c:url value="/u/question?mvid=${moduleVersion.id}&qid=${q.id}" />">[edit question]</a></p>
               </div>
            </c:forEach>


            <form action="<c:url value="/u/r/savequestion" />" class="topmarg" method="post">
               <input type="hidden" name="mvid" value="${moduleVersion.id}">
               <input type="hidden" name="qsid" value="${questionScheme.id}">
               <input class="okbutton" type="submit" value="Add new question">
            </form>


         </c:if>


      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

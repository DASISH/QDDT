<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />


<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp" />
      <div class="tab-box">

            <h4>Add response domain for question:</h4>
            <h3><em>${fn:escapeXml(question.questionText)} ${fn:escapeXml(question.questionText2)}</em></h3>

         <h4>Response domain:</h4>

         <ul>
            <li><a href="<c:url value="/u/codelist?mvid=${param.mvid}&qid=${param.qid}" />">Code List</a></li>
         </ul>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

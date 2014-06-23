<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Categories for ${fn:escapeXml(survey.name)}</h1>

      <c:forEach items="${categories}" var="c">
         <ul class="plain-list">
            <li>${fn:escapeXml(c.label)}</li>
         </ul>
      </c:forEach>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

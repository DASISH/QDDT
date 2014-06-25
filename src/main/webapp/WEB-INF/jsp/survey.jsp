<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Survey - ${fn:escapeXml(survey.name)}</h1>

         <ul class="plain-list">
            <li><a href="<c:url value="/u/category?sid=${param.id}" />">Categories</a></li>
      </ul>


   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

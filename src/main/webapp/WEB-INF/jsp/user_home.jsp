<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h2>Modules</h2>
      <h3>Existing modules</h3>
      <ul>
      <c:forEach items="${modules}" var="m">
         <li><a href="<c:url value="/u/history?agency=${m.urn.agency}&id=${m.urn.id}" />">${m.study} - ${m.title}</a></li>
      </c:forEach>
      </ul>
      
      <form action="<c:url value="/u/title" />" method="get">
         <input class="button" type="submit" value="New module">
      </form>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

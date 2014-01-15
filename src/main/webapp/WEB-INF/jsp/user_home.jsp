<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">


      <h2>Modules</h2>
      <ul>
      <c:forEach items="${modules}" var="m">
         <li><a href="<c:url value="/u/module?agency=${m.urn.agency}&id=${m.urn.id}" />">${m.study} - ${m.title}</a></li>
      </c:forEach>
      </ul>
      
      <p><a href="<c:url value="/u/r/regmodule" />">Register new module</a></p>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

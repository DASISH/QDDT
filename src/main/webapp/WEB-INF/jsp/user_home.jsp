<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Modules</h1>
      
      <ul>
      <c:forEach items="${modules}" var="m">
         <li>${m.study} - ${m.title}</li>
      </c:forEach>
      </ul>
      
      <p><a href="<c:url value="/u/r/regmodule" />">Register new module</a></p>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

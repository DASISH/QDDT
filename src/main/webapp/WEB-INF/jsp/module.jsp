<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Module</h1>
      
      <ul>
      <c:forEach items="${modules}" var="m">
         <li>
            ${m.urn.version} - ${m.study} - ${m.title}
            - <a href="<c:url value="/u/r/updatemodule" />">[Update module]</a>
         </li>
      </c:forEach>
      </ul>
      
      <p><a href="<c:url value="/u/r/regmodule" />">Register new module</a></p>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

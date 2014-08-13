<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />


<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="responsedomain" />
      </c:import>
      
      <div class="tab-box">

         <h3>Response domains:</h3>

         <ul>
            <li><a href="<c:url value="/u/category?mvid=${param.mvid}" />">Categories</a></li>
            <li><a href="<c:url value="/u/code?mvid=${param.mvid}" />">Codes</a></li>
            <li><a href="<c:url value="/u/codelist?mvid=${param.mvid}" />">Code Lists</a></li>
         </ul>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

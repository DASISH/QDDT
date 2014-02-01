<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="status" />
      </c:import>

      <div class="tab-box">

         <h3>The status of this module version (${fn:escapeXml(module.versionText)}) is: "${fn:escapeXml(module.statusText)}"</h3>
         
         <c:if test="${module.statusAsLong eq 1}">
            <form action="<c:url value="" />" method="post">
               <input class="button" type="submit" value="Change status to comment">
            </form>
         </c:if>
         <c:if test="${module.statusAsLong eq 2}">
            <form action="<c:url value="" />" method="post">
               <input class="button" type="submit" value="Change status to closed">
            </form>
         </c:if>

      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="versioninfo" />
      </c:import>
      

      <form class="tab-box" action="<c:url value="" />" method="post">

         <input type="hidden" name="mvid" value="${moduleVersion.id}">
         
         <h4>Version number:</h4>
         <input class="w8" type="text" name="version_number" value="${fn:escapeXml(moduleVersion.versionNumber)}">

         <h4>Version description:</h4>
         <textarea class="w10" name="version_description" rows="7">${fn:escapeXml(moduleVersion.versionDescription)}</textarea>

         <div>
         <input class="okbutton topmarg" type="submit" value="Save">
         </div>

      </form>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

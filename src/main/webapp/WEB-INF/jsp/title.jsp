<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="title" />
      </c:import>
      

      <form class="tab-box" action="<c:url value="/u/r/savemodule" />" method="post">

         <input type="hidden" name="mvid" value="${moduleVersion.id}">
         
         <h4>Title:</h4>
         <input class="w10" type="text" name="title" value="${fn:escapeXml(moduleVersion.title)}">

         <h4>Module Authors:</h4>
         <textarea class="w10" name="authors" rows="5">${fn:escapeXml(moduleVersion.authors)}</textarea>

         <h4>Module Author's affiliation:</h4>
         <textarea class="w10" name="affiliation" rows="5">${fn:escapeXml(moduleVersion.authorsAffiliation)}</textarea>

         <h4>Abstract:</h4>
         <textarea class="w10" name="abstract" rows="10">${fn:escapeXml(moduleVersion.moduleAbstract)}</textarea>


         <div>
         <input class="okbutton topmarg" type="submit" value="Save">
         </div>

      </form>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

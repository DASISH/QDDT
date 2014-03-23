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


      <form class="tab-box" action="<c:url value="/u/r/saveversioninfo" />" method="post">

         <input type="hidden" name="mvid" value="${moduleVersion.id}">

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>
         
         <h4>Version number:</h4>
         <input class="w4" type="text" name="version_number" value="${fn:escapeXml(moduleVersion.versionNumber)}">

         <h4>Version description:</h4>
         <textarea class="w10" name="version_description" rows="7">${fn:escapeXml(moduleVersion.versionDescription)}</textarea>

         <h4>Version status:</h4>
         <select name="status">
            <option value="1" ${moduleVersion.statusAsLong eq 1 ? 'selected="selected"' : ''}>Development</option>
            <option value="2" ${moduleVersion.statusAsLong eq 2 ? 'selected="selected"' : ''}>Comment</option>
            <option value="3" ${moduleVersion.statusAsLong eq 3 ? 'selected="selected"' : ''}>Closed</option>
            <option value="4" ${moduleVersion.statusAsLong eq 4 ? 'selected="selected"' : ''}>Closed - published internal</option>
            <option value="5" ${moduleVersion.statusAsLong eq 5 ? 'selected="selected"' : ''}>Closed - published external</option>
         </select>


         <div>
            <input class="okbutton topmarg" type="submit" value="Save">
         </div>

      </form>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

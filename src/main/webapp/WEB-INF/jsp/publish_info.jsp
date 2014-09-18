<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="publishinfo" />
      </c:import>

      <div class="tab-box">

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>         
         
      <form action="<c:url value="/u/r/savepublishinfo" />" method="post">
         <input type="hidden" name="mvid" value="${moduleVersion.id}">

         <h4>Version pubished status:</h4>
         <select name="version_publish_code">
            <option value="0" ${moduleVersion.versionPublishCodeAsLong == null || moduleVersion.versionPublishCodeAsLong eq 0 ? 'selected="selected"' : ''}>Not published</option>
            <option value="1" ${moduleVersion.versionPublishCodeAsLong eq 1 ? 'selected="selected"' : ''}>Published internal</option>
            <option value="2" ${moduleVersion.versionPublishCodeAsLong eq 2 ? 'selected="selected"' : ''}>Published external</option>
         </select>


         <div><input class="okbutton topmarg" type="submit" value="OK - Save"></div>

         <c:if test="${error != null}">
            <p class="error topmarg">Error: ${error}</p>
         </c:if>
         
      </form>
         

      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

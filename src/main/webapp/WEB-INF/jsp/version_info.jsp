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

      <div class="tab-box">

      <form action="<c:url value="/u/r/saveversioninfo" />" method="post">
         <input type="hidden" name="mvid" value="${moduleVersion.id}">

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>

            <div class="boxheader">Version info</div>
            
         <c:if test="${moduleVersion.urnVersion != null}">
            <h4>Version change:</h4>
            <select name="version_change_code">
               <option value=""></option>
               <option value="1" ${moduleVersion.versionChangeCodeAsLong eq 1 ? 'selected="selected"' : ''}>Major change</option>
            <option value="2" ${moduleVersion.versionChangeCodeAsLong eq 2 ? 'selected="selected"' : ''}>Minor change</option>
            <%--<option value="3" ${moduleVersion.versionChangeCodeAsLong eq 3 ? 'selected="selected"' : ''}>Sub-minor change</option>--%>
         </select>
         </c:if>

         <h4>Version description:</h4>
         <textarea class="w10" name="version_description" rows="7">${fn:escapeXml(moduleVersion.versionDescription)}</textarea>

         <h4>Release number:</h4>
         <input class="w4" type="text" name="version_number" value="${fn:escapeXml(moduleVersion.versionNumber)}">
         
         <div><input class="okbutton topmarg" type="submit" value="Save version info"></div>

      </form>

      <div class="boxheader">Publish info</div>
      <form action="<c:url value="/u/r/savepublishinfo" />" method="post">
         <input type="hidden" name="mvid" value="${moduleVersion.id}">

         <h4>Version pubished status:</h4>
         <select name="version_publish_code">
            <option value="0" ${moduleVersion.versionPublishCodeAsLong == null || moduleVersion.versionPublishCodeAsLong eq 0 ? 'selected="selected"' : ''}>Not published</option>
            <option value="1" ${moduleVersion.versionPublishCodeAsLong eq 1 ? 'selected="selected"' : ''}>Published internal</option>
            <option value="2" ${moduleVersion.versionPublishCodeAsLong eq 2 ? 'selected="selected"' : ''}>Published external</option>
         </select>


         <div><input class="okbutton topmarg" type="submit" value="Save publish info"></div>

      </form>
         
         
      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

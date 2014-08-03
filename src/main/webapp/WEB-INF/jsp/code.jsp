<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="responsedomain" />
      </c:import>
      
      <div class="tab-box">
      
         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>
         
      <div class="boxheader">New code</div>

      <form action="<c:url value="/u/r/savecategory" />" method="post">
         <input type="hidden" name="mvid" value="${param.mvid}">
         <input type="hidden" name="cid" value="${category.id}">

         <h4>Value:</h4>
         <input class="w10" type="text" name="value" value="${fn:escapeXml(code.value)}">

         <h4>Version description:</h4>
         <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(category.versionDescription)}</textarea>

         <c:if test="${code == null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Create new code"></div></c:if>
         <c:if test="${code != null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Save"></div></c:if>
         </form>


         <div class="boxheader">Choose category</div>
      
      
      <c:forEach items="${categories}" var="c">
         <ul class="plain-list">
            <li><a href="?mvid=${param.mvid}&cid=${c.id}">${fn:escapeXml(c.label)}</a></li>
         </ul>
      </c:forEach>
         
         </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

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
      
      <div class="boxheader">New category</div>

      <form action="<c:url value="/u/r/savecategory" />" method="post">
         <input type="hidden" name="sid" value="${survey.id}">
         <input type="hidden" name="cid" value="${category.id}">

         <h4>Label:</h4>
         <input class="w10" type="text" name="label" value="${fn:escapeXml(category.label)}">

         <h4>Short label:</h4>
         <input class="w10" type="text" name="label_short" value="${fn:escapeXml(category.labelShort)}">

         <h4>Description:</h4>
         <textarea class="w10" name="description" rows="5">${fn:escapeXml(category.description)}</textarea>

         <h4>Version description:</h4>
         <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(category.versionDescription)}</textarea>

         <c:if test="${category == null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Create new category"></div></c:if>
         <c:if test="${category != null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Save"></div></c:if>
         </form>


         <div class="boxheader">All existing categories for ${fn:escapeXml(survey.name)}</div>
      
      
      <c:forEach items="${categories}" var="c">
         <ul class="plain-list">
            <li>${fn:escapeXml(c.label)}</li>
         </ul>
      </c:forEach>
         
         </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

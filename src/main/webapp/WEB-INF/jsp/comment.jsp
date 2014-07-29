<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>
<fmt:setLocale value="en-GB" />

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="comment" />
      </c:import>

      <div class="tab-box">

         <h3>General comments for this version</h3>

         <qddt:comments 
            agencyId="${moduleVersion.module.agency.id}" 
            urnId="${moduleVersion.module.urnId}" 
            elementId="${moduleVersion.id}" />

         <c:import url="/WEB-INF/jspf/add_comment.jsp">
            <c:param name="elementId" value="${moduleVersion.id}" />
            <c:param name="agencyId" value="${moduleVersion.module.agency.id}" />
            <c:param name="urnId" value="${moduleVersion.module.urnId}" />
            <c:param name="fromUrl" value="/u/comment?mvid=${param.mvid}" />
         </c:import>

      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

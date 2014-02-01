<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Module development history</h1>

      <table>
         <thead>
            <tr>
               <th>Version</th>
               <th>Study</th>
               <th>Title</th>
               <th>Status</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${modules}" var="m">
               <tr>
                  <td>${fn:escapeXml(m.versionText)}</td>
                  <td>${fn:escapeXml(m.study)}</td>
                  <td>${fn:escapeXml(m.title)}</td>
                  <td>${fn:escapeXml(m.statusText)}</td>
                  
                  <td>
                     <c:if test="${m.statusAsLong eq 1}">
                        <a href="<c:url value="/u/r/regmodule?id=${m.id}" />">Update</a>
                     </c:if>
                     <c:if test="${m.statusAsLong eq 2}">
                        <a href="<c:url value="/u/r/regmodule?id=${m.id}" />">View/Comment</a>
                     </c:if>
                     <c:if test="${m.statusAsLong eq 3}">
                        <a href="<c:url value="/u/r/regmodule?id=${m.id}" />">View</a>
                     </c:if>
                  </td>
               </tr>
            </c:forEach>
         </tbody>
      </table>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Module: ${fn:escapeXml(module.name)}</h1>

      <h2 class="boxheader">Module details</h2>
      <ul class="plain-list">
         <li>Name: ${fn:escapeXml(module.name)}</li>
         <li>Study: ${fn:escapeXml(module.study)}</li>
         <li>Maintenance agency ID: ${fn:escapeXml(module.agency.urnId)}</li>
      </ul>


      <p><a href="<c:url value="/u/module?id=${module.id}" />">[Edit module details]</a></p>

      <h2 class="boxheader">Module development history</h2>

      <table>
         <thead>
            <tr>
               <th>Rev.ID</th>
               <th>DDI version</th>
               <th>Version number</th>
               <th>Version description</th>
               <th>Actor</th>
               <th>Status</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${moduleVersions}" var="mv">
               <tr>
                  <td>#${mv.id}</td>
                  <td>${fn:escapeXml(mv.urnVersion)}</td>
                  <td>${fn:escapeXml(mv.versionNumber)}</td>
                  <td>${fn:escapeXml(mv.versionDescription)}</td>
                  <td>${fn:escapeXml(mv.actor.name)}</td>
                  <td>${fn:escapeXml(mv.statusText)}</td>
                  <td><a href="<c:url value="/u/title?mvid=${mv.id}" />">Update</a></td>
               </tr>
            </c:forEach>
         </tbody>
      </table>



      <c:if test="${lastModuleVersion == null || lastModuleVersion.statusAsLong >= 3}">
         <form action="<c:url value="/u/r/newmoduleversion" />" method="get">
            <input type="hidden" name="mid" value="${module.id}">
            <input class="okbutton topmarg" type="submit" value="Create new module revision">
         </form>
      </c:if>



   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

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

      <c:set var="i" value="0" />
      <table>
         <thead>
            <tr>
               <th>#</th>
               <th>DDI version</th>
               <th>Release number</th>
               <th>Version description</th>
               <th>Actor</th>
               <th>Publish info</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${moduleVersions}" var="mv">
               <c:set var="i" value="${i + 1}" />
               <tr>
                  <td>${i}</td>
                  <td>${fn:escapeXml(mv.urnVersion)}</td>
                  <td>${fn:escapeXml(mv.versionNumber)}</td>
                  <td>${fn:escapeXml(mv.versionDescription)}</td>
                  <td>${fn:escapeXml(mv.actor.name)}</td>
                  <td>${fn:escapeXml(mv.versionPublishText)}</td>
                  <td class="align-center">
                     <c:if test="${mv.published}"><a href="<c:url value="/u/title?mvid=${mv.id}" />">View</a></c:if>
                     <c:if test="${!mv.published}"><a class="button" href="<c:url value="/u/title?mvid=${mv.id}" />">Update</a></c:if>
                  </td>
               </tr>
            </c:forEach>
         </tbody>
      </table>


      <c:if test="${lastModuleVersion == null || lastModuleVersion.published}">
         <form action="<c:url value="/u/r/newmoduleversion" />" method="get">
            <input type="hidden" name="mid" value="${module.id}">
            <input class="okbutton topmarg" type="submit" value="New module version">
         </form>
      </c:if>


   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

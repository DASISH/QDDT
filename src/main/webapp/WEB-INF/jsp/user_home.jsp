<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <h1>Home</h1>
      <div class="boxheader">Modules</div>
      <table>
         <thead>
            <tr>
               <th>Survey</th>
               <th>Study</th>
               <th>Module</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${modules}" var="m">
               <tr>
                  <td>${fn:escapeXml(m.study.survey)}</td>
                  <td>${fn:escapeXml(m.study)}</td>
                  <td><a href="<c:url value="/u/history?id=${m.id}" />">${fn:escapeXml(m.name)}</a></td>
               </tr>
            </c:forEach>

         </tbody>
      </table>

      <form action="<c:url value="/u/module" />" method="get">
         <input class="button topmarg" type="submit" value="New module">
      </form>


   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

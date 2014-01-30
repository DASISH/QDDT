<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                  <td>${m.versionText}</td>
                  <td>${m.study}</td>
                  <td>${m.title}</td>
                  <td>${m.statusText}</td>
                  <td><a href="<c:url value="/u/r/regmodule?id=${m.id}" />">Update</a></td>
               </tr>
            </c:forEach>
         </tbody>
      </table>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

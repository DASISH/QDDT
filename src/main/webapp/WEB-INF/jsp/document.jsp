<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="document" />
      </c:import>

      <div class="tab-box">

         <h3>Documents</h3>
         <form action="<c:url value="/u/r/uploadfile/${param.mvid}" />" method="post" enctype="multipart/form-data">
            <p>File: <input name="file" type="file">
               <input class="okbutton" value="Upload file" type="submit"></p>
         </form>

         <c:if test="${empty documents}">
            <p class="helptext">(No uploaded documents.)</p>
         </c:if>

         <c:if test="${!empty documents}">
            <table>
               <caption>Uploaded documents</caption>
               <thead>
                  <tr><th>Filename</th></tr>
               </thead>
               <tbody>
                  <c:forEach items="${documents}" var="d">
                     <tr><td>${fn:escapeXml(d)}</td><td><a class="button" href="<c:url value="/u/downloadfile?id=${d.id}" />">View/Download</a></td></tr>
                  </c:forEach>
               </tbody>
            </table>
         </c:if>
            

      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

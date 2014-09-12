<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

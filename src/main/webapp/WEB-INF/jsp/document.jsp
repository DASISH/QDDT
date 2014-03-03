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

         <h3>Background document</h3>
         <p>Upload background document.
            <input class="button" type="button" value="Upload">
         </p>

         <br>
         
         <h3>Concepts and relationships</h3>
         <p>Upload a document describing concepts and relationships with other concepts.
            <input class="button" type="button" value="Upload">
         </p>

      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

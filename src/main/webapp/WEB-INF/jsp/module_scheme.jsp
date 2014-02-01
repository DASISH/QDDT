<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="scheme" />
      </c:import>

      <div class="tab-box">

         <h3>Question scheme</h3>
         <p>(Not implemented.)</p>

      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

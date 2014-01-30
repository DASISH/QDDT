<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <h1>New module</h1>
      <ul class="header-tabs">
         <li><a href="<c:url value="/u/r/regmodule?id=${param.id}" />">Title/Authors...</a></li>
         <li class="active-tab"><a href="<c:url value="/u/r/moduledoc?id=${param.id}" />">Background documents</a></li>
         <li><a href="<c:url value="/u/r/moduleconcept?id=${param.id}" />">Concepts/Questions</a></li>
         <li><a href="<c:url value="/u/r/modulescheme?id=${param.id}" />">Question scheme</a></li>
         <li><a href="<c:url value="/u/r/modulequest?id=${param.id}" />">Questionnaire</a></li>
         <li><a href="<c:url value="/u/r/moduleoutput?id=${param.id}" />">Outputs</a></li>
         <li><a href="<c:url value="/u/r/modulestatus?id=${param.id}" />">Status</a></li>
      </ul>

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

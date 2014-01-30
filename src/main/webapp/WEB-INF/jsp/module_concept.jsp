<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <ul class="header-tabs">
         <li><a href="<c:url value="/u/r/regmodule?id=${param.id}" />">Title/Authors...</a></li>
         <li><a href="<c:url value="/u/r/moduledoc?id=${param.id}" />">Background documents</a></li>
         <li class="active-tab"><a href="<c:url value="/u/r/moduleconcept?id=${param.id}" />">Concepts/Questions</a></li>
         <li><a href="<c:url value="/u/r/modulescheme?id=${param.id}" />">Question scheme</a></li>
         <li><a href="<c:url value="/u/r/modulequest?id=${param.id}" />">Questionnaire</a></li>
         <li><a href="<c:url value="/u/r/moduleoutput?id=${param.id}" />">Outputs</a></li>
         <li><a href="<c:url value="/u/r/modulestatus?id=${param.id}" />">Status</a></li>
      </ul>

      <div class="tab-box">

         <div class="grid">
            <div class="col w4 border-r">

               <h3>Concept hierarchy</h3>
               <ul class="link-list">
                  <c:forEach items="${conceptScheme.concepts}" var="c">
                     <li><a href="?id=${param.id}&cid=${c.id}"><c:out value="${c.name}" default="(name missing)" /></a>
                        <c:if test="${!empty c.subConcepts}">
                           <ul>
                              <c:forEach items="${c.subConcepts}" var="sub">
                                 <li><a href="?id=${param.id}&cid=${sub.id}"><c:out value="${sub.name}" default="(name missing)" /></a>
                              </c:forEach>
                           </ul>
                        </c:if>
                     </li>
                     </c:forEach>
               </ul>
            </div>
            <div class="col w8">

               <h3>Concepts and questions</h3>


               <form action="<c:url value="" />" method="post">
                  <input type="hidden" name="id" value="${module.id}">
                  <input type="hidden" name="cid" value="${concept.id}">

                  <h3>Name:</h3>
                  <input class="w10" type="text" name="name" value="${concept.name}">

                  <h3>Label:</h3>
                  <input class="w10" type="text" name="label" value="${concept.name}">

                  <h3>description:</h3>
                  <textarea class="w10" name="description" rows="5">${concept.name}</textarea>

                  <h3>relationship_concept:</h3>
                  <textarea class="w10" name="relationship_concept" rows="5">${concept.name}</textarea>

                  <input class="button topmarg" type="submit" value="Save">
               </form>

               
               
               
               
            </div>
         </div>
      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

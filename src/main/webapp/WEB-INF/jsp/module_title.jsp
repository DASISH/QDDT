<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      
      <c:import url="/WEB-INF/jspf/module_header.jsp" />
      
      <ul class="header-tabs">
         <li class="active-tab"><a href="<c:url value="/u/r/regmodule?id=${param.id}" />">Title/Authors...</a></li>
         <li><a href="<c:url value="/u/r/moduledoc?id=${param.id}" />">Background documents</a></li>
         <li><a href="<c:url value="/u/r/moduleconcept?id=${param.id}" />">Concepts/Questions</a></li>
         <li><a href="<c:url value="/u/r/modulescheme?id=${param.id}" />">Question scheme</a></li>
         <li><a href="<c:url value="/u/r/modulequest?id=${param.id}" />">Questionnaire</a></li>
         <li><a href="<c:url value="/u/r/moduleoutput?id=${param.id}" />">Outputs</a></li>
         <li><a href="<c:url value="/u/r/modulestatus?id=${param.id}" />">Status</a></li>
      </ul>

      <form class="tab-box" action="<c:url value="/u/r/savemodule" />" method="post">

         <input type="hidden" name="id" value="${module.id}">
         
         <c:if test="${module.id == null}">
            <h4>Version:</h4>
            <select name="version">
               <option value=""></option>
               <option value="draft">Draft</option>
               <option value="final">Final</option>
            </select>
         </c:if>
         <c:if test="${module.id != null}">
            <h4>Version: ${module.versionText}</h4>
         </c:if>


         <h4>Study:</h4>
         <input class="w10" type="text" name="study" value="${module.study}">

         <h4>Title:</h4>
         <input class="w10" type="text" name="title" value="${module.title}">

         <h4>Module Authors:</h4>
         <textarea class="w10" name="authors" rows="5">${module.authors}</textarea>

         <h4>Module Author's affiliation:</h4>
         <textarea class="w10" name="affiliation" rows="5">${module.authorsAffiliation}</textarea>

         <h4>Abstract:</h4>
         <textarea class="w10" name="abstract" rows="10">${module.moduleAbstract}</textarea>

         <h4>Repeat or new module?</h4>
         <div><input id="repeatYes" type="radio" name="repeat" value="yes" ${module.repeat ? 'checked' : ''}> <label for="repeatYes">Repeat</label></div>
         <div><input id="repeatNo" type="radio" name="repeat" value="no" ${module.repeat ? '' : 'checked'} > <label for="repeatNo">new</label></div>


         <input class="button topmarg" type="submit" value="Save">

      </form>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

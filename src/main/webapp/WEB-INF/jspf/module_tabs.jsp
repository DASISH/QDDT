<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul class="header-tabs">
   <li ${param.page eq 'title' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/regmodule?id=${param.id}" />">Title/Authors...</a></li>
   <li ${param.page eq 'document' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/moduledoc?id=${param.id}" />">Background documents</a></li>
   <li ${param.page eq 'concept' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/moduleconcept?id=${param.id}" />">Concepts/Questions</a></li>
   <li ${param.page eq 'scheme' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/modulescheme?id=${param.id}" />">Question scheme</a></li>
   <li ${param.page eq 'questionnaire' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/modulequest?id=${param.id}" />">Questionnaire</a></li>
   <li ${param.page eq 'report' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/modulereport?id=${param.id}" />">Reports</a></li>
   <li ${param.page eq 'status' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/r/modulestatus?id=${param.id}" />">Status</a></li>
</ul>

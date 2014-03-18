<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul class="header-tabs">
   <li ${param.page eq 'title' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/title?mvid=${param.mvid}" />">Title/Authors...</a></li>
   <li ${param.page eq 'document' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/document?mvid=${param.mvid}" />">Background documents</a></li>
   <li ${param.page eq 'conceptscheme' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/conceptscheme?mvid=${param.mvid}" />">Concepts</a></li>
   <li ${param.page eq 'questionscheme' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/questionscheme?mvid=${param.mvid}" />">Questions</a></li>
   <li ${param.page eq 'instrument' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/instrument?mvid=${param.mvid}" />">Instrument</a></li>
   <li ${param.page eq 'report' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/report?mvid=${param.mvid}" />">Reports</a></li>
   <li ${param.page eq 'versioninfo' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/versioninfo?mvid=${param.mvid}" />">Version info</a></li>
   <li ${param.page eq 'status' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/status?mvid=${param.mvid}" />">Status</a></li>
</ul>

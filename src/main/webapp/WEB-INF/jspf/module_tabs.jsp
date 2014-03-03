<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul class="header-tabs">
   <li ${param.page eq 'title' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/title?mid=${param.mid}" />">Title/Authors...</a></li>
   <li ${param.page eq 'document' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/document?mid=${param.mid}" />">Background documents</a></li>
   <li ${param.page eq 'conceptscheme' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/conceptscheme?mid=${param.mid}" />">Concepts</a></li>
   <li ${param.page eq 'questionscheme' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/questionscheme?mid=${param.mid}" />">Questions</a></li>
   <li ${param.page eq 'instrument' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/instrument?mid=${param.mid}" />">Instrument</a></li>
   <li ${param.page eq 'report' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/report?mid=${param.mid}" />">Reports</a></li>
   <li ${param.page eq 'status' ? 'class="active-tab"' : ''}><a href="<c:url value="/u/status?mid=${param.mid}" />">Status</a></li>
</ul>

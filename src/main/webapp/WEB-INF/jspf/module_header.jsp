<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Module: <a href="<c:url value="/u/history?id=${moduleVersion.module.id}" />">${fn:escapeXml(moduleVersion.module.name)}</a></h1>

<p class="helptext">
   ${fn:escapeXml(moduleVersion.module.study)} (${fn:escapeXml(moduleVersion.module.agency.urnId)})
 - actor: ${fn:escapeXml(actor.name)}
</p>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Module: ${fn:escapeXml(moduleVersion.module.name)}</h1>

<div class="boxheader">Module details</div>
<ul class="plain-list">
   <li>Name: ${fn:escapeXml(moduleVersion.module.name)}</li>
   <li>Study: ${fn:escapeXml(moduleVersion.module.study)}</li>
   <li>Maintenance agency ID: ${fn:escapeXml(moduleVersion.module.agency.urnId)}</li>
</ul>

<div class="boxheader">Update module version as ${fn:escapeXml(actor.name)}</div>

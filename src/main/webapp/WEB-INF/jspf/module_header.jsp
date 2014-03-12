<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Module: ${fn:escapeXml(moduleVersion.module.name)}</h1>

<h2 class="boxheader">Module details</h2>
<ul class="plain-list">
   <li>Name: ${fn:escapeXml(moduleVersion.module.name)}</li>
   <li>Study: ${fn:escapeXml(moduleVersion.module.study)}</li>
</ul>

<h2 class="boxheader">Update revision #${moduleVersion.id}</h2>

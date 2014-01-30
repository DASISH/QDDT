<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${module.id == null}">
<h1>New module</h1>
</c:if>
<c:if test="${module.id != null}">
<h1>Update module: '${module.title}' (${module.versionText})</h1>
</c:if>


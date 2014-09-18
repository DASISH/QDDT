<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<h1>Version error</h1>

<p>Missing version change code.</p>

<p>Error: ${error}</p>

<p><a href="javascript:history.back()">Go back --></a>.</p>


<c:import url="/WEB-INF/jspf/bottom.jsp" />

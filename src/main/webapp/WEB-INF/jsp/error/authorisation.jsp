<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<h1>Authorisation error</h1>

<p>This action is not allowed.</p>

<p>Message: <%= exception.getMessage() %></p>

<p><a href="javascript:history.back()">Go back</a>.</p>


<c:import url="/WEB-INF/jspf/bottom.jsp" />

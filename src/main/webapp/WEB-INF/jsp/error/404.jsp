<% response.setStatus(javax.servlet.http.HttpServletResponse.SC_NOT_FOUND); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<h1>Not found</h1>

<p>The requested resource/file was not found.</p>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

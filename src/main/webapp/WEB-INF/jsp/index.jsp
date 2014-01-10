<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<h1>QDDT</h1>

    <c:if test="${error}"><p>Wrong username/password.</p></c:if>
    <form action="<c:url value="/login" />" method="post">
           <p>Username: <input type="text" name="u" value=""></p>
           <p>Password: <input type="password" name="p" value=""></p>
           <p><input type="submit" value="OK"></p>
        </form>
        

           
<c:import url="/WEB-INF/jspf/bottom.jsp" />
           
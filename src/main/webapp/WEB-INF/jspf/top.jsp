<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>QDDT</title>
      <link rel="stylesheet" href="<c:url value="/css/normalize.css" />" media="screen" />
      <link rel="stylesheet" href="<c:url value="/css/main.css" />" media="screen" />

   </head>
   <body>


      <header class="l-headerwrapper">
         <div class="l-headercontent">
            <div class="titleheader">QDDT - Questionnaire Design and Development Tool</div>
            
            <c:if test="${sessionScope.user != null}">
               <div class="headernav">
                  Logged in as ${sessionScope.user}:
                  <a href="<c:url value="/u/" />">Home</a> / <a href="<c:url value="/logout" />">Log out</a>
               </div>
            </c:if>

         </div>
      </header>

      <div class="l-maincontent">

         <div class="l-main">


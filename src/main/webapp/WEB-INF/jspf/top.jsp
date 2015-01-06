<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>DASISH QDDT</title>
      <link rel="stylesheet" href="<c:url value="/css/normalize.css" />" media="screen" />
      <link rel="stylesheet" href="<c:url value="/css/main.css" />" media="screen" />

        <script src="<c:url value="/js/jquery-1.11.0.min.js" />"></script>
        <script src="<c:url value="/js/jquery-ui-1.10.4.min.js" />"></script>
   </head>
   <body>


      <header class="l-headerwrapper">
         <div class="wrap">
            <div class="grid">
               <div class="col">
                  <div class="titleheader"><span class="dasishheader">DASISH</span> QDDT<div class="titleheadersub">Questionnaire Design and Documentation Tool</div></div>

                  <div class="headernav">
                     <c:if test="${sessionScope.user != null}">
                        Logged in as ${sessionScope.user}:
                        <a href="<c:url value="/u/" />">Home</a> / <a href="<c:url value="/logout" />">Log out</a>
                     </c:if>
                  </div>

               </div>
            </div>
         </div>
      </header>

      <div class="wrap">



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en-GB" />
<% pageContext.setAttribute("newLineChar", "\n"); %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="comment" />
      </c:import>

      <div class="tab-box">

         <h3>General comments for this revision</h3>

         <c:forEach items="${comments}" var="comment">
            <div class="comment w10">
               <ul class="comment-list">
               <li>Author: ${fn:escapeXml(comment.actor)}</li>
               <li>Source: ${fn:escapeXml(commentSourceMap[comment.sourceId])}</li>
               <li>Comment date: <fmt:formatDate value="${comment.date}" type="date" /></li>
               </ul>
               <p>${fn:replace(fn:escapeXml(comment.text), newLineChar, "<br />")}</p>
            </div>
         </c:forEach>



         <span class="togglelink" id="commlink">Add comment</span>

         <form id="commform" action="<c:url value="/u/r/newcomment" />" method="post" style="display: none;">
            <input type="hidden" name="mvid" value="${moduleVersion.id}">
            <input type="hidden" name="eid" value="${moduleVersion.id}">
            <input type="hidden" name="aid" value="${moduleVersion.module.agency.id}">
            <input type="hidden" name="urnid" value="${moduleVersion.module.urnId}">
            <input type="hidden" name="fromurl" value="/u/comment?mvid=${param.mvid}">

            <h4>Source:</h4>
            <select name="sourceid">
               <option value=""></option>
               <c:forEach items="${commentSourceMap}" var="map">
                  <option value="${map.value.id}">${fn:escapeXml(map.value.text)}</option>
               </c:forEach>
            </select>

            <h4>Date:</h4>
            <c:set var="now" value="<%=new java.util.Date()%>" />
            Day: <input type="text" name="day" value="<fmt:formatDate value="${now}" pattern="d" />" style="width: 40px;">
            Month: <input type="text" name="month" value="<fmt:formatDate value="${now}" pattern="M" />" style="width: 40px;">
            Year: <input type="text" name="year" value="<fmt:formatDate value="${now}" pattern="y" />" style="width: 80px;">

            <h4>Comment:</h4>
            <textarea class="w10" name="comment_text" rows="10"></textarea>

            <div><input class="okbutton topmarg" type="submit" name="action" value="Save comment"></div>
         </form>

         <script>
            $("#commlink").click(function() {
               $("#commform").toggle();
            });
         </script>




      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

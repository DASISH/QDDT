<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>
<fmt:setLocale value="en-GB" />


<span class="togglelink" id="commlink">Add comment</span>

<form id="commform" action="<c:url value="/u/r/newcomment" />" method="post" style="display: none;">
   <input type="hidden" name="mvid" value="${param.mvid}">
   <input type="hidden" name="eid" value="${param.elementId}">
   <input type="hidden" name="aid" value="${param.agencyId}">
   <input type="hidden" name="urnid" value="${fn:escapeXml(param.urnId)}">
   <input type="hidden" name="fromurl" value="${param.fromUrl}">

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
   Year: <input type="text" name="year" value="<fmt:formatDate value="${now}" pattern="yyyy" />" style="width: 80px;">

   <h4>Comment:</h4>
   <textarea class="w10" name="comment_text" rows="10"></textarea>

   <div><input class="okbutton topmarg" type="submit" name="action" value="Save comment"></div>
</form>

<script>
   $("#commlink").click(function() {
      $("#commform").toggle();
   });
</script>




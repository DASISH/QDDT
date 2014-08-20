<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<div class="boxheader">Codes in this code list</div>

<c:set var="i" value="0" />
<table>
   <tbody>
      <c:forEach items="${codesInList}" var="c">
         <c:set var="i" value="${i + 1}" />
         <tr>
            <td>${fn:escapeXml(c.value)}</td>
            <td>${fn:escapeXml(c.category.label)}</td>
            <td>
               <form action="<c:url value="/u/r/changecodelist" />" method="post">
                  <input type="hidden" name="mvid" value="${param.mvid}">
                  <input type="hidden" name="codeid" value="${c.id}">
                  <input type="hidden" name="clid" value="${param.clid}">
                  <input type="hidden" name="sort" value="${c.sortOrder}">
                  <input class="deletebutton" type="submit" name="action" value="remove">
                  <c:if test="${i > 1}"><input class="okbutton" type="submit" name="action" value="up"></c:if>
                  <c:if test="${i < fn:length(codesInList)}"><input class="okbutton" type="submit" name="action" value="down"></c:if>
                  </form>
               </td>
            </tr>
      </c:forEach>
   </tbody>
</table>


<div class="boxheader">Add codes to this code list</div>

<table>
   <tbody>
      <c:forEach items="${codes}" var="c">
         <tr>
            <td>
               <c:if test="${codeMap[c.id] != null}">
                  <span class="helptext">Added</span>
               </c:if>
               <c:if test="${codeMap[c.id] == null}">
                  <form action="<c:url value="/u/r/addcodetocodelist" />" method="post">
                     <input type="hidden" name="mvid" value="${param.mvid}">
                     <input type="hidden" name="codeid" value="${c.id}">
                     <input type="hidden" name="clid" value="${param.clid}">
                     <input class="okbutton" type="submit" name="action" value="Add">
                  </form>
               </c:if>
            </td>
            <td>${fn:escapeXml(c.value)}</td>
            <td>${fn:escapeXml(c.category.label)}</td>
         </tr>
      </c:forEach>
   </tbody>
</table>


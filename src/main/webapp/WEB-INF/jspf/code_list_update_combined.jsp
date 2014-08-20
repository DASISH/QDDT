
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<div class="boxheader">Codes in this code list</div>

<c:set var="i" value="0" />
<table class="topmarg">
   <tbody>
      <c:forEach items="${validCodeList.codes}" var="c">
         <c:set var="i" value="${i + 1}" />
         <tr>
            <c:if test="${i == 1}">
               <td rowspan="${fn:length(validCodeList.codes)}">
                  <form action="<c:url value="/u/r/changecodelistcombined" />" method="post">
                     <input type="hidden" name="mvid" value="${param.mvid}">
                     <input type="hidden" name="clid" value="${param.clid}">
                     <input type="hidden" name="vclid" value="${validCodeList.id}">
                     <input class="deletebutton" type="submit" name="action" value="Remove">
                  </form>
               </td>
            </c:if>
            <td>${fn:escapeXml(c.value)}</td>
            <td>${fn:escapeXml(c.category.label)}</td>
         </tr>
      </c:forEach>
   </tbody>
</table>

<c:set var="i" value="0" />
<table>
   <tbody>
      <c:forEach items="${missingCodeList.codes}" var="c">
         <c:set var="i" value="${i + 1}" />
         <tr>
            <c:if test="${i == 1}">
               <td rowspan="${fn:length(missingCodeList.codes)}">
                  <form action="<c:url value="/u/r/changecodelistcombined" />" method="post">
                     <input type="hidden" name="mvid" value="${param.mvid}">
                     <input type="hidden" name="clid" value="${param.clid}">
                     <input type="hidden" name="mclid" value="${missingCodeList.id}">
                     <input class="deletebutton" type="submit" name="action" value="Remove">
                  </form>
               </td>
            </c:if>
            <td>${fn:escapeXml(c.value)}</td>
            <td>${fn:escapeXml(c.category.label)}</td>
         </tr>
      </c:forEach>
   </tbody>
</table>


<div class="boxheader">Choose a valid code list</div>

<c:forEach items="${codeLists}" var="cl">
   <c:if test="${cl.valid}">
   <table class="topmarg">
      <tbody>
         <tr>
            <td rowspan="${1 + fn:length(cl.codes)}">
               <c:if test="${cl.id == validCodeList.id}">
               <em>Selected</em>
               </c:if>

               <c:if test="${cl.id != validCodeList.id}">
                  <form action="<c:url value="/u/r/changecodelistcombined" />" method="post">
                     <input type="hidden" name="mvid" value="${param.mvid}">
                     <input type="hidden" name="clid" value="${param.clid}">
                     <input type="hidden" name="vclid" value="${cl.id}">
                     <input class="okbutton" type="submit" name="action" value="Choose">
                  </form>
               </c:if>
            </td>
            <td colspan="2">${fn:escapeXml(cl.label)}</td>
         </tr>
         <c:forEach items="${cl.codes}" var="c">
            <tr>
               <td>${fn:escapeXml(c.value)}</td>
               <td>${fn:escapeXml(c.category.label)}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   </c:if>
</c:forEach>


<div class="boxheader">Choose a missing code list</div>

<c:forEach items="${codeLists}" var="cl">
   <c:if test="${cl.missing}">
   <table class="topmarg">
      <tbody>
         <tr>
            <td rowspan="${1 + fn:length(cl.codes)}">
               <c:if test="${cl.id == missingCodeList.id}">
               <em>Selected</em>
               </c:if>

               <c:if test="${cl.id != missingCodeList.id}">
                  <form action="<c:url value="/u/r/changecodelistcombined" />" method="post">
                     <input type="hidden" name="mvid" value="${param.mvid}">
                     <input type="hidden" name="clid" value="${param.clid}">
                     <input type="hidden" name="mclid" value="${cl.id}">
                     <input class="okbutton" type="submit" name="action" value="Choose">
                  </form>
               </c:if>
            </td>
            <td colspan="2">${fn:escapeXml(cl.label)}</td>
         </tr>
         <c:forEach items="${cl.codes}" var="c">
            <tr>
               <td>${fn:escapeXml(c.value)}</td>
               <td>${fn:escapeXml(c.category.label)}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   </c:if>
</c:forEach>

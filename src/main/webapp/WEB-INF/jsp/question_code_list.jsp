<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />


<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp" />

      <div class="tab-box">

         <h4>Add code list to question:</h4>
         <h3>${fn:escapeXml(question)}</h3>

         <div class="boxheader">Choose a code list</div>

         <h3>Valid code lists</h3>
         <c:forEach items="${codeLists}" var="cl">
            <c:if test="${cl.valid}">
               <table class="topmarg">
                  <tbody>
                     <tr>
                        <td rowspan="${1 + fn:length(cl.codes)}">
                           <form action="<c:url value="/u/r/addcodelisttoquestion" />" method="post">
                              <input type="hidden" name="mvid" value="${param.mvid}">
                              <input type="hidden" name="qid" value="${question.id}">
                              <input type="hidden" name="clid" value="${cl.id}">
                              <input class="okbutton" type="submit" name="action" value="Use this">
                           </form>
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

         <h3>Missing code lists</h3>
         <c:forEach items="${codeLists}" var="cl">
            <c:if test="${cl.missing}">
               <table class="topmarg">
                  <tbody>
                     <tr>
                        <td rowspan="${1 + fn:length(cl.codes)}">
                           <form action="<c:url value="/u/r/addcodelisttoquestion" />" method="post">
                              <input type="hidden" name="mvid" value="${param.mvid}">
                              <input type="hidden" name="qid" value="${question.id}">
                              <input type="hidden" name="clid" value="${cl.id}">
                              <input class="okbutton" type="submit" name="action" value="Use this">
                           </form>
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

         <h3>Valid + missing code lists</h3>
         <c:forEach items="${codeLists}" var="cl">
            <c:if test="${cl.combined}">
               <table class="topmarg">
                  <tbody>
                     <tr>
                        <td rowspan="${1 + fn:length(cl.validCodeList.codes) + fn:length(cl.missingCodeList.codes)}">
                           <form action="<c:url value="/u/r/addcodelisttoquestion" />" method="post">
                              <input type="hidden" name="mvid" value="${param.mvid}">
                              <input type="hidden" name="qid" value="${question.id}">
                              <input type="hidden" name="clid" value="${cl.id}">
                              <input class="okbutton" type="submit" name="action" value="Use this">
                           </form>
                        </td>
                        <td colspan="2">${fn:escapeXml(cl.label)}</td>
                     </tr>
                     <c:forEach items="${cl.validCodeList.codes}" var="c">
                        <tr>
                           <td>${fn:escapeXml(c.value)}</td>
                           <td>${fn:escapeXml(c.category.label)}</td>
                        </tr>
                     </c:forEach>
                     <c:forEach items="${cl.missingCodeList.codes}" var="c">
                        <tr>
                           <td>${fn:escapeXml(c.value)}</td>
                           <td>${fn:escapeXml(c.category.label)}</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </c:if>
         </c:forEach>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

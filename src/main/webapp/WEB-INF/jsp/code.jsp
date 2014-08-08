<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">
   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="responsedomain" />
      </c:import>

      <div class="tab-box">

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>

         <p class="helptext">Create new code or update/create new version of existing code.</p>
         
         <table class="w12">
            <tbody>
               <c:forEach items="${categories}" var="cat">
                  <tr>
                     <td class="w1">
                        <form action="<c:url value="/u/r/newcode" />" method="post">
                           <input type="hidden" name="mvid" value="${param.mvid}">
                           <input type="hidden" name="cid" value="${cat.id}">
                           <input class="okbutton" type="submit" name="action" value="New code">
                        </form>
                     </td>
                     <td class="w8">${fn:escapeXml(cat.label)}</td>
                     <td class="w3"></td>
                  </tr>

                  <c:forEach items="${codes[cat.id]}" var="cod">
                     <tr>
                        <td class="w1"></td>
                        <td class="w8">${fn:escapeXml(cod.value)}</td>
                        <td class="w3"><a class="button" href="<c:url value="/u/updatecode?mvid=${param.mvid}&cid=${cod.id}" />">Update/New version</a></td>
                     </tr>
                  </c:forEach>
                  
               </c:forEach>
            </tbody>
         </table>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

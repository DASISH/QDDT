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


            <div class="boxheader">Update code</div>

            <form action="<c:url value="/u/r/savecode" />" method="post">
            <input type="hidden" name="mvid" value="${param.mvid}">
            <input type="hidden" name="cid" value="${code.id}">

            <h4>Category:</h4>
            <p>${fn:escapeXml(code.category.label)}</p>

            <h4>Value:</h4>
            <input class="w6" type="text" name="value" value="${fn:escapeXml(code.value)}">

            <h4>Version description:</h4>
            <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(code.versionDescription)}</textarea>

            <div><input class="okbutton topmarg" type="submit" name="action" value="Save"></div>
            </form>


            <div class="boxheader">Choose category</div>

            <table>
               <tbody>
               <c:forEach items="${categories}" var="cat">
                  <tr>
                     <td>
                        <c:if test="${code.category.id == cat.id}">
                           <span class="helptext">Selected</span>
                        </c:if>
                        <c:if test="${code.category.id != cat.id}">
                           <form action="<c:url value="/u/r/newcode" />" method="post">
                              <input type="hidden" name="mvid" value="${param.mvid}">
                              <input type="hidden" name="cid" value="${cat.id}">
                              <input class="okbutton" type="submit" name="action" value="Use this">
                           </form>
                        </c:if>
                     </td>
                     <td>${fn:escapeXml(cat.label)}</td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

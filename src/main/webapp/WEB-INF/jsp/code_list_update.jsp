<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="qddt" uri="/WEB-INF/taglibrary.tld" %>

<c:import url="/WEB-INF/jspf/top.jsp" />


<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="responsedomain" />
      </c:import>

      <div class="tab-box">

         <c:if test="${param.saved != null}"><p class="ok">-- Save OK --</p></c:if>
         
         <div class="boxheader">Update code list</div>
         
         <form action="<c:url value="/u/r/savecodelist" />" method="post">
            <input type="hidden" name="mvid" value="${moduleVersion.id}">
            <input type="hidden" name="clid" value="${param.clid}">

            <h4>Name:</h4>
            <input class="w10" type="text" name="name" value="${fn:escapeXml(codeList.name)}">

            <h4>Label:</h4>
            <input class="w10" type="text" name="label" value="${fn:escapeXml(codeList.label)}">

            <h4>Description:</h4>
            <textarea class="w10" name="description" rows="5">${fn:escapeXml(codeList.description)}</textarea>

            <h4>Version description:</h4>
            <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(codeList.versionDescription)}</textarea>

            <div class="topmarg">
               <input class="okbutton" type="submit" name="action" value="Save">
            </div>
         </form>


            

            <div class="boxheader">Add codes to this code list</div>

            <table>
               <tbody>
               <c:forEach items="${codes}" var="c">
                  <tr>
                     <td>
                        <c:if test="${code.id == 999}">
                           <span class="helptext">Selected</span>
                        </c:if>
                        <c:if test="${code.id != 999}">
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

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

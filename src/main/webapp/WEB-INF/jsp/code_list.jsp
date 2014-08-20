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

         <p class="helptext">Create a new code list or update an existing code list.</p>
            
         <p>
            <a class="button" href="<c:url value="/u/newcodelist?mvid=${param.mvid}&type=v" />">New valid code list</a>
            <a class="button" href="<c:url value="/u/newcodelist?mvid=${param.mvid}&type=m" />">New missing code list</a>
            <a class="button" href="<c:url value="/u/newcodelist?mvid=${param.mvid}&type=c" />">New code list (valid + missing)</a>
         </p>

            <div class="boxheader">Update existing code lists</div>

               <c:forEach items="${codeLists}" var="cl">
                  <table class="topmarg">
                     <tbody>
                     <tr>
                        <td rowspan="${1 + fn:length(cl.codes)}"><a class="button" href="<c:url value="/u/updatecodelist?mvid=${param.mvid}&clid=${cl.id}" />">update</a></td>
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

               </c:forEach>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

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
            
         <p><a class="button" href="<c:url value="/u/updatecodelist?mvid=${param.mvid}" />">Create new code list</a></p>

            <div class="boxheader">Update existing code lists</div>

            <ul>
               <c:forEach items="${codeLists}" var="cl">
                  <li><a href="<c:url value="/u/updatecodelist?mvid=${param.mvid}&clid=${cl.id}" />">${fn:escapeXml(cl.label)}</a></li>
                  </c:forEach>
            </ul>

      </div>

   </div>
</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

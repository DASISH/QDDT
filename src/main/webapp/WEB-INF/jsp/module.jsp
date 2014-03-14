<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">

      <c:if test="${module == null}">
         <h1>New module</h1>
      </c:if>
      <c:if test="${module != null}">
         <h1>Update module details</h1>
      </c:if>

      <form class="box" action="<c:url value="/u/r/savemodule" />" method="post">

         <input type="hidden" name="id" value="${param.id}">

         <h4>Name:</h4>
         <input class="w10" type="text" name="name" value="${fn:escapeXml(module.name)}">

         <h4>Study:</h4>
         <c:if test="${module == null}">
            <p class="helptext">(Study cannot be changed after module is created.)</p>
            <select name="study">
               <option value=""></option>
               <c:forEach items="${studies}" var="s">
                  <option value="${s.id}">${fn:escapeXml(s.title)}</option>
               </c:forEach>
            </select>
         </c:if>
         <c:if test="${module != null}">
            ${fn:escapeXml(module.study.title)}
         </c:if>

         <h4>Maintenance agency:</h4>
         <c:if test="${module == null}">
            <p class="helptext">(Agency cannot be changed after module is created.)</p>
            <select name="agency">
               <option value=""></option>
               <c:forEach items="${agencies}" var="a">
                  <option value="${a.id}">${fn:escapeXml(a.name)} - ${fn:escapeXml(a.urnId)}</option>
               </c:forEach>
            </select>
         </c:if>
         <c:if test="${module != null}">
            ${fn:escapeXml(module.agency.name)} (${fn:escapeXml(module.agency.urnId)})
         </c:if>

         <h4>Repeat or new module?</h4>
         <p class="helptext">(Is this a repeat or new module for this survey?)</p>
         <div><input id="repeatYes" type="radio" name="repeat" value="yes" ${module.repeat ? 'checked' : ''}> <label for="repeatYes">Repeat</label></div>
         <div><input id="repeatNo" type="radio" name="repeat" value="no" ${module.repeat ? '' : 'checked'} > <label for="repeatNo">new</label></div>

         <p>
            <input class="okbutton topmarg" type="submit" value="Save">
            <c:if test="${module == null}"><a href="<c:url value="/u/" />">cancel</a></c:if>
            <c:if test="${module != null}"><a href="<c:url value="/u/history?id=${module.id}" />">cancel</a></c:if>
            </p>

         </form>

      </div>


   </div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

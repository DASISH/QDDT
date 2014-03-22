<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">

      <c:import url="/WEB-INF/jspf/module_header.jsp" />

      <c:import url="/WEB-INF/jspf/module_tabs.jsp">
         <c:param name="page" value="conceptscheme" />
      </c:import>

      <div class="tab-box">

         <div class="grid">
            <div class="col w4 border-r padding-r">

               <div class="boxheader">Concept hierarchy</div>
               <ul class="link-list">
                  <c:forEach items="${conceptScheme.concepts}" var="c">
                     <li><a ${param.cid eq c.id ? 'class="current"' : ''} href="?mvid=${moduleVersion.id}&cid=${c.id}"><c:out value="${c.name}" default="(name missing)" /></a>
                        <c:if test="${!empty c.subConcepts}">
                           <ul>
                              <c:forEach items="${c.subConcepts}" var="sub">
                                 <li><a ${param.cid eq sub.id ? 'class="current"' : ''} href="?mvid=${moduleVersion.id}&cid=${sub.id}"><c:out value="${sub.name}" default="(name missing)" /></a>
                                 </c:forEach>
                           </ul>
                        </c:if>
                     </li>
                  </c:forEach>
               </ul>
            </div>
            <div class="col w8 padding-l">


               <c:if test="${param.cid == null}">
                  <div class="boxheader">Concept scheme</div>


                  <form action="<c:url value="/u/r/saveconceptscheme" />" method="post">
                     <input type="hidden" name="mvid" value="${moduleVersion.id}">
                     <input type="hidden" name="csid" value="${conceptScheme.id}">

                     <h4>Name:</h4>
                     <input class="w10" type="text" name="name" value="${fn:escapeXml(conceptScheme.name)}">

                     <h4>Label:</h4>
                     <input class="w10" type="text" name="label" value="${fn:escapeXml(conceptScheme.label)}">

                     <h4>Description:</h4>
                     <textarea class="w10" name="description" rows="5">${fn:escapeXml(conceptScheme.description)}</textarea>

                     <h4>Version description:</h4>
                     <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(conceptScheme.versionDescription)}</textarea>

                     <c:if test="${conceptScheme == null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Create concept scheme"></div></c:if>
                     <c:if test="${conceptScheme != null}"><div><input class="okbutton topmarg" type="submit" name="action" value="Save"></div></c:if>
                     </form>


                  <c:if test="${conceptScheme != null}">
                     <div class="boxheader">Concepts</div>
                     <p class="helptext">
                        All concepts in this concept scheme are listed to the left. 
                        Click on the concept you want to update, or click the button below to add a new concept.
                     </p>

                     <form action="<c:url value="/u/r/newconcept" />" method="post">
                        <input type="hidden" name="mvid" value="${moduleVersion.id}">
                        <input type="hidden" name="csid" value="${conceptScheme.id}">
                        <input class="okbutton" type="submit" value="Add new concept">
                     </form>
                  </c:if>

               </c:if>


               <c:if test="${param.cid != null}">
                  <div class="boxheader">Concept</div>


                  <form action="<c:url value="/u/r/saveconcept" />" method="post">
                     <input type="hidden" name="mvid" value="${moduleVersion.id}">
                     <input type="hidden" name="csid" value="${conceptScheme.id}">
                     <input type="hidden" name="cid" value="${concept.id}">

                     <h4>Name:</h4>
                     <input class="w10" type="text" name="name" value="${fn:escapeXml(concept.name)}">

                     <h4>Label:</h4>
                     <input class="w10" type="text" name="label" value="${fn:escapeXml(concept.label)}">

                     <h4>Description:</h4>
                     <textarea class="w10" name="description" rows="5">${fn:escapeXml(concept.description)}</textarea>

                     <h4>Relationship with other concepts:</h4>
                     <textarea class="w10" name="relationship_concept" rows="5">${fn:escapeXml(concept.relationshipConcept)}</textarea>

                     <h4>Version description:</h4>
                     <textarea class="w10" name="version_description" rows="4">${fn:escapeXml(concept.versionDescription)}</textarea>

                     <div class="topmarg">
                        <input class="okbutton" type="submit" name="action" value="Update concept">
                        <input class="deletebutton" type="submit" name="action" value="Remove concept">
                     </div>

                  </form>

                  <h3>Comments</h3>

                  <c:forEach items="${comments}" var="comment">
                     <div class="comment w10">
                        <strong>(source: ${fn:escapeXml(comment.source)})</strong><br>
                        ${fn:escapeXml(comment.text)}
                     </div>
                  </c:forEach>

                  <button class="button" id="commlink">Add comment to this concept</button>
                  <form id="commform" action="<c:url value="" />" method="post" style="display: none;">
                     <div style="border: 1px solid #CCCCCC; background-color: #EEEEEE; padding: 20px;">Test</div>
                  </form>

                  <script>
                     $("#commlink").click(function() {
                        $("#commform").toggle();
                     });
                  </script>                     

               </c:if>


               <c:if test="${concept.id != null}">

                  <div class="boxheader">Sub-concepts</div>

                  <c:set var="c" value="${conceptScheme.conceptMap[concept.id]}" />
                  <ul class="plain-list">
                     <c:forEach items="${c.subConcepts}" var="sub">
                        <li><a href="?mvid=${param.mvid}&cid=${sub.id}"><c:out value="${sub.name}" default="(name missing)" /></a></li>
                        </c:forEach>
                  </ul>

                  <form action="<c:url value="/u/r/newconcept" />" method="post">
                     <input type="hidden" name="mvid" value="${moduleVersion.id}">
                     <input type="hidden" name="csid" value="${conceptScheme.id}">
                     <input type="hidden" name="pcid" value="${concept.id}">
                     <input class="okbutton" type="submit" value="Add new sub-concept">
                  </form>


                  <div class="boxheader">Questions</div>

                  <p>(Not implemented.)</p>
                  <%--
                  <form action="<c:url value="" />" method="post">
                     <input class="button" type="submit" value="Add question">
                  </form>

                  <h3>Comments</h3>

                  <a href="">Add comment to question group</a>
                  --%>

               </c:if>



            </div>
         </div>
      </div>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

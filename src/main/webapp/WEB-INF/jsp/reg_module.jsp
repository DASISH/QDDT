<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <h1>New module</h1>
      <ul class="header-tabs">
         <li class="active-tab"><a href="<c:url value="/u/r/savemodule" />">Title/Authors...</a></li>
         <li><a href="<c:url value="/u/r/savemodule" />">Concepts</a></li>
         <li><a href="<c:url value="/u/r/savemodule" />">Instrument</a></li>
      </ul>

      <form class="tab-box" action="<c:url value="/u/r/savemodule" />" method="post">

         <h3>Study:</h3>
         <input class="w10" type="text" name="study" value="">

         <h3>Title:</h3>
         <input class="w10" type="text" name="title" value="">

         <h3>Module Authors:</h3>
         <textarea class="w10" name="authors" rows="5"></textarea>

         <h3>Module Author's affiliation:</h3>
         <textarea class="w10" name="affiliation" rows="5"></textarea>

         <h3>Abstract:</h3>
         <textarea class="w10" name="abstract" rows="10"></textarea>

         <h3>Repeat or new module?</h3>
         <p>(Module previously used in same study)</p>
         <div><input id="repeatYes" type="radio" name="repeat" value="yes"> <label for="repeatYes">Repeat</label></div>
         <div><input id="repeatNo" type="radio" name="repeat" value="no"> <label for="repeatNo">new</label></div>


         <input class="button" type="submit" value="OK">

      </form>

   </div>


</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

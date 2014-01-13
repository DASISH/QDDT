<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <h1>New module</h1>
   </div>

   <form action="<c:url value="/u/r/savemodule" />" method="post">

      <div class="col w10">
         <h3>Study:</h3>
         <input class="w12" type="text" name="study" value="">
      </div>

      <div class="col w10">
         <h3>Title:</h3>
         <input class="w12" type="text" name="title" value="">
      </div>

      <div class="col w10">
         <h3>Module Authors:</h3>
         <textarea class="w12" name="authors" rows="5"></textarea>
      </div>

      <div class="col w10">
         <h3>Module Author's affiliation:</h3>
         <textarea class="w12" name="affiliation" rows="5"></textarea>
      </div>

      <div class="col w10">
         <h3>Abstract:</h3>
         <textarea class="w12" name="abstract" rows="10"></textarea>
      </div>

      <div class="col w10">
         <h3>Repeat or new module?</h3>
         <p>(Module previously used in same study)</p>
         <div><input id="repeatYes" type="radio" name="repeat" value="yes"> <label for="repeatYes">Repeat</label></div>
         <div><input id="repeatNo" type="radio" name="repeat" value="no"> <label for="repeatNo">new</label></div>
      </div>
      
      
      <div class="col">
         <input class="button" type="submit" value="OK">
      </div>

      </form>

</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

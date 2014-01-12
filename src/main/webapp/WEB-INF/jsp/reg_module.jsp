<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jspf/top.jsp" />

<div class="grid">

   <div class="col">
      <h1>New module</h1>
   </div>

   <form action="<c:url value="/u/r/savemodule" />" method="post">

      <div class="col w10">
         <h3>Title:</h3>
         <input style="width: 100%;" type="text" name="title" value="">
      </div>

      <div class="col w10">
         <h3>Module Authors:</h3>
         <textarea name="authors" rows="5"></textarea>
      </div>

      <div class="col w10">
         <h3>Module Author's affiliation:</h3>
         <textarea name="affiliation" rows="5"></textarea>
      </div>

      <div class="col w10">
         <h3>Abstract:</h3>
         <textarea name="abstract" rows="10"></textarea>
      </div>

      <div class="col">
         <input class="button" type="submit" value="OK">
      </div>

      </form>

</div>

<c:import url="/WEB-INF/jspf/bottom.jsp" />

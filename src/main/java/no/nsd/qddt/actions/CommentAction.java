package no.nsd.qddt.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.servlets.ServletUtil;

public class CommentAction {

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/comment.jsp", request, response);
   }
   
}

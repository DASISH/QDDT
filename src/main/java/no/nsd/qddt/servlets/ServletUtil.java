package no.nsd.qddt.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

   private ServletUtil() {
   }
   
   public static void forward(String url, ServletRequest request, ServletResponse response) throws ServletException, IOException {
      RequestDispatcher rd = request.getRequestDispatcher(url);
      rd.forward(request, response);
   }

   public static void redirect(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
      String url = request.getContextPath() + path;
      url = response.encodeRedirectURL(url);
      response.sendRedirect(url);
   }
   
   public static String getUriWithoutJsessionId(String uri) {
      if (uri == null) {
         return null;
      }
      int i = uri.indexOf(';');
      if (i == -1) {
         return uri;
      }
      return uri.substring(0, i);
   }
   
   public static Integer getRequestParamAsInteger(HttpServletRequest request, String param) {
      try {
         return Integer.valueOf(request.getParameter(param));
      } catch (Exception ignored) {
         return null;
      }      
   }
   
   
}

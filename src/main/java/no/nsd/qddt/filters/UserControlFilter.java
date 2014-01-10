package no.nsd.qddt.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.model.User;
import no.nsd.qddt.servlets.ServletUtil;

public class UserControlFilter implements Filter {
   
   private FilterConfig filterConfig;
   
   @Override
   public void init(FilterConfig filterConfig) {
      this.filterConfig = filterConfig;
   }
   
   @Override
   public void destroy() {
   }
   
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      User user = this.getUserFromRequest(request);
      if (user == null) {
         ServletUtil.forward("/WEB-INF/jsp/index.jsp", request, response);
      } else {
         chain.doFilter(request, response);
      }
   }
   
   private User getUserFromRequest(ServletRequest request) {
      HttpServletRequest httpReq = (HttpServletRequest) request;
      HttpSession httpSession = httpReq.getSession(false);
      User user = null;
      
      if (httpSession != null) {
         user = (User) httpSession.getAttribute("user");
      }
      return user;
   }
   
}

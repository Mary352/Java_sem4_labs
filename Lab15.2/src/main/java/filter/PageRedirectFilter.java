//package filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.xml.DOMConfigurator;
//
//
//
///*@WebFilter(filterName = "PageRedirectFilter",
//        urlPatterns = {"/secret/*"},
//        initParams = { @WebInitParam(name = "INDEX_PATH", value = "/login.jsp") })*/
//public class PageRedirectFilter implements Filter {
//    private static final Logger LOG = Logger.getLogger(PageRedirectFilter.class);
//    private String indexPath;
//    public void init(FilterConfig fConfig) throws ServletException {
//        indexPath = fConfig.getInitParameter("INDEX_PATH");
//    }
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(true);
//        if(session.getAttribute("auth") != "true")
//        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
//        chain.doFilter(request, response);
//        LOG.info("PageRedirectFilter is work");
//    }
//    public void destroy() {
//    }
//}

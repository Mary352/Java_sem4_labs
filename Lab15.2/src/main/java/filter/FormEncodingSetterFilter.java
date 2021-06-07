//package filter;
//
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//
//@WebFilter(urlPatterns = {"/*"},
//        initParams = { @WebInitParam(name = "encoding",
//                value = "UTF-8",
//                description = "Encoding Param") })
//public class FormEncodingSetterFilter implements Filter{
//    private String encoding;
//
//    public void destroy(){
//        encoding = null;
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp,
//                         FilterChain chain) throws ServletException, IOException{
//        String codeRequest = req.getCharacterEncoding();
//        if (encoding != null && !encoding.equalsIgnoreCase(codeRequest)) {
//            req.setCharacterEncoding(encoding);
//            resp.setCharacterEncoding(encoding);
//        }
//        chain.doFilter(req, resp);
//    }
//
//    public void init(FilterConfig config) throws ServletException{
//        encoding = config.getInitParameter("encoding");
//    }
//}
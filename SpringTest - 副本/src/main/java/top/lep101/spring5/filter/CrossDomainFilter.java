package top.lep101.spring5.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossDomainFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELECT,PUT");
        resp.setHeader("Access-Control-Max-Age","3600");
        resp.setHeader("Access-Control-Allow-Headers","x-auth-token,Origin,Access-Token,X-Requested-with,Content-Type, Accept");
        resp.setHeader("Access-Control-Allow-Credentials","true");

        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(200);
            return;
        }

        chain.doFilter(request,response);

    }

    public void destroy() {

    }
}

package iuh.fit.se.filtersample.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilter extends HttpFilter implements Filter {
    FilterConfig filterConfig;
    @Override
    public void init() throws ServletException {
        super.init();
        this.filterConfig = getFilterConfig();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.setContentType ("text/html");
        PrintWriter out = res.getWriter ();

        if (req.getParameter("username").equals(filterConfig.getInitParameter("username")) &&
                req.getParameter("password").equals(filterConfig.getInitParameter("password"))) {
            out.println ("Login success!");
            out.println ("<br/>");
            out.println ("<br/>");
            out.println ("<br/>");
            chain.doFilter (req, res);
        }
        else {
            out.println ("Login failed!");
        }
        out.println ("<br/>");
        out.println ("<br/>");
        out.println ("<br/>");
        out.close();
    }
}

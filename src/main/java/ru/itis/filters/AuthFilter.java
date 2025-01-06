package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Boolean isAthenticated = false;
        Boolean sessionExist = session != null;
        Boolean isLogin = request.getRequestURI().equals("/login");
        Boolean isRegistration = request.getRequestURI().equals("/registration");

        if (sessionExist) {
            isAthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAthenticated == null) {
                isAthenticated = false;
            }
        }

        if (isAthenticated && !isLogin || !isAthenticated && isLogin || !isAthenticated && isRegistration) {
            filterChain.doFilter(request, response);
        } else if (isAthenticated && (isLogin || isRegistration)) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}

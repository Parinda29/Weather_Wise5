package com.weatherwise.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.weatherwise.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN_PAGE = "/login";
    private static final String REGISTER_PAGE = "/register";
    private static final String HOME_PAGE = "/home";
    private static final String ROOT_PAGE = "/";
    private static final String[] PUBLIC_PAGES = {LOGIN_PAGE, REGISTER_PAGE, HOME_PAGE, ROOT_PAGE};
    private static final String[] PROTECTED_KEYWORDS = {"portfolio", "contact", "dashboard", "about_us"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional init logic
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String contextPath = req.getContextPath();  
        String uri = req.getRequestURI();         
        String path = uri.substring(contextPath.length());

        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;

        boolean isPublic = isPublicPage(path);
        boolean isStaticResource = isStaticFile(path);
        boolean isProtected = isProtectedPage(path);

        if (isStaticResource || isPublic) {
            chain.doFilter(request, response);  // Allow
            return;
        }

        if (!isLoggedIn && isProtected) {
            res.sendRedirect(contextPath + LOGIN_PAGE);
            return;
        }

        if (isLoggedIn && (path.equals(LOGIN_PAGE) || path.equals(REGISTER_PAGE))) {
            res.sendRedirect(contextPath + HOME_PAGE);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicPage(String path) {
        for (String publicPage : PUBLIC_PAGES) {
            if (path.equals(publicPage)) {
                return true;
            }
        }
        return false;
    }

    private boolean isProtectedPage(String path) {
        for (String keyword : PROTECTED_KEYWORDS) {
            if (path.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStaticFile(String path) {
        return path.matches(".*(\\.css|\\.js|\\.png|\\.jpg|\\.jpeg|\\.gif|\\.svg|\\.woff|\\.ttf|\\.eot|\\.ico)$");
    }

    @Override
    public void destroy() {
        // Optional cleanup logic
    }
}
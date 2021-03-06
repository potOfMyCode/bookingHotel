package controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LangFilter implements Filter{
    private final String CUR_LANG = "curLang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getParameter(CUR_LANG) != null) {
            request.getSession().setAttribute(CUR_LANG, request.getParameter(CUR_LANG));
        } else {
            request.getSession().setAttribute(CUR_LANG, Locale.getDefault().getLanguage());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

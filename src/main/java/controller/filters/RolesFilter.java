package controller.filters;

import model.entity.User;
import model.util.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RolesFilter implements Filter {
    private final String SESSION_USER = "sessionUser";

    private Map<Role, Set<String>> ways;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ways = new HashMap<>();

        Set<String> guestSet = new HashSet<>();
        Set<String> userSet = new HashSet<>();
        Set<String> adminSet = new HashSet<>();

        guestSet.add("/");
        guestSet.add("/login");
        guestSet.add("/registration");
        guestSet.add("/go_login");
        guestSet.add("/go_registration");
        guestSet.add("/numbers");
        guestSet.add("/go_double");
        guestSet.add("/go_single");
        guestSet.add("/go_triple");
        guestSet.add("/go_luxe");
        guestSet.add("/go_booking");
        guestSet.add("/operation_success");

        userSet.add("/");
        userSet.add("/logout");
        userSet.add("/numbers");
        userSet.add("/go_double");
        userSet.add("/go_single");
        userSet.add("/go_triple");
        userSet.add("/go_luxe");
        userSet.add("/go_booking");
        userSet.add("/go_orders");
        userSet.add("/booking");
        userSet.add("/operation_success");
        userSet.add("/operation_fail");
        userSet.add("/go_pay");
        userSet.add("/payByCard");
        userSet.add("/remove_application");

        adminSet.add("/");
        adminSet.add("/rooms");
        adminSet.add("/reserved_rooms");
        adminSet.add("/logout");
        adminSet.add("/go_double");
        adminSet.add("/go_single");
        adminSet.add("/go_triple");
        adminSet.add("/go_luxe");
        adminSet.add("/go_booking");
        adminSet.add("/booking");
        adminSet.add("/go_applications");
        adminSet.add("/confirm_application");
        adminSet.add("/operation_success");
        adminSet.add("/operation_fail");
        adminSet.add("/assign_room");
        adminSet.add("/refuse_application");
        adminSet.add("/remove_reservation");
        adminSet.add("/remove_application");
        adminSet.add("/search");

        Set<String> set =

        ways.put(Role.GUEST, guestSet);
        ways.put(Role.USER, userSet);
        ways.put(Role.ADMIN, adminSet);



    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().replace(request.getContextPath(), "").replace(request.getServletPath(), "");

        if (request.getSession().getAttribute(SESSION_USER) == null) {
            request.getSession().setAttribute(SESSION_USER, User.getGuestInst());
        }
        Role requestRole = ((User) request.getSession().getAttribute(SESSION_USER)).getRole();

        if (!ways.get(requestRole).contains(path)) {
            request.getRequestDispatcher("/WEB-INF/util/forbidden.jsp").forward(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
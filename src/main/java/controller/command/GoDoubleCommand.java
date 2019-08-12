package controller.command;

import model.entity.HotelNumber;
import model.entity.User;
import model.service.HotelNumberService;
import model.util.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class GoDoubleCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String CUR_ROLE = "curRole";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(SESSION_USER)).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(CUR_LANG)).orElse("en");
        String curRole = role.get().toString().toLowerCase();

        request.setAttribute(CUR_ROLE, curRole);

        return role.map(o -> "forward:/WEB-INF/util/double.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}

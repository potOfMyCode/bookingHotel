package controller.command;

import model.entity.HotelNumber;
import model.entity.User;
import model.service.HotelNumberService;
import model.util.Language;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ShowNumbersCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String  HOTEL_NUMBERS = "numbers";
    private final String CUR_ROLE = "curRole";

    private static Logger log = LogGen.getInstance();
    private HotelNumberService hotelNumberService;

    public ShowNumbersCommand(HotelNumberService hotelNumberService) {
        this.hotelNumberService = hotelNumberService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(SESSION_USER)).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Language.isLangOrGetDefault(localeTag));

        String curRole = role.get().toString().toLowerCase();

        request.setAttribute(CUR_ROLE, curRole);

        List<HotelNumber> numbers = hotelNumberService.getHotelNumbers();

        request.setAttribute(HOTEL_NUMBERS, numbers);

        return role.map(o -> "forward:/WEB-INF/util/numbers.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}

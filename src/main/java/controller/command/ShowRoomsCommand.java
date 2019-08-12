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

public class ShowRoomsCommand implements Command{
    private final String CUR_LANG = "curLang";
    private final String  HOTEL_NUMBERS = "numbers";

    private static Logger log = LogGen.getInstance();
    private HotelNumberService hotelNumberService;

    public ShowRoomsCommand(HotelNumberService hotelNumberService) {
        this.hotelNumberService = hotelNumberService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<HotelNumber> numbers = hotelNumberService.getHotelNumbers();

        request.setAttribute(HOTEL_NUMBERS, numbers);

        return "forward:/WEB-INF/admin/rooms.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

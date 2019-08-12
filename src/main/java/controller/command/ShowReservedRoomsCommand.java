package controller.command;

import model.dao.exception.DaoException;
import model.entity.Bill;
import model.entity.HotelNumber;
import model.entity.ReservedRoom;
import model.entity.User;
import model.service.BillService;
import model.service.HotelNumberService;
import model.service.ReservedRoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ShowReservedRoomsCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String  RESERVED_ROOMS = "reservedRoomsAndBills";
    private final String MESSAGE2 = "msg2";
    private final String LOCAL_RB_BASE_NAME = "lang";

    private static Logger log = LogGen.getInstance();
    private ReservedRoomService reservedRoomService;
    private BillService billService;

    public ShowReservedRoomsCommand(ReservedRoomService reservedRoomService,BillService billService) {
        this.reservedRoomService = reservedRoomService;
        this.billService = billService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        request.getSession().setAttribute("date1", "all time");
        request.getSession().setAttribute("date2", "");

        List<ReservedRoom> reservedRooms = null;
        try {
            reservedRooms = reservedRoomService.getAll();
        } catch (DaoException e) {
            log.error("No reserved rooms", e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("reserved.get.fail"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }
        Map<ReservedRoom, Bill> reservedRoomsAndBills  = new HashMap<>();

        for(ReservedRoom r: reservedRooms){
            Bill bill = null;
            try {
                bill = billService.getEntityById(r.getBillId());
            } catch (DaoException e) {
                log.error("No bill for reserved room", e);
            }
            reservedRoomsAndBills.put(r , bill);
        }

        request.setAttribute(RESERVED_ROOMS, reservedRoomsAndBills);

        return "forward:/WEB-INF/admin/reserved_rooms.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

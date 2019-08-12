package controller.command;

import model.dao.exception.DaoException;
import model.entity.Bill;
import model.entity.HotelNumber;
import model.entity.ReservedRoom;
import model.service.BillService;
import model.service.HotelNumberService;
import model.service.ReservedRoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SearchReservedRoomsCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String  RESERVED_ROOMS = "reservedRoomsAndBills";
    private final String MESSAGE2 = "msg2";
    private final String LOCAL_RB_BASE_NAME = "lang";

    private static Logger log = LogGen.getInstance();
    private ReservedRoomService reservedRoomService;
    private BillService billService;

    public SearchReservedRoomsCommand(ReservedRoomService reservedRoomService,BillService billService) {
        this.reservedRoomService = reservedRoomService;
        this.billService = billService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        List<ReservedRoom> reservedRooms = null;
        try {
            reservedRooms = reservedRoomService.getAll();
        } catch (DaoException e) {
            log.error("No reserved rooms", e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("reserved.get.fail"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }

        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        request.getSession().setAttribute("date1", dateFrom);
        request.getSession().setAttribute("date2", dateTo);

        if (!(dateFrom.compareTo(dateTo) < 0)){
            return "forward:/WEB-INF/admin/reserved_rooms.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }


        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        if (request.getSession().getAttribute(CUR_LANG).equals("uk")){
            format = new SimpleDateFormat("dd/MM/yyyy");
        }
        Date dateFirst = null;
        Date dateSecond = null;
        try {
            dateFirst = format.parse(dateFrom);
            dateSecond= format.parse(dateTo);
        } catch (ParseException ex) {
            log.error("Error in parsing date in rooms.jsp", ex);
        }

        List<ReservedRoom> finalReservedRooms = new ArrayList<>();

        for(ReservedRoom r: reservedRooms){
            if((dateFirst.before(r.getDate_before()) && dateFirst.after(r.getDate_from()))
                    || (dateSecond.after(r.getDate_from()) && (dateSecond.before(r.getDate_before()))) ||
                    (dateFirst.before(r.getDate_from()) && dateSecond.after(r.getDate_before())) ){
                finalReservedRooms.add(r);
            }
        }


        Map<ReservedRoom, Bill> reservedRoomsAndBills  = new HashMap<>();

        if (finalReservedRooms != null) {
            for(ReservedRoom r: finalReservedRooms){
                Bill bill = null;
                try {
                    bill = billService.getEntityById(r.getBillId());
                } catch (DaoException e) {
                    log.error("No bill for reserved room", e);
                }
                reservedRoomsAndBills.put(r , bill);
            }
        }


        request.setAttribute(RESERVED_ROOMS, reservedRoomsAndBills);

        return "forward:/WEB-INF/admin/reserved_rooms.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

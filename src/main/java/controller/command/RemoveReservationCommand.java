package controller.command;

import model.dao.exception.DaoException;
import model.entity.Bill;
import model.entity.ReservedRoom;
import model.service.BillService;
import model.service.ReservedRoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveReservationCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String  RESERVED_ROOMS = "reservedRoomsAndBills";
    private final String MESSAGE2 = "msg2";
    private final String LOCAL_RB_BASE_NAME = "lang";

    private static Logger log = LogGen.getInstance();
    private ReservedRoomService reservedRoomService;
    private BillService billService;

    public RemoveReservationCommand(ReservedRoomService reservedRoomService, BillService billService) {
        this.reservedRoomService = reservedRoomService;
        this.billService = billService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        String reservedRoomId = request.getParameter("reservedRoomId");

        ReservedRoom reservedRoom = null;

        try {
            reservedRoom = reservedRoomService.getById(Integer.valueOf(reservedRoomId));
        } catch (DaoException e) {
            log.error("No entity with id: " + Integer.valueOf(reservedRoomId), e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("error"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }

        Bill bill = null;

        try {
            bill = billService.getEntityById(reservedRoom.getBillId());
        } catch (DaoException e) {
            log.error("No entity with id: " +reservedRoom.getBillId(), e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("error"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }


        try {
            reservedRoomService.deleteReservAndBill(Integer.valueOf(reservedRoomId), bill.getId());
        } catch (DaoException e) {
            log.error("Reservation and account have not been deleted", e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("delete.reservation.fail"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }


        request.setAttribute(MESSAGE2, resourceBundle.getString("delete.reservation.success"));
        return "forward:/WEB-INF/util/operation_success.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

package controller.command;

import model.dao.exception.DaoException;
import model.entity.*;
import model.service.ApplicationService;
import model.service.HotelNumberService;
import model.service.ReservedRoomService;
import model.service.RoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class AssignRoomCommand implements Command{
    private final String LOCAL_RB_BASE_NAME = "lang";
    private final String CUR_LANG = "curLang";
    private final String MESSAGE2 = "msg2";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;
    private ReservedRoomService reservedRoomService;
    private RoomService roomService;
    private HotelNumberService hotelNumberService;

    public AssignRoomCommand(ApplicationService applicationService, ReservedRoomService reservedRoomService, RoomService roomService, HotelNumberService hotelNumberService) {
        this.applicationService = applicationService;
        this.reservedRoomService = reservedRoomService;
        this.roomService = roomService;
        this.hotelNumberService = hotelNumberService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        String applicationId = request.getParameter("applicationId");
        String clientId = request.getParameter("clientId");
        String roomForClientId = request.getParameter("roomForClientId");

        Application application = new Application();

        if (roomForClientId == null || roomForClientId.isEmpty()){
            request.setAttribute(MESSAGE2, resourceBundle.getString("assign.fail.fill"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }
        int roomId;
        try {
            roomId = Integer.valueOf(roomForClientId);
            if (roomId< 1 || roomId > 17)
                throw new NumberFormatException();
        }catch (NumberFormatException ex){
            request.setAttribute(MESSAGE2, resourceBundle.getString("assign.fail.onlyDigit"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }
        try {
            application = applicationService.getById(Integer.valueOf(applicationId));
        } catch (DaoException e) {
            log.error("No application with such id", e);
        }
        ReservedRoom reservedRoom = new ReservedRoom();
        reservedRoom.setUserId(Integer.valueOf(clientId));
        reservedRoom.setRoomId(roomId);
        reservedRoom.setApplicationId(Integer.valueOf(applicationId));
        reservedRoom.setDate_from(application.getDateFrom());
        reservedRoom.setDate_before(application.getDateTo());
        reservedRoom.setDateOfBooking(application.getDateOfBooking());

        Bill bill = new Bill();
        long totalDays = (reservedRoom.getDate_before().getTime()-reservedRoom.getDate_from().getTime())/1000/60/60/24;
        Room room = null;
        HotelNumber hotelNumber = null;
        try {
            room = roomService.getById(reservedRoom.getRoomId());
        } catch (DaoException e) {
            log.error("Such room not exist", e);
        }
        try {
            if (room != null) {
                hotelNumber = hotelNumberService.getById(room.getHotelNumberId());
            }
        } catch (DaoException e) {
            log.error("Such number not exist", e);
        }

        int price = 0;
        if (hotelNumber != null) {
            price = hotelNumber.getPrice();
        }else{
            request.setAttribute(MESSAGE2, resourceBundle.getString("assign.fail.no.price"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }
        int total = (int) (totalDays*price);
        bill.setTotal( total);
        bill.setStatus(false);

        int billId = bill.hashCode();
        bill.setId(billId);

        reservedRoom.setBillId(billId);

        try {
            reservedRoomService.createReservedRoomAndBill(reservedRoom, bill);
        } catch (DaoException e) {
            log.error("The room was not reserved", e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("assign.fail.error"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }


        request.setAttribute(MESSAGE2, resourceBundle.getString("assign.success"));
        return "forward:/WEB-INF/util/operation_success.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

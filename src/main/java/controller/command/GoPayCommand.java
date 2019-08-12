package controller.command;

import model.dao.exception.DaoException;
import model.entity.Application;
import model.entity.Bill;
import model.entity.ReservedRoom;
import model.entity.User;
import model.service.*;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GoPayCommand implements Command{
    private final String LOCAL_RB_BASE_NAME = "lang";
    private final String CUR_LANG = "curLang";
    private final String SESSION_USER = "sessionUser";
    private final String MESSAGE2 = "msg2";
    private final String CUR_APPLICATION = "curApplication";
    private final String CUR_RESERVED_ROOM = "curReservedRoom";
    private final String CUR_BILL = "curBill";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;
    private ReservedRoomService reservedRoomService;
    private BillService billService;

    public GoPayCommand(ApplicationService applicationService, ReservedRoomService reservedRoomService, BillService billService) {
        this.applicationService = applicationService;
        this.reservedRoomService = reservedRoomService;
        this.billService = billService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));


        String applicationIdString = request.getParameter("applicationId");

        User user = (User) request.getSession().getAttribute(SESSION_USER);

        int applicationId;
        if (applicationIdString == null) {
            Application application = (Application) request.getSession().getAttribute(CUR_APPLICATION);
            applicationId = application.getId();
        } else {
            applicationId = Integer.valueOf(applicationIdString);
        }
        Application application = null;

        ReservedRoom reservedRoom = null;
        try {
            reservedRoom = reservedRoomService.getByApplicationId(applicationId);
        } catch (DaoException e) {
            log.info("There are no reserved room for application with id: " + applicationId, e);
        }
        Bill bill = null;
        try {
            if (reservedRoom == null)
                throw new DaoException("No reserved room for this user");
            bill = billService.getEntityById(reservedRoom.getBillId());
            application = applicationService.getById(applicationId);
            if (bill == null)
                throw new DaoException("No bill for reserved room");
        } catch (DaoException e) {
            log.error("No bill for reserved room: " + reservedRoom, e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("pay.no.reserved"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }

        request.getSession().setAttribute(CUR_APPLICATION, application);
        request.getSession().setAttribute(CUR_BILL, bill);
        request.getSession().setAttribute(CUR_RESERVED_ROOM, reservedRoom);

//        try {
//            billService.updateStatus(bill);
//        } catch (DaoException e) {
//            log.error("Payment was not accepted" , e);
//            request.setAttribute(MESSAGE2, resourceBundle.getString("pay.no.accept"));
//            return "forward:/WEB-INF/util/operation_fail.jsp" +
//                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
//        }
        return "forward:/WEB-INF/user/pay.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

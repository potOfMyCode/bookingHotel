package controller.command;

import model.dao.exception.DaoException;
import model.service.ApplicationService;
import model.service.BillService;
import model.service.ReservedRoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveApplicationCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String  RESERVED_ROOMS = "reservedRoomsAndBills";
    private final String MESSAGE2 = "msg2";
    private final String LOCAL_RB_BASE_NAME = "lang";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;

    public RemoveApplicationCommand(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        String applicationId = request.getParameter("applicationId");

        try {
            applicationService.deleteById(Integer.valueOf(applicationId));
        } catch (DaoException e) {
            log.error("Application not remove", e);
            request.setAttribute(MESSAGE2, resourceBundle.getString("delete.application.fail"));
            return "forward:/WEB-INF/util/operation_fail.jsp" +
                    (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }


        request.setAttribute(MESSAGE2, resourceBundle.getString("delete.application.success"));
        return "forward:/WEB-INF/util/operation_success.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

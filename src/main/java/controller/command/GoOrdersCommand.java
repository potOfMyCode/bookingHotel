package controller.command;

import model.dao.exception.DaoException;
import model.entity.Application;
import model.entity.ReservedRoom;
import model.entity.User;
import model.service.ApplicationService;
import model.service.ReservedRoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.util.LogMsg.NO_ORDERS_FOR_THIS_USER;

public class GoOrdersCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String APPLICATIONS_FOR_CURRENT_USER = "applicationsForCurrentUser";
    private final String CUR_ROLE = "curRole";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;

    public GoOrdersCommand(ApplicationService applicationService){
        this.applicationService = applicationService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(SESSION_USER)).getRole());

        User user = (User) request.getSession().getAttribute(SESSION_USER);
        int id = user.getId();
        List<Application> applicationsForCurrentUser = null;
        try {
            applicationsForCurrentUser = applicationService.getApplicationsForUser(id);
        } catch (DaoException e) {
            log.error(NO_ORDERS_FOR_THIS_USER, e);
        }

        request.setAttribute(APPLICATIONS_FOR_CURRENT_USER, applicationsForCurrentUser);

        return "forward:/WEB-INF/user/orders.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

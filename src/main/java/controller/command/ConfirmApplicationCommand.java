package controller.command;

import model.dao.exception.DaoException;
import model.entity.Application;
import model.service.ApplicationService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConfirmApplicationCommand implements Command{
    private final String LOCAL_RB_BASE_NAME = "lang";
    private final String CUR_LANG = "curLang";
    private final String SESSION_USER = "sessionUser";
    private final String MESSAGE2 = "msg2";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;

    public ConfirmApplicationCommand(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        String applicationId = request.getParameter("applicationId");

        Application application = new Application();

        try {
            application = applicationService.getById(Integer.valueOf(applicationId));
        } catch (DaoException e) {
            log.error("No application with such id", e);
        }
        application.setNote(null);

        try {
            applicationService.updateStatus(application);
        } catch (DaoException e) {
            log.error("Cannot to update this application", e);
        }

        request.setAttribute(MESSAGE2, resourceBundle.getString("confirm.success"));
        return "forward:/WEB-INF/util/operation_success.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

package controller.command;

import model.entity.Application;
import model.entity.User;
import model.service.ApplicationService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GoApplicationsCommand implements Command{
    private final String SESSION_USER = "sessionUser";
    private final String CUR_LANG = "curLang";
    private final String APPLICATIONS = "applications";
    private final String CUR_ROLE = "curRole";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;

    public GoApplicationsCommand(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(SESSION_USER)).getRole());


        List<Application> applications = applicationService.getApplications();

        request.setAttribute(APPLICATIONS, applications);

        return "forward:/WEB-INF/admin/applications.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OperationFailCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/WEB-INF/util/operation_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

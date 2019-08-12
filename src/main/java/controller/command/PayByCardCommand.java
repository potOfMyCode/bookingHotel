package controller.command;

import model.dao.exception.DaoException;
import model.entity.Application;
import model.entity.Bill;
import model.service.ApplicationService;
import model.service.BillService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class PayByCardCommand implements Command{
    private final String LOCAL_RB_BASE_NAME = "lang";
    private final String CUR_LANG = "curLang";
    private final String SESSION_USER = "sessionUser";
    private final String MESSAGE2 = "msg2";
    private final String CUR_BILL = "curBill";

    private static Logger log = LogGen.getInstance();
    private BillService billService;

    public PayByCardCommand(BillService billService) {
        this.billService = billService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        Bill bill= (Bill) request.getSession().getAttribute(CUR_BILL);


        try {
            billService.updateStatus(bill);
        } catch (DaoException e) {
            log.error("Cannot to confirm payment", e);
        }

        request.setAttribute(MESSAGE2, resourceBundle.getString("confirm.payment"));
        return "forward:/WEB-INF/util/operation_success.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}

package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.exception.DaoException;
import model.dao.exception.NoSuchUserException;
import model.entity.User;
import model.service.exception.ServiceException;
import model.util.LogGen;
import model.util.Regexp;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static model.util.LogMsg.EXCEPTION_WRITE_RESPONSE;
import static model.util.LogMsg.USER_WAS_LOGGED_IN;

public class UserService {

    private Logger log = LogGen.getInstance();
    private String usernameRegex = Regexp.USERNAME_REGEX;
    private String emailRegex = Regexp.MAIL_REGEX;
    private String passRegex = Regexp.PASSWORD_REGEX;

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public User login(String usernameOrMail) throws NoSuchUserException {
        User user = new User();

        try (UserDao dao = daoFactory.createUserDao()) {
            if (usernameOrMail.matches(emailRegex)) {
                user = dao.getEntityByEmail(usernameOrMail);
            } else if (usernameOrMail.matches(usernameRegex)) {
                user = dao.getEntityByUsername(usernameOrMail);
            }
        }
        if (!user.notEmpty())
            throw new NoSuchUserException("No such user");
        return user;
    }

    public void authorize(User user, String password) throws ServiceException {
        if (!password.equals(user.getPassword())) {
            throw new ServiceException("User: (username=" + user.getUsername() + ", password=" + user.getPassword() +
                    ") Couldn't authorize with password: " + password);
        }
    }

    public void register(User user) throws DaoException {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
        }
    }

    public boolean validate(String usernameOrMail, String password) {
        return usernameOrMail != null && !usernameOrMail.isEmpty() && password != null && !password.isEmpty();
    }

    public boolean ifInvalidRegData(String username, String email, String pass, String confPass) {
        return !username.matches(usernameRegex) || !email.matches(emailRegex) || !pass.matches(passRegex) || !pass.equals(confPass);
    }

    public String getFaultRegistrationReason(String username, String email, String pass, String confPass, ResourceBundle resBundle) {
        String faultReson = "no fault";
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || confPass.isEmpty()) {
            faultReson = resBundle.getString("invalid.fillAll");
        } else if (!username.matches(usernameRegex)) {
            faultReson = resBundle.getString("register.bad.username");
        } else if (!email.matches(emailRegex)) {
            faultReson = resBundle.getString("register.bad.email");
        } else if (!pass.matches(passRegex)) {
            faultReson = resBundle.getString("register.bad.password");
        } else if (!pass.equals(confPass)) {
            faultReson = resBundle.getString("register.confirm.pass");
        }
        return faultReson;
    }
}

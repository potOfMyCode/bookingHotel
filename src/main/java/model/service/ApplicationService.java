package model.service;

import model.dao.ApplicationDao;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.exception.DaoException;
import model.entity.Application;

import java.util.ArrayList;
import java.util.List;

public class ApplicationService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createApplication(Application application) throws DaoException {
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            dao.create(application);
        }
    }

    public List<Application> getApplications() {
        List<Application> applications;
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            applications = dao.getAll();
        }
        return applications;
    }

    public boolean validate(String name, String surname, String dateFrom, String dateTo, String count, String kids, String type) {
        return name != null && !name.isEmpty() && surname != null && !surname.isEmpty() &&
                dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty() &&
                count != null && !count.isEmpty() && kids != null && !kids.isEmpty() &&
                type != null && !type.isEmpty();
    }

    public boolean validateDate(String dateFrom, String dateTo) {
        return !(dateFrom.compareTo(dateTo) < 0);
    }

    public List<Application> getApplicationsForUser(int id) throws DaoException{
        List<Application> applications;
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            applications = dao.getEntityByUserId(id);
        }
        return applications;
    }
    public void updateStatus(Application application) throws DaoException {
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            dao.update(application);
        }
    }
    public void updateNote(Application application) throws DaoException {
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            dao.update(application);
        }
    }
    public Application getById(int id) throws DaoException {
        Application application = null;
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            application = dao.getEntityById(id);
        }
        return application;
    }

    public void deleteById(int ApplicationId) throws DaoException {
        try (ApplicationDao dao = daoFactory.createApplicationDao()) {
            dao.delete(ApplicationId);
        }
    }
}

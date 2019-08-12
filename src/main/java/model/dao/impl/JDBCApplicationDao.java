package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.ApplicationDao;
import model.dao.exception.DaoException;
import model.dao.mapper.ApplicationMapper;
import model.entity.Application;
import model.service.ApplicationService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.util.LogMsg.*;

public class JDBCApplicationDao extends AbstractDao implements ApplicationDao {
    private String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    private String CANT_CLOSE_CONNECTION = "Couldn't close connection";

    private Connection connection;
    private Logger log = LogGen.getInstance();

    JDBCApplicationDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Application> getAll() {
        List<Application> applications = new ArrayList<>();
        String sqlQuery = "SELECT * FROM booking_hotel.applications order by id desc";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in ApplicationDao getAll()");

            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ApplicationDao getAll()");

                ApplicationMapper applicationMapper = new ApplicationMapper();

                while (resultSet.next()) {
                    Application application = applicationMapper.extractFromResultSet(resultSet,1,2,3,4,5,6,7,8,9,10,11,12);

                    applications.add(application);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return applications;
    }

    @Override
    public Application update(Application entity) throws DaoException {
        String sqlQuery = "UPDATE `booking_hotel`.`applications`\n" +
                "                SET applications.`status` = true , applications.`note` = ?\n" +
                "                WHERE applications.id=?";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setString(1, entity.getNote());
            prepStatement.setInt(2, entity.getId());

            log.debug(PREP_STAT_OPENED + " in ApplicationDao update()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in ApplicationDao update()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_UPDATE, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return entity;
    }

    @Override
    public Application getEntityById(Integer id) throws DaoException {
        String sqlQuery = "SELECT * FROM booking_hotel.applications WHERE id = ?;";

        Application application = new Application();
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, id);
            log.debug(PREP_STAT_OPENED + " in ApplicationDao getEntityById()");

            ApplicationMapper applicationMapper = new ApplicationMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ApplicationDao getEntityById()");

                if (!resultSet.isBeforeFirst()) {
                    throw new DaoException("No such entity in the DB");
                }

                while (resultSet.next()) {
                    application = applicationMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6,7,8,9,10,11,12);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return application;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sqlQuery = "DELETE FROM booking_hotel.applications WHERE id = " + id;

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in ApplicationDao delete()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in ApplicationDao delete()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_DELETING, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
    }

    @Override
    public void create(Application entity) throws DaoException {
        String sqlQuery = "INSERT INTO `booking_hotel`.`applications` (`users_id`, `name`, `surname`, `amount_people`, `amount_children`, " +
                "`type_of_number`, `note`, `dateFrom`, `dateTo`, `date_of_booking`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, entity.getUserId());
            prepStatement.setString(2, entity.getName());
            prepStatement.setString(3, entity.getSurname());
            prepStatement.setInt(4, entity.getAmountPeople());
            prepStatement.setInt(5, entity.getAmounChildren());
            prepStatement.setString(6, entity.getTypeOfNumber());
            prepStatement.setString(7, entity.getNote());

            prepStatement.setDate(8, new java.sql.Date(entity.getDateFrom().getTime()+24*60*60*1000));
            prepStatement.setDate(9,  new java.sql.Date(entity.getDateTo().getTime()+24*60*60*1000));
            prepStatement.setTimestamp(10, entity.getDateOfBooking());
            prepStatement.setBoolean(11, entity.getStatus());

            log.debug(PREP_STAT_OPENED + " in ApplicationDao create()");

            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in ApplicationDao create()");
            } catch (SQLIntegrityConstraintViolationException e) {
                log.info(SUCH_USER_ALREADY_EXISTS, e);
                throw new DaoException(e.getMessage(), e);
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_CREATE, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
            log.debug(CONNECTION_CLOSED);
        } catch (SQLException e) {
            log.error(CANT_CLOSE_CONNECTION, e);
        }
    }

    @Override
    public List<Application> getEntityByUserId(int id) {
        List<Application> applications = new ArrayList<>();
        String sqlQuery = "SELECT * FROM booking_hotel.applications as a where a.users_id = ?";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, id);

            log.debug(PREP_STAT_OPENED + " in ApplicationDao getEntityByUserId(int id)");

            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ApplicationDao getEntityByUserId(int id)");

                ApplicationMapper applicationMapper = new ApplicationMapper();

                while (resultSet.next()) {
                    Application application = applicationMapper.extractFromResultSet(resultSet,1,2,3,4,5,6,7,8,9,10,11,12);

                    applications.add(application);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return applications;
    }
}

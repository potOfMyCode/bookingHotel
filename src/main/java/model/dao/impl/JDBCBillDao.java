package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.BillDao;
import model.dao.exception.DaoException;
import model.dao.mapper.ApplicationMapper;
import model.dao.mapper.BillMapper;
import model.entity.Application;
import model.entity.Bill;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static model.util.LogMsg.*;

public class JDBCBillDao extends AbstractDao implements BillDao {
    private String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    private String CANT_CLOSE_CONNECTION = "Couldn't close connection";

    private Connection connection;
    private Logger log = LogGen.getInstance();

    JDBCBillDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Bill> getAll() {
        return null;
    }

    @Override
    public Bill update(Bill entity) throws DaoException {
        String sqlQuery = "UPDATE `booking_hotel`.`bills` as b " +
                "SET b.`status` = true " +
                "WHERE b.`id` = ?";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, entity.getId());

            log.debug(PREP_STAT_OPENED + " in BillDao update()");
            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in BillDao update()");
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_UPDATE, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return entity;
    }

    @Override
    public Bill getEntityById(Integer id) throws DaoException {
        String sqlQuery = "SELECT * FROM booking_hotel.bills WHERE id = ?;";

        Bill bill = new Bill();
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, id);
            log.debug(PREP_STAT_OPENED + " in BillDao getEntityById()");

            BillMapper billMapper = new BillMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in BillDao getEntityById()");

                if (!resultSet.isBeforeFirst()) {
                    throw new DaoException("No such entity in the DB");
                }

                while (resultSet.next()) {
                    bill = billMapper.extractFromResultSet(resultSet, 1,2,3);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return bill;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public void create(Bill entity) throws DaoException {
        String sqlQuery = "INSERT INTO `booking_hotel`.`bills` (`id`, `total`, `status`) VALUES (?,?,?)";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, entity.getId());
            prepStatement.setInt(2, entity.getTotal());
            prepStatement.setBoolean(3, entity.getStatus());
            log.debug(PREP_STAT_OPENED + " in BillDao create()");

            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in BillDao create()");
            } catch (SQLIntegrityConstraintViolationException e) {
                log.info(SUCH_ID_NOT_EXIST, e);
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
}

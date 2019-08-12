package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.ReservedRoomDao;
import model.dao.exception.DaoException;
import model.dao.mapper.ApplicationMapper;
import model.dao.mapper.BillMapper;
import model.dao.mapper.ReservedRoomMapper;
import model.entity.Application;
import model.entity.Bill;
import model.entity.ReservedRoom;
import model.service.ReservedRoomService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static model.util.LogMsg.*;

public class JDBCReservedRoomDao extends AbstractDao implements ReservedRoomDao {
    private String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    private String CANT_CLOSE_CONNECTION = "Couldn't close connection";

    private Connection connection;
    private Logger log = LogGen.getInstance();

    JDBCReservedRoomDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ReservedRoom> getAll() {
        List<ReservedRoom> reservedRooms = new ArrayList<>();
        String sqlQuery = "SELECT * FROM booking_hotel.reserved_rooms order by date_from";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao getAll()");

            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ReservedRoomDao getAll()");

                ReservedRoomMapper reservedRoomMapper = new ReservedRoomMapper();

                while (resultSet.next()) {
                    ReservedRoom reservedRoom = reservedRoomMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6,7,8);

                    reservedRooms.add(reservedRoom);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return reservedRooms;
    }

    @Override
    public ReservedRoom update(ReservedRoom entity) throws DaoException {
        return null;
    }

    @Override
    public ReservedRoom getEntityById(Integer id) throws DaoException {
        String sqlQuery = "SELECT * FROM booking_hotel.reserved_rooms WHERE id = ?;";

        ReservedRoom reservedRoom = new ReservedRoom();
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, id);
            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao getEntityById()");

            ReservedRoomMapper reservedRoomMapper = new ReservedRoomMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ReservedRoomDao getEntityById()");

                if (!resultSet.isBeforeFirst()) {
                    throw new DaoException("No such entity in the DB");
                }

                while (resultSet.next()) {
                    reservedRoom = reservedRoomMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6,7,8);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return reservedRoom;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public void create(ReservedRoom entity) throws DaoException {
        String sqlQuery = "INSERT INTO `booking_hotel`.`reserved_rooms` (`user_id`, `room_id`, `date_from`, `date_before`, `date_of_booking`) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement prepStatement1 = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao create()");
            prepStatement1.setInt(1, entity.getUserId());
            prepStatement1.setInt(2, entity.getRoomId());
            prepStatement1.setInt(3, entity.getBillId());
            prepStatement1.setInt(4,entity.getApplicationId());
            prepStatement1.setDate(5, new java.sql.Date(entity.getDate_from().getTime()));
            prepStatement1.setDate(6, new java.sql.Date(entity.getDate_before().getTime()));
            prepStatement1.setTimestamp(7, entity.getDateOfBooking());


            try {
                prepStatement1.execute();
                log.debug(QUERY_EXECUTED + " in ReservedRoomDao create()");
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

    public void createReservationAndBill(ReservedRoom reservedRoom, Bill bill) throws DaoException{
        String sqlQuery1 = "INSERT INTO `booking_hotel`.`reserved_rooms` (`user_id`, `room_id`, `bills_id`, `applications_id`, `date_from`," +
                " `date_before`, `date_of_booking`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlQuery2 = "INSERT INTO `booking_hotel`.`bills` (`id`, `total`, `status`) VALUES (?,?,?)";

        try (PreparedStatement prepStatement1 = connection.prepareStatement(sqlQuery1);
             PreparedStatement prepStatement2 = connection.prepareStatement(sqlQuery2)){

            connection.setAutoCommit(false);

            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao createReservationAndBill()");
            prepStatement1.setInt(1, reservedRoom.getUserId());
            prepStatement1.setInt(2, reservedRoom.getRoomId());
            prepStatement1.setInt(3, reservedRoom.getBillId());
            prepStatement1.setInt(4,reservedRoom.getApplicationId());
            prepStatement1.setDate(5, new java.sql.Date(reservedRoom.getDate_from().getTime()));
            prepStatement1.setDate(6, new java.sql.Date(reservedRoom.getDate_before().getTime()));
            prepStatement1.setTimestamp(7, reservedRoom.getDateOfBooking());

            prepStatement2.setInt(1, bill.getId());
            prepStatement2.setInt(2, bill.getTotal());
            prepStatement2.setBoolean(3, bill.getStatus());

            prepStatement2.execute();

            prepStatement1.execute();

            connection.commit();

        } catch (SQLIntegrityConstraintViolationException e){
            throw new DaoException("Couldn't create bill and reservation");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error(EXCEPTION_IN_ROLLBACK, ex);
            }
            log.error(EXCEPTION_IN_PREPARED_STATEMENT, e);
        }
    }

    @Override
    public ReservedRoom getByApplicationId(int applicationId) throws DaoException{
        String sqlQuery = "SELECT * FROM booking_hotel.reserved_rooms WHERE applications_id = ?;";

        ReservedRoom reservedRoom = null;
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, applicationId);
            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao getByApplicationId()");

            ReservedRoomMapper reservedRoomMapper = new ReservedRoomMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ReservedRoomDao getByApplicationId()");

                if (!resultSet.isBeforeFirst()) {
                    throw new DaoException("No reserved room for this user");
                }
                while (resultSet.next()) {
                    reservedRoom = reservedRoomMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6,7,8);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return reservedRoom;
    }

    @Override
    public void deleteReservAndBill(int reservedRoomId, int billId) throws DaoException {
        String sqlQuery1 = "DELETE FROM `booking_hotel`.reserved_rooms WHERE id = " + reservedRoomId;
        String sqlQuery2 = "DELETE FROM `booking_hotel`.bills WHERE id = " + billId;

        try (PreparedStatement prepStatement1 = connection.prepareStatement(sqlQuery1);
             PreparedStatement prepStatement2 = connection.prepareStatement(sqlQuery2)){

            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao deleteReservAndBill()");
            try {
                connection.setAutoCommit(false);
                prepStatement1.execute();
                prepStatement2.execute();
                log.debug(QUERY_EXECUTED + " in ReservedRoomDao deleteReservAndBill()");
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                log.error(SQL_EXCEPTION_WHILE_DELETING, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
    }

    @Override
    public ReservedRoom getLastEntity() throws DaoException {
        String sqlQuery = "select * from reserved_rooms order by id desc limit 1;";

        ReservedRoom reservedRoom = new ReservedRoom();
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in ReservedRoomDao getLastEntity()");

            ReservedRoomMapper reservedRoomMapper = new ReservedRoomMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in ReservedRoomDao getLastEntity()");

                while (resultSet.next()) {
                    reservedRoom = reservedRoomMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return reservedRoom;
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

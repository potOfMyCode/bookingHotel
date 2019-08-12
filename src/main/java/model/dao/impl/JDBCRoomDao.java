package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.RoomDao;
import model.dao.exception.DaoException;
import model.dao.mapper.ApplicationMapper;
import model.dao.mapper.RoomMapper;
import model.entity.Application;
import model.entity.Room;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static model.util.LogMsg.*;
import static model.util.LogMsg.EXCEPTION_IN_PREPARED_STATEMENT_PROCESS;

public class JDBCRoomDao extends AbstractDao implements RoomDao {
    private String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    private String CANT_CLOSE_CONNECTION = "Couldn't close connection";

    private Connection connection;
    private Logger log = LogGen.getInstance();

    JDBCRoomDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public Room update(Room entity) throws DaoException {
        return null;
    }

    @Override
    public Room getEntityById(Integer id) throws DaoException {
        String sqlQuery = "SELECT * FROM booking_hotel.rooms WHERE id = ?;";

        Room room = new Room();
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, id);
            log.debug(PREP_STAT_OPENED + " in RoomDao getEntityById()");

            RoomMapper roomMapper = new RoomMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in RoomDao getEntityById()");

                if (!resultSet.isBeforeFirst()) {
                    throw new DaoException("No such entity in the DB");
                }

                while (resultSet.next()) {
                    room = roomMapper.extractFromResultSet(resultSet, 1,2,3);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return room;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public void create(Room entity) throws DaoException {

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

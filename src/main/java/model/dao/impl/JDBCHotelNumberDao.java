package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.HotelNumberDao;
import model.dao.exception.DaoException;
import model.dao.mapper.HotelNumberMapper;
import model.dao.mapper.ObjectMapper;
import model.dao.mapper.RoomMapper;
import model.dao.mapper.UserMapper;
import model.entity.HotelNumber;
import model.entity.Room;
import model.entity.User;
import model.util.LogGen;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static model.util.LogMsg.*;
import static model.util.LogMsg.EXCEPTION_IN_PREPARED_STATEMENT_PROCESS;

public class JDBCHotelNumberDao extends AbstractDao implements HotelNumberDao {
    private String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    private String CANT_CLOSE_CONNECTION = "Couldn't close connection";

    private Connection connection;
    private Logger log = LogGen.getInstance();

    JDBCHotelNumberDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<HotelNumber> getAll() {

        Map<Integer, HotelNumber> hotelNumbers = new HashMap<>();
        String sqlQuery = "SELECT * FROM booking_hotel.hotel_numbers as hn \n" +
                "left join booking_hotel.rooms as r on hn.id = r.hotel_number_id";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in HotelNumberDao getAll()");

            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in HotelNumberDao getAll()");

                HotelNumberMapper hotelNumberMapper = new HotelNumberMapper();
                RoomMapper roomMapper = new RoomMapper();

                while (resultSet.next()) {
                    HotelNumber hotelNumber = hotelNumberMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6);

                    Room room = roomMapper.extractFromResultSet(resultSet, 7,8,9);
                    hotelNumber.getRooms().add(room);
                    HotelNumber hotelNumber1 = new HotelNumber();
                    if ((hotelNumber1 = hotelNumbers.putIfAbsent(hotelNumber.getId(), hotelNumber)) != null)
                        hotelNumber1.getRooms().add(room);

                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return new ArrayList<>(hotelNumbers.values());
    }

    @Override
    public HotelNumber update(HotelNumber entity) throws DaoException {
        return null;
    }

    @Override
    public HotelNumber getEntityById(Integer id) throws DaoException {
        String sqlQuery = "SELECT * FROM booking_hotel.hotel_numbers WHERE id = ?;";

        HotelNumber hotelNumber = new HotelNumber();
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setInt(1, id);
            log.debug(PREP_STAT_OPENED + " in HotelNumberDao getEntityById()");

            HotelNumberMapper hotelNumberMapper = new HotelNumberMapper();
            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in HotelNumberDao getEntityById()");

                if (!resultSet.isBeforeFirst()) {
                    throw new DaoException("No such entity in the DB");
                }

                while (resultSet.next()) {
                    hotelNumber = hotelNumberMapper.extractFromResultSet(resultSet, 1,2,3,4,5,6);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return hotelNumber;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public void create(HotelNumber entity) throws DaoException {

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

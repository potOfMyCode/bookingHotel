package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.UserDao;
import model.dao.exception.DaoException;
import model.dao.exception.NoSuchUserException;
import model.dao.mapper.ObjectMapper;
import model.dao.mapper.UserMapper;
import model.entity.User;
import model.util.LogGen;
import model.util.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

import static model.util.LogMsg.*;
import static model.util.LogMsg.EXCEPTION_IN_PREPARED_STATEMENT_PROCESS;

public class JDBCUserDao extends AbstractDao implements UserDao {
    private String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    private String CANT_CLOSE_CONNECTION = "Couldn't close connection";

    private Connection connection;
    private Logger log = LogGen.getInstance();

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getEntityByUsername(String login) throws NoSuchUserException {
        String query = "select * from booking_hotel.users as u where u.username = ?";
        return getUser(login, query);
    }

    @Override
    public User getEntityByEmail(String email) throws NoSuchUserException {
        String query = "select * from booking_hotel.users as u where u.email = ?";
        return getUser(email, query);
    }

    private User getUser(String email, String query) throws NoSuchUserException {
        User user = null;
        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            ObjectMapper<User> mapper= new UserMapper();

            if(rs.next()){
                user = mapper.extractFromResultSet(rs, 1,2,3,4,5);
            }
            if ( user == null)
                throw new NoSuchUserException("No such user");

        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>(20);
        String sqlQuery = "SELECT * FROM booking_hotel.users;";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            log.debug(PREP_STAT_OPENED + " in UserDao getAll()");

            try (ResultSet resultSet = prepStatement.executeQuery();){

                log.debug(QUERY_EXECUTED + " in UserDao getAll()");

                UserMapper userMapper = new UserMapper();
                while (resultSet.next()) {

                    User user = new User();
                    user = userMapper.extractFromResultSet(resultSet, 1,2,3,4,5);
                    list.add(user);
                }
            } catch (SQLException e) {
                log.error(SQL_EXCEPTION_WHILE_READING_FROM_DB, e);
            }
        } catch (SQLException e) {
            log.error(EXCEPTION_IN_PREPARED_STATEMENT_PROCESS, e);
        }
        return list;
    }

    @Override
    public User update(User entity) throws DaoException {
        return null;
    }

    @Override
    public User getEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public void create(User entity) throws DaoException {
        String sqlQuery = "INSERT INTO `booking_hotel`.`users` (`username`, `email`, `password`, `role`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);){

            prepStatement.setString(1, entity.getUsername());
            prepStatement.setString(2, entity.getEmail());
            prepStatement.setString(3, entity.getPassword());
            prepStatement.setString(4, entity.getRole().getString());
            log.debug(PREP_STAT_OPENED + " in UserDao create()");

            try {
                prepStatement.execute();
                log.debug(QUERY_EXECUTED + " in UserDao create()");
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
}

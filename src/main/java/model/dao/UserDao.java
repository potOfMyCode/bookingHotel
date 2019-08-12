package model.dao;

import model.dao.exception.DaoException;
import model.dao.exception.NoSuchUserException;
import model.entity.User;

public interface UserDao extends GenericDao<User, Integer> {

    User getEntityByUsername(String name) throws NoSuchUserException;

    User getEntityByEmail(String name) throws NoSuchUserException;

}

package model.service;

import model.dao.DaoFactory;
import model.dao.RoomDao;
import model.dao.exception.DaoException;
import model.entity.Room;

public class RoomService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Room getById(int id) throws DaoException {
        try (RoomDao dao = daoFactory.createRoomDao()) {
            return dao.getEntityById(id);
        }
    }
}

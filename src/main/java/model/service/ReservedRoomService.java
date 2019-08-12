package model.service;

import model.dao.DaoFactory;
import model.dao.ReservedRoomDao;
import model.dao.exception.DaoException;
import model.entity.Bill;
import model.entity.ReservedRoom;

import java.util.ArrayList;
import java.util.List;

public class ReservedRoomService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createReservedRoom(ReservedRoom reservedRoom) throws DaoException {
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            dao.create(reservedRoom);
        }
    }
    public ReservedRoom getLastEntity() throws DaoException {
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            return dao.getLastEntity();
        }
    }
    public List<ReservedRoom> getAll() throws DaoException {
        List<ReservedRoom> reservedRooms = new ArrayList<>();
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            reservedRooms = dao.getAll();
        }
        return reservedRooms;
    }
    public void createReservedRoomAndBill(ReservedRoom reservedRoom, Bill bill) throws DaoException {
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            dao.createReservationAndBill(reservedRoom, bill);
        }
    }

    public ReservedRoom getByApplicationId(int id) throws DaoException {
        ReservedRoom reservedRoom =null;
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            reservedRoom = dao.getByApplicationId(id);
        }
        return reservedRoom;
    }

    public void deleteReservAndBill(int reservedRoomId, int billId) throws DaoException {
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            dao.deleteReservAndBill(reservedRoomId, billId);
        }
    }

    public ReservedRoom getById(int id) throws DaoException {
        ReservedRoom reservedRoom = null;
        try (ReservedRoomDao dao = daoFactory.createReservedRoomDao()) {
            reservedRoom = dao.getEntityById(id);
        }
        return reservedRoom;
    }
}

package model.dao;

import model.dao.exception.DaoException;
import model.entity.Bill;
import model.entity.ReservedRoom;

import java.util.List;

public interface ReservedRoomDao extends GenericDao<ReservedRoom, Integer>{
    ReservedRoom getLastEntity() throws DaoException;
    void createReservationAndBill(ReservedRoom reservedRoom, Bill bill) throws DaoException;
    ReservedRoom getByApplicationId(int userId) throws DaoException;

    void deleteReservAndBill(int reservedRoomId, int billId) throws DaoException;
}

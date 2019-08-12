package model.service;

import model.dao.DaoFactory;
import model.dao.HotelNumberDao;
import model.dao.RoomDao;
import model.dao.exception.DaoException;
import model.entity.HotelNumber;
import model.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelNumberService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<HotelNumber> getHotelNumbers() {
        List<HotelNumber> numbers = new ArrayList<>();
        try (HotelNumberDao dao = daoFactory.createHotelNumberDao()) {
            numbers = dao.getAll();
        }
        return numbers;
    }

    public HotelNumber getById(int id) throws DaoException {
        try (HotelNumberDao dao = daoFactory.createHotelNumberDao()) {
            return dao.getEntityById(id);
        }
    }
}

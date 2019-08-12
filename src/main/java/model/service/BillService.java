package model.service;

import model.dao.BillDao;
import model.dao.DaoFactory;
import model.dao.exception.DaoException;
import model.entity.Bill;

public class BillService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public Bill getEntityById(int id) throws DaoException {
        Bill bill =null;
        try (BillDao dao = daoFactory.createBillDao()) {
            bill = dao.getEntityById(id);
        }
        return bill;
    }

    public void updateStatus(Bill bill) throws DaoException {
        try (BillDao dao = daoFactory.createBillDao()) {
            dao.update(bill);
        }
    }
}

package model.dao.impl;

import model.dao.*;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static model.util.LogMsg.CONNECTION_NOT_RECEIVED;

public class JDBCDaoFactory extends DaoFactory {
    private static Logger log = LogGen.getInstance();

    private DataSource dataSource = ConnectionPool.getDataSource();
    @Override
    public UserDao createUserDao() {
        AbstractDao dao = new JDBCUserDao(getConnection());
        dao.setLocale(super.getDaoLocale());
        return (UserDao)dao;
    }

    @Override
    public BillDao createBillDao() {
        AbstractDao dao = new JDBCBillDao(getConnection());
        dao.setLocale(super.getDaoLocale());
        return (BillDao) dao;
    }

    @Override
    public HotelNumberDao createHotelNumberDao() {
        AbstractDao dao = new JDBCHotelNumberDao(getConnection());
        dao.setLocale(super.getDaoLocale());
        return (HotelNumberDao) dao;
    }

    @Override
    public ReservedRoomDao createReservedRoomDao() {
        AbstractDao dao = new JDBCReservedRoomDao(getConnection());
        dao.setLocale(super.getDaoLocale());
        return (ReservedRoomDao) dao;
    }

    @Override
    public RoomDao createRoomDao() {
        AbstractDao dao = new JDBCRoomDao(getConnection());
        dao.setLocale(super.getDaoLocale());
        return (RoomDao) dao;
    }

    @Override
    public ApplicationDao createApplicationDao() {
        AbstractDao dao = new JDBCApplicationDao(getConnection());
        dao.setLocale(super.getDaoLocale());
        return (ApplicationDao) dao;
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(CONNECTION_NOT_RECEIVED);
            throw new RuntimeException(e);
        }
    }
}

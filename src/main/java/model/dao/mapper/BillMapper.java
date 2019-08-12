package model.dao.mapper;

import model.entity.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BillMapper implements ObjectMapper<Bill>{
    @Override
    public Bill extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        Bill bill = new Bill();
        bill.setId(resultSet.getInt(columnIndexes[0]));
        bill.setTotal(resultSet.getInt(columnIndexes[1]));
        bill.setStatus(resultSet.getBoolean(columnIndexes[2]));
        return bill;
    }

    @Override
    public Bill makeUnique(Map<Integer, Bill> cache, Bill entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

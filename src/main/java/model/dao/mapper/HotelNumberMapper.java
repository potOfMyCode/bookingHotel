package model.dao.mapper;

import model.entity.HotelNumber;
import model.entity.User;
import model.util.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class HotelNumberMapper implements ObjectMapper<HotelNumber>{
    @Override
    public HotelNumber extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        HotelNumber hotelNumber = new HotelNumber();
        hotelNumber.setId(resultSet.getInt(columnIndexes[0]));
        hotelNumber.setShortType(resultSet.getString(columnIndexes[1]));
        hotelNumber.setAmount(resultSet.getInt(columnIndexes[2]));
        hotelNumber.setPrice(resultSet.getInt(columnIndexes[3]));
        hotelNumber.setType(resultSet.getString(columnIndexes[4]));
        hotelNumber.setTypeUkr(resultSet.getString(columnIndexes[5]));
        return hotelNumber;
    }

    @Override
    public HotelNumber makeUnique(Map<Integer, HotelNumber> cache, HotelNumber entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

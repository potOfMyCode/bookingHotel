package model.dao.mapper;

import model.entity.ReservedRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReservedRoomMapper implements ObjectMapper<ReservedRoom>{
    @Override
    public ReservedRoom extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        ReservedRoom reservedRoom = new ReservedRoom();
        reservedRoom.setId(resultSet.getInt(columnIndexes[0]));
        reservedRoom.setUserId(resultSet.getInt(columnIndexes[1]));
        reservedRoom.setRoomId(resultSet.getInt(columnIndexes[2]));
        reservedRoom.setBillId(resultSet.getInt(columnIndexes[3]));
        reservedRoom.setApplicationId(resultSet.getInt(columnIndexes[4]));
        reservedRoom.setDate_from(resultSet.getDate(columnIndexes[5]));
        reservedRoom.setDate_before(resultSet.getDate(columnIndexes[6]));
        reservedRoom.setDateOfBooking(resultSet.getTimestamp(columnIndexes[7]));
        return reservedRoom;
    }

    @Override
    public ReservedRoom makeUnique(Map<Integer, ReservedRoom> cache, ReservedRoom entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

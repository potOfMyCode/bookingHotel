package model.dao.mapper;

import model.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoomMapper implements ObjectMapper<Room>{
    @Override
    public Room extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt(columnIndexes[0]));
        room.setHotelNumberId(resultSet.getInt(columnIndexes[1]));
        room.setShortType(resultSet.getString(columnIndexes[2]));
        return room;
    }

    @Override
    public Room makeUnique(Map<Integer, Room> cache, Room entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

package model.dao.mapper;

import model.entity.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ApplicationMapper implements ObjectMapper<Application>{
    @Override
    public Application extractFromResultSet(ResultSet resultSet, int... columnIndexes) throws SQLException {
        Application application = new Application();
        application.setId(resultSet.getInt(columnIndexes[0]));
        application.setUserId(resultSet.getInt(columnIndexes[1]));
        application.setName(resultSet.getString(columnIndexes[2]));
        application.setSurname(resultSet.getString(columnIndexes[3]));
        application.setAmountPeople(resultSet.getInt(columnIndexes[4]));
        application.setAmounChildren(resultSet.getInt(columnIndexes[5]));
        application.setTypeOfNumber(resultSet.getString(columnIndexes[6]));
        application.setNote(resultSet.getString(columnIndexes[7]));
        application.setDateFrom(resultSet.getDate(columnIndexes[8]));
        application.setDateTo(resultSet.getDate(columnIndexes[9]));
        application.setDateOfBooking(resultSet.getTimestamp(columnIndexes[10]));
        application.setStatus(resultSet.getBoolean(columnIndexes[11]));
        return application;
    }

    @Override
    public Application makeUnique(Map<Integer, Application> cache, Application entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

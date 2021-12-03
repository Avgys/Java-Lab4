package by.bsuir.task.domain.mapper;

import by.bsuir.task.domain.entity.HotelRoom;
import by.bsuir.task.exception.DataSourceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements Mapper<HotelRoom> {

    private static final String ID = "id";
    private static final String ROOM_NUMBER = "room_number";
    private static final String OCCUPIED = "occupied";

    @Override
    public HotelRoom build(ResultSet resultSet) throws DataSourceException {
        try {
            Integer id = resultSet.getInt(ID);
            String roomNumber = resultSet.getString(ROOM_NUMBER);
            Boolean occupied = resultSet.getBoolean(OCCUPIED);

            return new HotelRoom(id, roomNumber, occupied);
        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }
}

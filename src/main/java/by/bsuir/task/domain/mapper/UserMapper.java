package by.bsuir.task.domain.mapper;

import by.bsuir.task.domain.entity.Role;
import by.bsuir.task.domain.entity.User;
import by.bsuir.task.exception.DataSourceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    @Override
    public User build(ResultSet resultSet) throws DataSourceException {
        try {
            Integer id = resultSet.getInt(ID);
            String login = resultSet.getString(USERNAME);
            String password = resultSet.getString(PASSWORD);
            Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());

            return new User(id, login, password, role);
        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }
}

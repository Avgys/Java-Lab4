package by.bsuir.task.domain.mapper;

import by.bsuir.task.exception.DataSourceException;

import java.sql.ResultSet;

public interface Mapper<T> {

    T build(ResultSet resultSet) throws DataSourceException;
}

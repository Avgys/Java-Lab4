package by.bsuir.task.repository;

import by.bsuir.task.domain.mapper.Mapper;
import by.bsuir.task.domain.entity.BaseEntity;
import by.bsuir.task.exception.DataSourceException;
import by.bsuir.task.repository.queryBuilder.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends BaseEntity> implements Repository<T> {

    private final Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    public List<T> executeQuery(String sql, Mapper<T> mapper, List<Object> params) throws DataSourceException {
        List<T> objects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            QueryBuilder.prepare(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T item = mapper.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException ex) {
            throw new DataSourceException(ex.getMessage());
        }
        return objects;
    }

    public Optional<T> executeQueryForSingleResult(String query, Mapper<T> mapper, List<Object> params) throws DataSourceException {
        List<T> items = executeQuery(query, mapper, params);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    public void save(T item) throws DataSourceException {
        try {
            String sql;
            if (item.getId() != null) {
                sql = QueryBuilder.makeUpdateQuery(getFields(item), getTableName());
            } else {
                sql = QueryBuilder.makeInsertQuery(getFields(item), getTableName());
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            QueryBuilder.prepare(preparedStatement, getFields(item));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataSourceException(ex.getMessage());
        }
    }
}

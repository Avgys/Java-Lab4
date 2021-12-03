package by.bsuir.task.service;

import by.bsuir.task.domain.entity.User;
import by.bsuir.task.exception.DataSourceException;
import by.bsuir.task.exception.ServiceException;
import by.bsuir.task.repository.creator.RepositoryCreator;
import by.bsuir.task.repository.impl.UserRepository;
import by.bsuir.task.specification.user.FindByUsername;
import by.bsuir.task.specification.user.FindByUsernameAndPassword;

import java.util.Optional;

public class UserService {

    public Optional<User> findByUsernameAndPassword(String username, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByUsernameAndPassword(username, password));

        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<User> findByUsername(String username) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByUsername(username));

        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public void signUp(Integer id, String username, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            User user = new User(id, username, password);
            userRepository.save(user);

        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}

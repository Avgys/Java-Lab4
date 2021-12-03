package by.bsuir.task.service;

import by.bsuir.task.domain.entity.HotelRoom;
import by.bsuir.task.exception.DataSourceException;
import by.bsuir.task.exception.ServiceException;
import by.bsuir.task.repository.creator.RepositoryCreator;
import by.bsuir.task.repository.impl.RoomRepository;
import by.bsuir.task.specification.common.FindById;
import by.bsuir.task.specification.room.FindAll;
import by.bsuir.task.specification.room.FindFree;

import java.util.List;
import java.util.Optional;

public class HotelRoomService {

    public List<HotelRoom> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();
            return roomRepository.queryAll(new FindAll());

        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<HotelRoom> findFree() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();
            return roomRepository.queryAll(new FindFree());

        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public void save(Integer id, String roomNumber, Boolean occupied) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();
            HotelRoom hotelRoom = new HotelRoom(id, roomNumber, occupied);
            roomRepository.save(hotelRoom);

        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public void changeStatus(Integer id, Boolean occupied) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();
            Optional<HotelRoom> room = roomRepository.query(new FindById(id));
            if (room.isPresent()) {
                room.get().setOccupied(occupied);
                roomRepository.save(room.get());
            } else {
                throw new ServiceException(String.format("Room with id=%s not found.", id));
            }
        } catch (DataSourceException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}

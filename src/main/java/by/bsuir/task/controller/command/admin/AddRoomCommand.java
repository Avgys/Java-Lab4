package by.bsuir.task.controller.command.admin;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandResult;
import by.bsuir.task.domain.entity.HotelRoom;
import by.bsuir.task.exception.ServiceException;
import by.bsuir.task.service.HotelRoomService;
import by.bsuir.task.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRoomCommand implements Command {

    private static final String MAIN_PAGE = "controller?command=showRooms";
    private static final String ROOM_NUMBER = "roomNumber";
    private static final String ROOM_LIST = "roomList";
    private static final String ADDING_ROOM = "added";
    private static final String MESSAGE = "&message=";
    private static final String ERROR_MESSAGE = "invalidRoom";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String roomNumber = request.getParameter(ROOM_NUMBER);

        Validation validation = new Validation();
        Map<String, String> values = new HashMap<>();
        values.put(ROOM_NUMBER, roomNumber);
        if (!validation.isValid(values)) {
            return CommandResult.redirect(MAIN_PAGE + MESSAGE + ERROR_MESSAGE);
        }

        HotelRoomService roomService = new HotelRoomService();
        roomService.save(null, roomNumber, false);

        List<HotelRoom> hotelRoomList = roomService.findAll();
        request.setAttribute(ROOM_LIST, hotelRoomList);

        return CommandResult.redirect(MAIN_PAGE + MESSAGE + ADDING_ROOM);
    }
}

package by.bsuir.task.controller.command.admin;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandResult;
import by.bsuir.task.domain.entity.HotelRoom;
import by.bsuir.task.exception.ServiceException;
import by.bsuir.task.service.HotelRoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeoccupyRoomCommand implements Command {
    private static final String MAIN_PAGE = "controller?command=showRooms";
    private static final String ROOM_LIST = "roomList";
    private static final String ROOM_ID = "roomId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String roomId = request.getParameter(ROOM_ID);

        HotelRoomService roomService = new HotelRoomService();
        roomService.changeStatus(Integer.valueOf(roomId), false);

        List<HotelRoom> hotelRoomList = roomService.findAll();
        request.setAttribute(ROOM_LIST, hotelRoomList);

        return CommandResult.redirect(MAIN_PAGE);
    }
}

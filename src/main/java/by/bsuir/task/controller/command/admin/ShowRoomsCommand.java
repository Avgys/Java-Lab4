package by.bsuir.task.controller.command.admin;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandResult;
import by.bsuir.task.domain.entity.HotelRoom;
import by.bsuir.task.exception.ServiceException;
import by.bsuir.task.service.HotelRoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRoomsCommand implements Command {
    private static final String ROOMS_PAGE = "/WEB-INF/pages/admin/rooms.jsp";
    private static final String ROOM_LIST = "roomList";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HotelRoomService roomService = new HotelRoomService();
        List<HotelRoom> fullHotelRoomList = roomService.findAll();
        request.setAttribute(ROOM_LIST, fullHotelRoomList);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }

        return CommandResult.forward(ROOMS_PAGE);
    }
}

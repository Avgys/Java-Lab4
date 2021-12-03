package by.bsuir.task.controller.command.user;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandResult;
import by.bsuir.task.domain.entity.HotelRoom;
import by.bsuir.task.exception.ServiceException;
import by.bsuir.task.service.HotelRoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainPageCommand implements Command {

    private static final String MAIN_PAGE = "/WEB-INF/pages/makeOrder.jsp";
    private static final String ROOM_LIST = "roomList";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HotelRoomService roomService = new HotelRoomService();
        List<HotelRoom> freeHotelRoomList = roomService.findFree();
        request.setAttribute(ROOM_LIST, freeHotelRoomList);
        return CommandResult.forward(MAIN_PAGE);
    }
}

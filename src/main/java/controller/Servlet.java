package controller;

import controller.command.*;
import model.entity.Bill;
import model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private final String CUR_REQ_URL = "curReqURL";

    private Map<String, Command> commandsMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commandsMap.put("/", new HomeCommand());
        commandsMap.put("/go_registration", new GoRegisterCommand());
        commandsMap.put("/login", new LoginCommand(new UserService()));
        commandsMap.put("/logout", new LogoutCommand());
        commandsMap.put("/go_login", new GoLoginCommand());
        commandsMap.put("/numbers", new ShowNumbersCommand(new HotelNumberService()));
        commandsMap.put("/rooms", new ShowRoomsCommand(new HotelNumberService()));
        commandsMap.put("/search", new SearchReservedRoomsCommand(new ReservedRoomService(), new BillService()));
        commandsMap.put("/reserved_rooms", new ShowReservedRoomsCommand(new ReservedRoomService(), new BillService()));
        commandsMap.put("/go_double", new GoDoubleCommand());
        commandsMap.put("/go_single", new GoSingleCommand());
        commandsMap.put("/go_triple", new GoTripleCommand());
        commandsMap.put("/go_luxe", new GoLuxeCommand());
        commandsMap.put("/go_booking", new GoBookingCommand());
        commandsMap.put("/booking", new BookingCommand(new ApplicationService()));
        commandsMap.put("/registration", new RegisterCommand(new UserService()));
        commandsMap.put("/operation_success", new OperationSuccessCommand());
        commandsMap.put("/operation_fail", new OperationFailCommand());
        commandsMap.put("/go_orders", new GoOrdersCommand(new ApplicationService()));
        commandsMap.put("/go_applications", new GoApplicationsCommand(new ApplicationService()));
        commandsMap.put("/confirm_application", new ConfirmApplicationCommand(new ApplicationService()));
        commandsMap.put("/assign_room", new AssignRoomCommand(new ApplicationService(), new ReservedRoomService(), new RoomService(), new HotelNumberService()));
        commandsMap.put("/go_pay", new GoPayCommand(new ApplicationService(), new ReservedRoomService(), new BillService()));
        commandsMap.put("/payByCard", new PayByCardCommand(new BillService()));
        commandsMap.put("/refuse_application", new RefuseApplicationCommand(new ApplicationService()));
        commandsMap.put("/remove_reservation", new RemoveReservationCommand(new ReservedRoomService(), new BillService()));
        commandsMap.put("/remove_application", new RemoveApplicationCommand(new ApplicationService()));

//        commandsMap.put("/logout", new Logout());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getPathInfo();
        req.setAttribute(CUR_REQ_URL, req.getRequestURL());
        Command command = commandsMap.get(path);
        String page = command.execute(req, resp);

        if (page.contains("redirect")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else if (page.contains("forward")) {
            req.getRequestDispatcher(page.replace("forward:", "")).forward(req, resp);
        }
    }
}
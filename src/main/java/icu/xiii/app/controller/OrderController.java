package icu.xiii.app.controller;

import icu.xiii.app.entity.Order;
import icu.xiii.app.service.order.OrderService;
import icu.xiii.app.service.order.OrderServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderController extends HttpServlet {

    private OrderService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new OrderServiceImpl();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action.toLowerCase()) {
                case "/new" -> renderNewForm(req, resp);
                case "/insert" -> create(req, resp);
                case "/delete" -> delete(req, resp);
                case "/edit" -> renderEditForm(req, resp);
                case "/update" -> update(req, resp);
                default -> read(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void read(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Order> orders = service.getAll();
        req.setAttribute("ordersList", orders);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("webapp/templates/order-list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void renderEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void renderNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}

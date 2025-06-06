package icu.xiii.app.controller;

import icu.xiii.app.entity.Order;
import icu.xiii.app.entity.Product;
import icu.xiii.app.service.order.OrderService;
import icu.xiii.app.service.order.OrderServiceImpl;
import icu.xiii.app.service.product.ProductService;
import icu.xiii.app.service.product.ProductServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderController extends HttpServlet {

    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        orderService = new OrderServiceImpl();
        productService = new ProductServiceImpl();
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
        List<Order> orders = orderService.getAll();
        req.setAttribute("ordersList", orders);
        RequestDispatcher dispatcher = req.getRequestDispatcher("templates/order-list.jsp");
        dispatcher.forward(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Order order = new Order();
        order.setDate(LocalDate.parse(req.getParameter("date")));
        for (String productId : req.getParameterValues("orderProducts")) {
            Product product = productService.getById(Long.parseLong(productId));
            if (product != null) {
                order.getProducts().add(product);
                product.getOrders().add(order);
            }
        }
        orderService.create(order);
        resp.sendRedirect("list");
    }

    private void renderEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("id"));
        Order order = orderService.getById(orderId);
        List<Product> productsList = productService.getAll();
        req.setAttribute("productsList", productsList);
        req.setAttribute("order", order);
        RequestDispatcher dispatcher = req.getRequestDispatcher("templates/order-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void renderNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productsList = productService.getAll();
        req.setAttribute("productsList", productsList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("templates/order-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}

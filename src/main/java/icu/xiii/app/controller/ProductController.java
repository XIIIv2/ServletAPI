package icu.xiii.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.xiii.app.entity.Product;
import icu.xiii.app.service.order.OrderService;
import icu.xiii.app.service.order.OrderServiceImpl;
import icu.xiii.app.service.product.ProductService;
import icu.xiii.app.service.product.ProductServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// @WebServlet(name = "Products", urlPatterns = "/api/v1/products/*")
public class ProductController extends HttpServlet {

    private ProductService productService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = new ProductServiceImpl();
        objectMapper = new ObjectMapper();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //String action = req.getServletPath();
        String action = req.getPathInfo();
        try {
            switch (action.toLowerCase()) {
                case "/list.json" -> jsonList(req, resp);
                /*case "/new" -> renderNewForm(req, resp);
                case "/insert" -> create(req, resp);
                case "/delete" -> delete(req, resp);
                case "/edit" -> renderEditForm(req, resp);
                case "/update" -> update(req, resp);*/
                //default -> jsonList(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void jsonList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = productService.getAll();
        String json = objectMapper.writeValueAsString(products);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setContentLength(json.length());
        try (ServletOutputStream out = resp.getOutputStream()) {
            out.println(json);
            out.flush();
        }
    }
}

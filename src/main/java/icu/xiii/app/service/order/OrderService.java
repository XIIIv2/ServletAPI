package icu.xiii.app.service.order;

import icu.xiii.app.entity.Order;
import icu.xiii.app.service.BaseService;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    void create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    void update(Order order);

    void deleteById(Long id);
}

package icu.xiii.app.service.order;

import icu.xiii.app.entity.Order;
import icu.xiii.app.repository.order.OrderRepository;
import icu.xiii.app.repository.order.OrderRepositoryImpl;

import java.util.Collections;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository = new OrderRepositoryImpl();

    @Override
    public void create(Order order) {
        repository.create(order);
    }

    public List<Order> getAll() {
        return repository.getAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Order getById(Long id) {
        return repository.getById(id)
                .orElse(null);
    }

    @Override
    public void update(Order order) {
        repository.update(order);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

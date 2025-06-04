package icu.xiii.app.repository.order;

import icu.xiii.app.dto.order.OrderDtoRequest;
import icu.xiii.app.entity.Order;
import icu.xiii.app.repository.BaseRepository;

import java.util.Optional;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    void create(Order order);

    Optional<List<Order>> getAll();

    Optional<Order> getById(Long id);

    void update(Order order);

    boolean deleteById(Long id);

    Optional<Order> getLastEntity();
}

package icu.xiii.app.service.product;

import icu.xiii.app.entity.Product;
import icu.xiii.app.service.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    void create(Product product);

    List<Product> getAll();

    Product getById(Long id);

    void update(Product product);

    void deleteById(Long id);
}

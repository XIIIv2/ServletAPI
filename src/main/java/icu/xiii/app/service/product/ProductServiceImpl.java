package icu.xiii.app.service.product;

import icu.xiii.app.entity.Product;
import icu.xiii.app.repository.product.ProductRepository;
import icu.xiii.app.repository.product.ProductRepositoryImpl;

import java.util.Collections;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository = new ProductRepositoryImpl();

    @Override
    public void create(Product product) {
        repository.create(product);
    }

    public List<Product> getAll() {
        return repository.getAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Product getById(Long id) {
        return repository.getById(id)
                .orElse(null);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

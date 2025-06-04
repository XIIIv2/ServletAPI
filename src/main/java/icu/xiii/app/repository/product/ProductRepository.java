package icu.xiii.app.repository.product;

import icu.xiii.app.entity.Product;
import icu.xiii.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product> {

    void create(Product product);

    Optional<List<Product>> getAll();

    Optional<Product> getById(Long id);

    void update(Product product);

    boolean deleteById(Long id);

    Optional<Product> getLastEntity();
}

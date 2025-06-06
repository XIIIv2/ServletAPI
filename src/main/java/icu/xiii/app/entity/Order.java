package icu.xiii.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import icu.xiii.app.dto.order.OrderDtoRequest;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<Product> products = new HashSet<>();

    public Order() {

    }

    public Order(OrderDtoRequest request) {
        this.id = request.id();
        this.date = request.date();
        request.products().forEach(productId -> {
            Product product = new Product();
            product.setId(productId);
            this.products.add(product);
        });
    }

    public Order(Long id, OrderDtoRequest request) {
        this(request);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCost() {
        return this.products
                .stream()
                .mapToDouble(Product::getCost)
                .sum();
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        /*products.forEach(p -> {
            p.getOrders().add(this);
        });*/
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id) && date.equals(order.date) && products.equals(order.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + date.hashCode();
        result = 31 * result + products.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}

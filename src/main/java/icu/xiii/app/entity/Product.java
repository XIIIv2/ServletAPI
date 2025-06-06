package icu.xiii.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import icu.xiii.app.dto.product.ProductDtoRequest;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private double cost;

    @JsonBackReference
    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Product() {

    }

    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Product(ProductDtoRequest request) {
        this.id = request.id();
        this.name = request.name();
        this.cost = request.cost();
    }

    public Product(Long id, ProductDtoRequest request) {
        this(request);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Double.compare(cost, product.cost) == 0 && Objects.equals(id, product.id) && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + Double.hashCode(cost);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}

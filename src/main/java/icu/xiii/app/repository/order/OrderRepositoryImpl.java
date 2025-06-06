package icu.xiii.app.repository.order;

import icu.xiii.app.config.HibernateUtil;
import icu.xiii.app.dto.order.OrderDtoRequest;
import icu.xiii.app.entity.Order;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public void create(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<List<Order>> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Order> orders = session
                    .createQuery("FROM Order", Order.class)
                    .getResultList();
            transaction.commit();
            return  Optional.of(orders);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = session.find(Order.class, id);
            transaction.commit();
            return Optional.ofNullable(order);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Optional<Order> order = getById(id);
            if (order.isPresent()) {
                session.remove(order.get());
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public Optional<Order> getLastEntity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<Order> query = session
                    .createQuery("FROM Order ORDER BY id DESC", Order.class);
            query.setMaxResults(1);
            Order order = query.getSingleResult();
            transaction.commit();
            return Optional.of(order);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

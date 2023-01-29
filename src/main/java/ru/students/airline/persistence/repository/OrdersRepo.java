package ru.students.airline.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.students.airline.persistence.entity.Order;
import ru.students.airline.persistence.entity.User;

import java.util.List;

public interface OrdersRepo extends CrudRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}

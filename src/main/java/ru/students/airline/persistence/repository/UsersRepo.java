package ru.students.airline.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.students.airline.persistence.entity.User;

import java.util.Optional;

public interface UsersRepo extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}

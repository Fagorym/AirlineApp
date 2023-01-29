package ru.students.airline.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.students.airline.persistence.entity.Passenger;

public interface PassengerRepo extends CrudRepository<Passenger, Long> {
}

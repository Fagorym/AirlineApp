package ru.students.airline.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.students.airline.persistence.entity.Ticket;

import java.util.Optional;

public interface TicketsRepo extends CrudRepository<Ticket, Integer> {
    @Query(value = "INSERT INTO tickets  (flight_date_id, username, reserved_seats)" +
            " VALUES (?1, ?2, ?3)", nativeQuery = true)
    Optional<Ticket> saveTicket(Integer flightDateId, String username, Integer reservedSeats);
}

package ru.students.airline.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.students.airline.persistence.entity.FlightDate;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface FlightDatesRepo extends CrudRepository<FlightDate, String> {
    @Query(value = "SELECT fd.* from flight_dates fd " +
            "WHERE fd.flight_number = ?1 AND fd.date = ?2" ,nativeQuery = true)
    Optional<FlightDate> findFlightDate(String flightNumber, Date date);
}

package ru.students.airline.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.students.airline.persistence.entity.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightsRepo extends JpaRepository<Flight, Integer> {
    @Query(value = "SELECT DISTINCT f.* FROM flights f JOIN flight_dates fd ON f.number = fd.flight_number " +
            "WHERE fd.date = ?1 AND f.departure_city = ?2 AND f.arrival_city = ?3 AND fd.reserved_seats + ?4 <= f.seats " +
            "ORDER BY f.cost",
    nativeQuery = true)
    List<Flight> findSomeFlights(LocalDate date, String dCity, String aCity, Integer seats);

    Optional<Flight> findFlightByNumber(String number);
}

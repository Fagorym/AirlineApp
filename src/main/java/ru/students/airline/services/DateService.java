package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.students.airline.persistence.repository.FlightDatesRepo;

@Service
@RequiredArgsConstructor
public class DateService {
    private final FlightDatesRepo flightDatesRepo;


}

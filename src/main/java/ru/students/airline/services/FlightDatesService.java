package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.students.airline.dto.TicketDto;
import ru.students.airline.persistence.entity.Flight;
import ru.students.airline.persistence.entity.FlightDate;
import ru.students.airline.persistence.repository.FlightDatesRepo;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.*;

@RequiredArgsConstructor
@Service
public class FlightDatesService {
    private final FlightDatesRepo flightDatesRepo;

    private final static Map<String, DayOfWeek> DAYS = Map.of(
            "Пн", MONDAY,
            "Вт", TUESDAY,
            "Ср", WEDNESDAY,
            "Чт", THURSDAY,
            "Пт", FRIDAY,
            "Сб", SATURDAY,
            "Вс", SUNDAY
    );

    @Transactional
    public FlightDate reserveSeats(TicketDto dto) {
        FlightDate flightDate = flightDatesRepo
                .findFlightDate(dto.getFlightNumber(), Date.valueOf(dto.getDate()))
                .orElseThrow(EntityNotFoundException::new);
        flightDate.setReservedSeats(flightDate.getReservedSeats() + dto.getSeats());
        return flightDate;
    }

    public List<FlightDate> generateFlightDates(List<Flight> flights) {
        List<FlightDate> flightDates = new ArrayList<>();
        for (Flight flight : flights) {
            List<DayOfWeek> days =  getDaysList(flight.getWeekdays());
            LocalDate nextDay = LocalDate.now();
            for (int i = 0 ; i < 30; i++) {
                if (days.contains(nextDay.getDayOfWeek()))
                    flightDates.add(new FlightDate(nextDay ,flight));
                nextDay = nextDay.plusDays(1);
            }
        }
        flightDatesRepo.saveAll(flightDates);
        return flightDates;
    }

    private List<DayOfWeek> getDaysList(String weekdays) {
        return Arrays.stream(weekdays.split(" "))
                .map(DAYS::get)
                .collect(Collectors.toList());
    }
}

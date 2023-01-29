package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.students.airline.dto.FlightDto;
import ru.students.airline.parser.FileToFlightParser;
import ru.students.airline.persistence.entity.Flight;
import ru.students.airline.persistence.entity.FlightDate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FlightService flightService;
    private final FlightDatesService flightDatesService;
    private final FileToFlightParser parser;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void uploadFile(MultipartFile file) {
        List<FlightDto> dtos = parser.parseCsv(file);
        List<Flight> flights = flightService.saveAll(dtos);
        List<FlightDate> flightDates = flightDatesService.generateFlightDates(flights);
        System.out.println(flightDates);
    }

}


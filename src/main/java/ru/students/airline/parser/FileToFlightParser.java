package ru.students.airline.parser;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import ru.students.airline.dto.FlightDto;
import ru.students.airline.exceptions.FlightParseException;

@Component
public class FileToFlightParser {

    public List<FlightDto> parseCsv(MultipartFile file) {
        try {
            return parseCsvImpl(file);
        } catch (IOException ignore) {
            throw new FlightParseException("Ошибка при прочтении файла");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new FlightParseException("Ошибка при конвертации");
        }
    }

    public List<FlightDto> parseCsvImpl(MultipartFile file) throws IOException, DateTimeParseException, NumberFormatException {
        List<FlightDto> flights = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                String[] row = reader.readLine().split(",");
                if (row.length != 8) throw new FlightParseException("Wrong file format");

                FlightDto dto = new FlightDto(row[0], row[1], row[2],
                        LocalTime.parse(row[3]),
                        LocalTime.parse(row[4]),
                        Integer.parseInt(row[5]),
                        Integer.parseInt(row[6]),
                        row[7]);
                flights.add(dto);
            }
        }
        return flights;
    }
}

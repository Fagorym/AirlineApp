package ru.students.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    String number;

    private String departureCity;

    private String arrivalCity;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private Integer cost;

    private Integer seats;

    private String weekdays;
}

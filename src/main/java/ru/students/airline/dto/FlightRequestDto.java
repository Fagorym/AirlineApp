package ru.students.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequestDto {

    private String date;

    private String arrivalCity;

    private String departureCity;

    private int neededSeats;

}

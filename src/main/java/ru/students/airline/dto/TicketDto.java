package ru.students.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    String flightNumber;
    String date;
    Integer seats;
}

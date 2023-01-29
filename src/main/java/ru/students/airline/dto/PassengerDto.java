package ru.students.airline.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
public class PassengerDto {
    String name;
    String surname;
    String passport;
    String birthDate;
    Boolean needLuggage;
}

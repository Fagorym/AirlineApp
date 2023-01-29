package ru.students.airline.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class OrderDto {
    Long id;
    FlightDto flight;
    String flightDate;
    String username;
    List<PassengerDto> passenger;
    Boolean needRegistration;
    Boolean needInsurance;

    public long luggageCount() {
        return passenger.stream().filter((x) -> x.needLuggage).count();
    }

    public int insuranceCount() {
        if (needInsurance) {
            return passenger.size();
        } else {
            return 0;
        }
    }

    public int registrationCount() {
        if (needRegistration) {
            return passenger.size();
        } else {
            return 0;
        }
    }
}

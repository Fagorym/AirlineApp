package ru.students.airline.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    private String number;

    private String departureCity;

    private String arrivalCity;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private Double cost;

    private Integer seats;

    private String weekdays;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<FlightDate> dates;

}

package ru.students.airline.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_dates")
public class FlightDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private Integer reservedSeats = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_number", referencedColumnName = "number")
    private Flight flight;

    @OneToMany(mappedBy = "flightDate", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public FlightDate(LocalDate date, Flight flight) {
        this.date = date;
        this.flight = flight;
    }
}

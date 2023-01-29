package ru.students.airline.persistence.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_number", referencedColumnName = "number")
    private Flight flight;
    private Boolean needRegistration;
    private Boolean needInsurance;

    private LocalDate flightDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Passenger> passengerList;

    public Order(User user, Boolean needInsurance, Boolean needRegistration, Flight flight, LocalDate date) {
        this.user = user;
        this.needInsurance = needInsurance;
        this.needRegistration = needRegistration;
        this.flight = flight;
        this.flightDate = date;
    }
}

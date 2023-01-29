package ru.students.airline.persistence.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String passport;
    private Boolean needLuggage;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;


}

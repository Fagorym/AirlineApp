package ru.students.airline.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import ru.students.airline.dto.PassengerDto;
import ru.students.airline.persistence.entity.Order;
import ru.students.airline.persistence.entity.Passenger;
import ru.students.airline.persistence.repository.PassengerRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public void saveAll(List<PassengerDto> passengerDtoList, Order order) {
        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDto dto : passengerDtoList) {
            var passenger = modelMapper.map(dto, Passenger.class);
            passenger.setBirthDate(LocalDate.parse(dto.getBirthDate()));
            passenger.setOrder(order);
            passengers.add(passenger);
        }
        passengerRepo.saveAll(passengers);
    }

}

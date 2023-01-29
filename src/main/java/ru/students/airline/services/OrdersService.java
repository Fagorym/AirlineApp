package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.students.airline.dto.OrderDto;
import ru.students.airline.persistence.entity.Flight;
import ru.students.airline.persistence.entity.Order;
import ru.students.airline.persistence.entity.User;
import ru.students.airline.persistence.repository.FlightsRepo;
import ru.students.airline.persistence.repository.OrdersRepo;
import ru.students.airline.persistence.repository.UsersRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {
    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    PassengerService passengerService;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FlightsRepo flightsRepo;

    public void createOrder(OrderDto orderDto, String flightNumber) {
        User owner = usersRepo.findByUsername(orderDto.getUsername()).get();
        Flight flight = flightsRepo.findFlightByNumber(flightNumber).get();
        LocalDate date = LocalDate.parse(orderDto.getFlightDate());
        Order order = new Order(owner, orderDto.getNeedInsurance(), orderDto.getNeedRegistration(), flight, date);
        ordersRepo.save(order);
        passengerService.saveAll(orderDto.getPassenger(), order);
    }

    public List<OrderDto> getAllByUser(String username) {
        User user = usersRepo.findByUsername(username).get();
        List<Order> orders = ordersRepo.findAllByUser(user);
        return orders.stream().map((order -> modelMapper.map(order, OrderDto.class))).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id){
        Order order = ordersRepo.findById(id).get();
        return modelMapper.map(order, OrderDto.class);
    }
}

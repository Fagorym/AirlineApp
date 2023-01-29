package ru.students.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.students.airline.dto.*;
import ru.students.airline.security.CustomAuthProvider;
import ru.students.airline.services.FlightService;
import ru.students.airline.services.OrdersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FlightService flightService;
    private final OrdersService ordersService;

    @GetMapping(value = "/")
    public ModelAndView userPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user");
        modelAndView.addObject("flightSelection", new FlightRequestDto());
        modelAndView.getModel().put("username", CustomAuthProvider.getAuthenticatedUsername());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView filterFlights(@ModelAttribute("flightSelection") FlightRequestDto dto, ModelAndView modelAndView) {
        modelAndView.getModel().put("username", CustomAuthProvider.getAuthenticatedUsername());
        modelAndView.getModel().put("flights", flightService.getSomeFlights(dto));
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping("/book")
    public ModelAndView buyTicket(@ModelAttribute("flightDate") String flightDate, @ModelAttribute("neededSeats") Integer neededSeats, @ModelAttribute("flightNumber") String flightNumber, ModelAndView modelAndView) {
        TicketDto ticket = new TicketDto(flightNumber, flightDate, neededSeats);
        List<PassengerDto> passengerDtoList = new ArrayList<>();
        for (int i = 0; i < neededSeats; i++) {
            passengerDtoList.add(new PassengerDto());
        }
        Optional<FlightDto> flight = flightService.getAllFlights().stream().filter((x) -> (Objects.equals(x.getNumber(), flightNumber))).findFirst();
        modelAndView.getModel().put("flightInfo", flight.get());
        modelAndView.getModel().put("passengers", passengerDtoList);
        modelAndView.getModel().put("ticket", ticket);
        OrderDto order = new OrderDto();
        modelAndView.addObject("bookRequest", order);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @PostMapping(value = "/bookTicket")
    public ModelAndView bookTicket(@ModelAttribute("bookRequest") OrderDto orderDto,
                                   @ModelAttribute("flightNumber") String flightNumber,
                                   @ModelAttribute("flightDate") String flightDate,
                                   ModelAndView modelAndView) {
        orderDto.setUsername(CustomAuthProvider.getAuthenticatedUsername());
        orderDto.setFlightDate(flightDate);
        ordersService.createOrder(orderDto, flightNumber);
        return userPage(modelAndView);

    }

    @PostMapping(value = "bootTicket")
    public ModelAndView cancelBooking(ModelAndView modelAndView) {
        return userPage(modelAndView);
    }

    @GetMapping(value = "/cab")
    public ModelAndView returnCab(ModelAndView modelAndView) {
        modelAndView.setViewName("cabinet");
        String username = CustomAuthProvider.getAuthenticatedUsername();
        modelAndView.getModel().put(username, CustomAuthProvider.getAuthenticatedUsername());
        modelAndView.getModel().put("orders", ordersService.getAllByUser(username));
        return modelAndView;
    }

    @GetMapping(value="/orders/{id}")
    public ModelAndView getOrder(@PathVariable Long id, ModelAndView modelAndView){
        modelAndView.setViewName("order");
        OrderDto order = ordersService.getOrderById(id);
        modelAndView.getModel().put("order", order);
        return modelAndView;
    }
}

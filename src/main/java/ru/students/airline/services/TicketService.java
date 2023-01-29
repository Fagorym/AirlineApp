package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.students.airline.dto.TicketDto;
import ru.students.airline.persistence.entity.FlightDate;
import ru.students.airline.persistence.entity.Ticket;
import ru.students.airline.persistence.entity.User;
import ru.students.airline.persistence.repository.TicketsRepo;
import ru.students.airline.security.CustomAuthProvider;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketsRepo ticketsRepo;
    private final FlightDatesService flightDatesService;

    @Transactional
    public void buyTicket(TicketDto dto) {
        FlightDate flightDate = flightDatesService.reserveSeats(dto);
        String username = CustomAuthProvider.getAuthenticatedUsername();
        ticketsRepo.save(new Ticket(dto.getSeats(),flightDate, new User(username)));
    }
}

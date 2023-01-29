package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.students.airline.persistence.entity.User;
import ru.students.airline.persistence.repository.UsersRepo;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UsersRepo usersRepo;

    @Transactional
    public void register(User user) {
        user.setRole("ROLE_USER");
        usersRepo.save(user);
    }
}

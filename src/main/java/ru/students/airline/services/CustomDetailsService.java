package ru.students.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.students.airline.persistence.entity.User;
import ru.students.airline.persistence.repository.UsersRepo;
import ru.students.airline.security.CustomUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepo.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new CustomUserDetails(user.get());
    }
}

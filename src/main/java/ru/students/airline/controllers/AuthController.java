package ru.students.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.students.airline.security.CustomAuthProvider;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

}

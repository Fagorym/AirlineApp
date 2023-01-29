package ru.students.airline.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.students.airline.services.FileService;
import ru.students.airline.services.FlightService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final FlightService flightService;
    private final FileService fileService;

    @GetMapping(value = "/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.getModel().put("flights", flightService.getAllFlights());
        return modelAndView;
    }

    @PostMapping(value = "/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file,
                                 HttpServletResponse response) throws IOException {
        fileService.uploadFile(file);
        response.sendRedirect("/admin");
    }
}

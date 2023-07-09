package com.example.appointmentplatform.controller;

import com.example.appointmentplatform.model.Person;
import com.example.appointmentplatform.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

//if use @RestController then the Thyleaf page won't show, just return string
@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController {

    @Autowired
    BookingService bookingService;
    @GetMapping(value = "/")
    public String getAppointmentPage(){
        return "appointmentBook";
    }



    @GetMapping(value ="/waitingList")
    public String getAppointmentList(Model model) {
        ArrayList<Person> records = bookingService.getWaitingList();
        model.addAttribute("records", records);
        return "waitingList";
    }

    @PostMapping(value ="/cancel")
    public String cancelAppointment(Model model, @RequestParam("id")UUID id) {
        bookingService.cancelAppointment(id);
        ArrayList<Person> records = bookingService.getWaitingList();
        model.addAttribute("records", records);
        return "waitingList";
    }

    @PostMapping(value = "/prioritize")
    public String prioritizeAppointment(Model model, @RequestParam("id")UUID id) {
        bookingService.prioritizeAppointment(id);
        ArrayList<Person> records = bookingService.getWaitingList();
        model.addAttribute("records", records);
        return "waitingList";
    }
}

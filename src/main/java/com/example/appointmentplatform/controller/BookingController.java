package com.example.appointmentplatform.controller;

import com.example.appointmentplatform.model.Person;
import com.example.appointmentplatform.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;



    @GetMapping(value = "/")
    public String getAppointmentBookingPage(Model model){
        model.addAttribute("person", new Person());
        return "appointmentBook";
    }

    @PostMapping(value ="/success")
    public String showSubmittedAppointment(Model model, @ModelAttribute("person")Person person){
       model.addAttribute("person", person);
       model.addAttribute("success", true);
       bookingService.makeAppointment(person);
       return "bookingSuccess";
    }

}

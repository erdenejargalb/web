package com.example.web.controller;

import com.example.web.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(){
        return "It will show order Form html";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        if(errors.hasErrors()){
            return "Order validation error: " + errors.toString();
        }
        //Send post request to localhost:8080/orders it will redirect to home
        log.info("Order submitted");
        //It will get Order object from html form and then save it to db
        //But we will do it later
        return "redirect:/";
    }
}

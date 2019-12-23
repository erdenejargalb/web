package com.example.web.controller;

import com.example.web.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public List<Ingredient> showDesign(){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO","Corn Tortilla", Ingredient.Type.PROTEIN),
                new Ingredient("LETC","Lettuce", Ingredient.Type.SAUCE),
                new Ingredient("SLSA","Salsa", Ingredient.Type.VEGGIES)
        );
        return ingredients;
    }

    @PostMapping
    public String processDesign(){
        log.info("Processing design");
        //It will redirect to localhost:8080/orders/current url
        return "redirect:/orders/current";
    }

}

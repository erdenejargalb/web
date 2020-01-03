package com.example.web.controller;

import com.example.web.data.IngredientRepository;
import com.example.web.data.TacoRepository;
import com.example.web.model.Ingredient;
import com.example.web.model.Order;
import com.example.web.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/design")
public class DesignTacoController {

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo){
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }
    @GetMapping
    public List<Ingredient> showDesign(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add); // i -> ingredients.add(i) replaced by reference method, both work fine
        return ingredients;
    }

    @PostMapping
    public String processDesign(
            @RequestBody @Valid Taco design, Errors errors,
            @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "taco validation error: " + errors.toString();
        }
        System.out.println(design);
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        log.info("Processing design");
        //It will redirect to localhost:8080/orders/current url
        return "redirect:/orders/current";
    }

}

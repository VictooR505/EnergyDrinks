package com.example.energydrink.drink;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@CrossOrigin(origins = "http://localhost:4200/")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping()
    public List<Drink> getAll(@RequestParam(required = false, defaultValue = "") List<String> brands,
                              @RequestParam(required = false, defaultValue = "") List<String> flavours,
                              @RequestParam(required = false, defaultValue = "75") float sugarMax,
                              @RequestParam(required = false, defaultValue = "0")  float sugarMin,
                              @RequestParam(required = false, defaultValue = "id") String sortBy,
                              @RequestParam(required = false,  defaultValue = "ASC") String sortOrder
    ){
        return drinkService.getAll(brands, flavours, sugarMax ,sugarMin, sortBy, sortOrder);
    }

    @GetMapping("/{id}")
    public Drink getDrink(@PathVariable Long id){
        return drinkService.getDrink(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addDrink(@RequestBody Drink drink){
        drinkService.addDrink(drink);
    }

    @PatchMapping("/{id}")
    public void updateDrink(@PathVariable Long id, @RequestBody DrinkUpdateDTO updateDTO){
        drinkService.updateDrink(id, updateDTO);
    }

    @GetMapping("/brands")
    public List<String> getAllBrands(){
        return drinkService.getAllBrands();
    }

    @GetMapping("/flavours")
    public List<String> getAllFlavours(){
        return drinkService.getAllFlavours();
    }

}

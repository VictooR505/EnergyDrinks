package com.example.energydrink.drink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    Logger logger = LoggerFactory.getLogger(DrinkService.class);

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public Drink getDrink(Long id){
        return drinkRepository.findById(id).get();
    }

    public void addDrink(Drink drink){
        drinkRepository.save(drink);
    }

    public void updateDrink(Long id, DrinkUpdateDTO updateDTO){
        Drink drink = drinkRepository.findById(id).get();
        drink.setBrand(updateDTO.brand());
        drink.setName(updateDTO.name());
        drink.setSugar(updateDTO.sugar());
        drink.setFlavour(updateDTO.flavour());
        drink.setAdditionalInfo(updateDTO.additionalInfo());
        drinkRepository.save(drink);
    }

    public List<Drink> getAll(List<String> brand, List<String> flavour, float sugarMax, float sugarMin,
                                            String sortBy, String sortOrder){
        brand = brand.isEmpty() ? drinkRepository.getAllBrands() : brand;
        flavour = flavour.isEmpty() ? drinkRepository.getAllFlavours() : flavour;

        if(sortBy.equals("name")){
            Sort sort = Sort.by(
                    Sort.Order.by("brand").with(Sort.Direction.valueOf(sortOrder.toUpperCase())),
                    Sort.Order.by("name").with(Sort.Direction.valueOf(sortOrder.toUpperCase()))
            );
            return drinkRepository.findAll(brand, flavour, sugarMax, sugarMin, sort);
        }
        return drinkRepository.findAll(brand, flavour, sugarMax, sugarMin, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy));
    }


    public List<String> getAllBrands(){
        return drinkRepository.getAllBrands().stream().sorted().collect(Collectors.toList());
    }

    public List<String> getAllFlavours(){
        return drinkRepository.getAllFlavours().stream().sorted().collect(Collectors.toList());
    }

}

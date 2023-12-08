package com.example.energydrink.drink;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository <Drink, Long> {

    //List<Drink> findAll();

    @Query("SELECT d FROM drinks d  WHERE (d.brand IN (:brands)) AND (d.flavour IN (:flavours)) AND (d.sugar>(:sugarMin)) AND (d.sugar<(:sugarMax))")
    List<Drink> findAll(List<String> brands, List<String> flavours, float sugarMax, float sugarMin, Sort by);

    @Query("SELECT DISTINCT d.brand FROM drinks d")
    List<String> getAllBrands();

    @Query("SELECT DISTINCT d.flavour FROM drinks d")
    List<String> getAllFlavours();
}

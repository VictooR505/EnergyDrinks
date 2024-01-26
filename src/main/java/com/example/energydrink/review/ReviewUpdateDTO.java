package com.example.energydrink.review;

import com.example.energydrink.drink.Drink;

import java.time.LocalDate;

public record ReviewUpdateDTO(
        Drink drink,
        LocalDate date,
        double rating,
        String positive,
        String negative) {
}

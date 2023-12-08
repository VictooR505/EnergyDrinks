package com.example.energydrink.review;

import com.example.energydrink.drink.Drink;

import java.time.LocalDate;

public record ReviewUpdateDTO(
        Drink drink,
        LocalDate date,
        int rating,
        String positive,
        String negative) {
}

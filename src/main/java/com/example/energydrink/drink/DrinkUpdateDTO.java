package com.example.energydrink.drink;

public record DrinkUpdateDTO(
        String brand,
        String name,
        float sugar,
        String flavour,
        String additionalInfo) {
}

package com.example.energydrink.review;

import com.example.energydrink.review.enums.Reviewer;
import com.example.energydrink.review.enums.Size;

import java.time.LocalDate;

public record ReviewListDTO(Long id,
                            String brand,
                            String name,
                            String flavour,
                            float sugar,
                            Reviewer reviewer,
                            LocalDate date,
                            double rating,
                            String positive,
                            String negative,
                            Size size) {

}

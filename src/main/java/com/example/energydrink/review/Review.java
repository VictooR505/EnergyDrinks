package com.example.energydrink.review;

import com.example.energydrink.drink.Drink;
import com.example.energydrink.review.enums.Reviewer;
import com.example.energydrink.review.enums.Size;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;
    private Reviewer reviewer;
    private LocalDate date;
    private double rating;
    private String positive;
    private String negative;
    private Size size;

    public Review(Long id, Drink drink, Reviewer reviewer, LocalDate date, double rating, String positive, String negative, Size size) {
        this.id = id;
        this.drink = drink;
        this.reviewer = reviewer;
        this.date = date;
        this.rating = rating;
        this.positive = positive;
        this.negative = negative;
        this.size = size;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}

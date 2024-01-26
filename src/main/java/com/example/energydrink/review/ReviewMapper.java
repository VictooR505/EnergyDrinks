package com.example.energydrink.review;

import com.example.energydrink.drink.Drink;

import java.util.Optional;

public class ReviewMapper {

        public static ReviewListDTO toReviewListDTO(Review review) {
            if (review == null) {
                return null;
            }

            return new ReviewListDTO(
                    review.getId(),
                    Optional.ofNullable(review.getDrink()).map(Drink::getBrand).orElse(null),
                    Optional.ofNullable(review.getDrink()).map(Drink::getName).orElse(null),
                    Optional.ofNullable(review.getDrink()).map(Drink::getFlavour).orElse(null),
                    Optional.ofNullable(review.getDrink()).map(Drink::getSugar).orElse(0.0f),
                    review.getReviewer(),
                    review.getDate(),
                    review.getRating(),
                    review.getPositive(),
                    review.getNegative(),
                    review.getSize()
            );
        }

    public static Review toReview(ReviewListDTO reviewDto) {
        if (reviewDto == null) {
            return null;
        }

        Review review = new Review();
        review.setId(reviewDto.id());

        Drink drink = new Drink();
        drink.setBrand(reviewDto.brand());
        drink.setName(reviewDto.name());
        drink.setFlavour(reviewDto.flavour());
        drink.setSugar(reviewDto.sugar());

        review.setDrink(drink);
        review.setReviewer(reviewDto.reviewer());
        review.setDate(reviewDto.date());
        review.setRating(reviewDto.rating());
        review.setPositive(reviewDto.positive());
        review.setNegative(reviewDto.negative());
        review.setSize(reviewDto.size());

        return review;
    }
}

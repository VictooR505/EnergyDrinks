package com.example.energydrink.review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    public Review getReview(Long id){
        return reviewRepository.findById(id).get();
    }

    public void addReview(Review review){
        reviewRepository.save(review);
    }

    public void updateReview(Long id, ReviewUpdateDTO updateDTO){
        Review review = reviewRepository.findById(id).get();
        review.setDrink(updateDTO.drink());
        review.setDate(updateDTO.date());
        review.setRating(updateDTO.rating());
        review.setPositive(updateDTO.positive());
        review.setNegative(updateDTO.negative());
        reviewRepository.save(review);
    }
}

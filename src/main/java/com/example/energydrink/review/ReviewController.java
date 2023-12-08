package com.example.energydrink.review;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getAll(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable Long id){
        return reviewService.getReview(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addReview(@RequestBody Review review){
        reviewService.addReview(review);
    }

    @PatchMapping("/{id}")
    public void updateReview(@PathVariable Long id, @RequestBody ReviewUpdateDTO updateDTO){
        reviewService.updateReview(id, updateDTO);
    }
}

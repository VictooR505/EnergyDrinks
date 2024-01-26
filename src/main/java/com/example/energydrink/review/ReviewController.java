package com.example.energydrink.review;

import com.example.energydrink.review.enums.Reviewer;
import com.example.energydrink.review.enums.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200/")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewListDTO> getAll(@RequestParam(required = false, defaultValue = "") List<String> brands,
                               @RequestParam(required = false, defaultValue = "") List<String> flavours,
                               @RequestParam(required = false, defaultValue = "75") float sugarMax,
                               @RequestParam(required = false, defaultValue = "0")  float sugarMin,
                               @RequestParam(required = false, defaultValue = "")  List<Reviewer> reviewers,
                               @RequestParam(required = false, defaultValue = "2020-01-01") LocalDate startDate,
                               @RequestParam(required = false, defaultValue = "2030-01-01")  LocalDate endDate,
                               @RequestParam(required = false, defaultValue = "10") float ratingMax,
                               @RequestParam(required = false, defaultValue = "0")  float ratingMin,
                               @RequestParam(required = false, defaultValue = "")  List<Size> sizes,
                               @RequestParam(required = false, defaultValue = "id") String sortBy,
                               @RequestParam(required = false,  defaultValue = "ASC") String sortOrder){
        return reviewService.getAll(brands,flavours,sugarMax,sugarMin,reviewers,startDate,endDate,ratingMax,ratingMin,sizes,sortBy,sortOrder);
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

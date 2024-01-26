package com.example.energydrink.review;

import com.example.energydrink.drink.DrinkRepository;
import com.example.energydrink.review.enums.Reviewer;
import com.example.energydrink.review.enums.Size;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final DrinkRepository drinkRepository;

    public ReviewService(ReviewRepository reviewRepository, DrinkRepository drinkRepository) {
        this.reviewRepository = reviewRepository;
        this.drinkRepository = drinkRepository;
    }

    public List<ReviewListDTO> getAll(List<String> brand, List<String> flavour, float sugarMax, float sugarMin, List<Reviewer> reviewers,
                               LocalDate startDate, LocalDate endDate, double ratingMax, double ratingMin, List<Size> sizes, String sortBy, String sortOrder){

        if(sortBy.equals("flavour") || sortBy.equals("name") || sortBy.equals("sugar")){
            sortBy="drink."+sortBy; //niepotrzebne, ale pamietaj o dobrym value na froncie
        }

        brand = brand.isEmpty() ? drinkRepository.getAllBrands() : brand;
        flavour = flavour.isEmpty() ? drinkRepository.getAllFlavours() : flavour;
        reviewers = reviewers.isEmpty() ? List.of(Reviewer.values()) : reviewers;
        sizes = sizes.isEmpty() ? List.of(Size.values()) : sizes;

        if(sortBy.equals("drink.name")){
            Sort sort = Sort.by(
                    Sort.Order.by("drink.brand").with(Sort.Direction.valueOf(sortOrder.toUpperCase())),
                    Sort.Order.by("drink.name").with(Sort.Direction.valueOf(sortOrder.toUpperCase()))
            );
            return reviewRepository.findAll(brand, flavour, sugarMax, sugarMin, reviewers, startDate, endDate, ratingMax, ratingMin, sizes,
                    sort).stream().map(ReviewMapper::toReviewListDTO).toList();
        }

        return reviewRepository.findAll(brand, flavour, sugarMax, sugarMin, reviewers, startDate, endDate, ratingMax, ratingMin, sizes,
                Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy)).stream().map(ReviewMapper::toReviewListDTO).toList();

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

/*    public List<Review> findLatestReviewsForEveryDrink(List<Review> reviews) {

        Map<Long, Review> latestReviewsMap = new HashMap<>();

        for (Review review : reviews) {
            Long drinkId = review.getDrink().getId();
            LocalDate reviewDate = review.getDate();

            if (!latestReviewsMap.containsKey(drinkId) || reviewDate.isAfter(latestReviewsMap.get(drinkId).getDate())) {
                latestReviewsMap.put(drinkId, review);
            }
        }
        return latestReviewsMap.values().stream().toList();
    }*/
}

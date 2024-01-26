package com.example.energydrink.review;

import com.example.energydrink.review.enums.Reviewer;
import com.example.energydrink.review.enums.Size;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

//@Query("SELECT r FROM reviews r JOIN r.drink d WHERE (d.brand IN (:brands)) AND (d.flavour IN (:flavours)) AND (d.sugar>(:sugarMin-0.1)) AND (d.sugar<(:sugarMax+0.1)) AND (r.reviewer IN (:reviewers)) AND (r.date BETWEEN (:startDate) AND (:endDate)) AND (r.rating>(:ratingMin-0.1)) AND (r.rating<(:ratingMax+0.1)) AND (r.size IN (:sizes))")
@Query("SELECT r FROM reviews r " +
        "JOIN r.drink d " +
        "WHERE EXISTS " +
        "(SELECT 1 FROM reviews subR " +
        "WHERE subR.drink.id = r.drink.id AND subR.reviewer = r.reviewer " +
        "GROUP BY subR.reviewer, subR.drink " +
        "HAVING MAX(subR.date) = r.date) " +
        "AND d.brand IN :brands " +
        "AND d.flavour IN :flavours " +
        "AND d.sugar > :sugarMin - 0.1 " +
        "AND d.sugar < :sugarMax + 0.1 " +
        "AND r.reviewer IN :reviewers " +
        "AND r.date BETWEEN :startDate AND :endDate " +
        "AND r.rating > :ratingMin - 0.1 " +
        "AND r.rating < :ratingMax + 0.1 " +
        "AND r.size IN :sizes")
List<Review> findAll(List<String> brands, List<String> flavours, float sugarMax, float sugarMin, List<Reviewer> reviewers,
                     LocalDate startDate, LocalDate endDate, double ratingMax, double ratingMin, List<Size> sizes, Sort by);
}

package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.idc.capstone.model.ReviewEntity;
import pers.idc.capstone.repo.ReviewRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewEntity add(ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }

    public List<ReviewEntity> getRandom(int number) {
        long count = reviewRepository.count();
        number = (int) Math.min(number, count);
        if (number <= 0) return List.of();
        return new Random()
                .ints(1, (int) count + 1)
                .distinct()
                .limit(number)
                .boxed()
                .map(x -> reviewRepository.findById((long) x).orElseThrow())
                .collect(Collectors.toList());
    }
}

package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.idc.capstone.model.ReviewEntity;
import pers.idc.capstone.service.ReviewService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{number}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ReviewEntity>> getRandom(@PathVariable("number") int number) {
        try {
            return ResponseEntity.ok(reviewService.getRandom(number));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest()
                    .header("Message", "Error fetching reviews.")
                    .build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_BASIC', 'ROLE_ADMIN')")
    public ResponseEntity<ReviewEntity> add(@RequestBody ReviewEntity reviewEntity) {
        return ResponseEntity.ok(reviewService.add(reviewEntity));
    }
}

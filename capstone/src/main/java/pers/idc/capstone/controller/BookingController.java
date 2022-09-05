package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.BookingEntity;
import pers.idc.capstone.model.BookingSlot;
import pers.idc.capstone.service.BookingService;

import java.sql.Date;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BASIC')")
    public ResponseEntity<BookingEntity> book(@RequestBody BookingEntity bookingEntity) {
        try {
            return ResponseEntity.ok(bookingService.book(bookingEntity));
        } catch (UniqueConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Boolean> isSlotAvailable(
            @RequestParam("city") String city,
            @RequestParam("hospital") String hospital,
            @RequestParam("date") Date date,
            @RequestParam("bookingSlot") BookingSlot bookingSlot) {
        return ResponseEntity.ok(bookingService.isSlotAvailable(city, hospital, date, bookingSlot));
    }
}

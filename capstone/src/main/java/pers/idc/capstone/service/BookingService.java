package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.BookingEntity;
import pers.idc.capstone.model.BookingSlot;
import pers.idc.capstone.repo.BookingRepository;

import java.sql.Date;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public BookingEntity book(BookingEntity bookingEntity) {
        boolean isPresent = bookingRepository.findByCityAndHospitalAndDateAndBookingSlot(
                bookingEntity.getCity(),
                bookingEntity.getHospital(),
                bookingEntity.getDate(),
                bookingEntity.getBookingSlot()
        ).isPresent();
        if (isPresent) throw new UniqueConstraintViolationException(
                "City, hospital, date and slot",
                String.format(
                        "%s %s %s %s",
                        bookingEntity.getCity(),
                        bookingEntity.getHospital(),
                        bookingEntity.getDate(),
                        bookingEntity.getBookingSlot()
                )
        );

        return bookingRepository.save(bookingEntity);
    }

    public boolean isSlotAvailable(String city, String hospital, Date date, BookingSlot bookingSlot) {
        boolean isPresent = bookingRepository.findByCityAndHospitalAndDateAndBookingSlot(
                city, hospital, date, bookingSlot).isPresent();
        return !isPresent;
    }
}

package pers.idc.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.idc.capstone.model.BookingEntity;
import pers.idc.capstone.model.BookingSlot;

import java.sql.Date;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    Optional<BookingEntity> findByCityAndHospitalAndDateAndBookingSlot(
            String city,
            String hospital,
            Date date,
            BookingSlot bookingSlot
    );
}

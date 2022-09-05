package pers.idc.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking_entity_details")
public class BookingEntity {
    @Id
    @SequenceGenerator(name = "booking_entity_sequence", allocationSize = 1)
    @GeneratedValue(generator = "booking_entity_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String city;
    private String hospital;
    private Date date;
    private BookingSlot bookingSlot;
}

package pers.idc.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review_entity_details")
public class ReviewEntity {
    @Id
    @SequenceGenerator(name = "review_entity_sequence", allocationSize = 1)
    @GeneratedValue(generator = "review_entity_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String hospital;
    private String city;
    private String message;
}

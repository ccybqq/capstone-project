package pers.idc.capstone.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "blood_registry_entity_details")
public class BloodRegistryEntity {
    @Id
    @SequenceGenerator(name = "blood_registry_entity_sequence", allocationSize = 1)
    @GeneratedValue(generator = "blood_registry_entity_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private BloodGroup bloodGroup;
    private State state;
    private Area area;
    private Integer pinCode;
    private Boolean required;
    private Boolean available;
}

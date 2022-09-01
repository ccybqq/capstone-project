package pers.idc.capstone.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_entity_details")
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_entity_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_entity_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private Float weight;
    private BloodGroup bloodGroup;
    private String contactNumber;
    private State state;
    private Area area;
    private Integer postalCode;

    public long getAge() {
        return ChronoUnit.YEARS.between(dateOfBirth.toLocalDate(), LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return email != null && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

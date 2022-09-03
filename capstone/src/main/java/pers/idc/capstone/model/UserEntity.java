package pers.idc.capstone.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(
        name = "user_entity_details",
        uniqueConstraints = {
                @UniqueConstraint(name = "Email", columnNames = "email")
        }
)
public class UserEntity implements Serializable {
    @Id
    @SequenceGenerator(name = "user_entity_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_entity_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @Transient
    private Long age;
    private Gender gender;
    private Float weight;
    private BloodGroup bloodGroup;
    private String contactNumber;
    private State state;
    private Area area;
    private Integer pinCode;

    @PostPersist
    @PostLoad
    @PostUpdate
    public void computeAge() {
        setAge(ChronoUnit.YEARS.between(dateOfBirth.toLocalDate(), LocalDate.now()));
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

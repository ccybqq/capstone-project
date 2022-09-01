package pers.idc.capstone.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
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
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date date;
    private Gender gender;
    private float weight;
    private BloodGroup bloodGroup;
    private String contactNumber;
    private State state;
    private Area area;
    private int postalCode;

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

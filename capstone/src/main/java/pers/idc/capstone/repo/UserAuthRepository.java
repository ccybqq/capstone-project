package pers.idc.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pers.idc.capstone.model.UserAuth;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    @Query("SELECT u FROM UserAuth u JOIN u.userEntity e WHERE e.email = :email")
    Optional<UserAuth> findByEmail(@Param("email") String email);
}

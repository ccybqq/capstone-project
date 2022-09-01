package pers.idc.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.idc.capstone.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    void deleteByEmail(String email);
}

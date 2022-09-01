package pers.idc.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.idc.capstone.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}

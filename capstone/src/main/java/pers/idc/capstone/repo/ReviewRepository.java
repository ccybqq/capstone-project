package pers.idc.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.idc.capstone.model.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}

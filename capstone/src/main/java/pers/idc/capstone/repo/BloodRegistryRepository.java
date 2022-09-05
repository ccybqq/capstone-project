package pers.idc.capstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.idc.capstone.model.Area;
import pers.idc.capstone.model.BloodGroup;
import pers.idc.capstone.model.BloodRegistryEntity;
import pers.idc.capstone.model.State;

import java.util.Optional;

public interface BloodRegistryRepository extends JpaRepository<BloodRegistryEntity, Long> {
    Optional<BloodRegistryEntity> findByBloodGroupAndStateAndAreaAndPinCodeAndRequiredAndAvailable(
            BloodGroup bloodGroup,
            State state,
            Area area,
            Integer pinCode,
            Boolean required,
            Boolean available
    );
    Optional<BloodRegistryEntity> findByBloodGroupAndStateAndAreaAndPinCode(
            BloodGroup bloodGroup,
            State state,
            Area area,
            Integer pinCode
    );
    Optional<BloodRegistryEntity> findByIdAndPinCode(
            Long id,
            Integer pinCode
    );
}

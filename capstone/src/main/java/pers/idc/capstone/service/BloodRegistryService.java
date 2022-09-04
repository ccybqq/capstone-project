package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.BloodRegistryEntity;
import pers.idc.capstone.repo.BloodRegistryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BloodRegistryService {
    private BloodRegistryRepository bloodRegistryRepository;

    @Autowired
    public BloodRegistryService(BloodRegistryRepository bloodRegistryRepository) {
        this.bloodRegistryRepository = bloodRegistryRepository;
    }

    public BloodRegistryEntity save(BloodRegistryEntity bloodRegistryEntity) {
        // Prevent overwriting of existing entry.
        if (bloodRegistryEntity.getId() != null) throw new IdNotNullException();
        Optional<BloodRegistryEntity> bloodRecord = bloodRegistryRepository.findByBloodGroupAndStateAndAreaAndPinCode(
                bloodRegistryEntity.getBloodGroup(),
                bloodRegistryEntity.getState(),
                bloodRegistryEntity.getArea(),
                bloodRegistryEntity.getPinCode()
        );
        // Prevents duplication of blood group, state, area, pin code combination.
        if (bloodRecord.isPresent())
            throw new UniqueConstraintViolationException(
                    "Specification",
                    String.format(
                            "{%s, %s, %s, %s}",
                            bloodRegistryEntity.getBloodGroup(),
                            bloodRegistryEntity.getState(),
                            bloodRegistryEntity.getArea(),
                            bloodRegistryEntity.getPinCode()
                    )
            );
        return bloodRegistryRepository.save(bloodRegistryEntity);
    }

    public Optional<BloodRegistryEntity> findByAll(BloodRegistryEntity bloodRegistryEntity) {
        return bloodRegistryRepository.findByBloodGroupAndStateAndAreaAndPinCodeAndRequiredAndAvailable(
                bloodRegistryEntity.getBloodGroup(),
                bloodRegistryEntity.getState(),
                bloodRegistryEntity.getArea(),
                bloodRegistryEntity.getPinCode(),
                bloodRegistryEntity.getRequired(),
                bloodRegistryEntity.getAvailable()
        );
    }

    public BloodRegistryEntity findBySpecification(BloodRegistryEntity bloodRegistryEntity) {
        System.out.println(bloodRegistryEntity.toString());
        return bloodRegistryRepository.findByBloodGroupAndStateAndAreaAndPinCode(
                bloodRegistryEntity.getBloodGroup(),
                bloodRegistryEntity.getState(),
                bloodRegistryEntity.getArea(),
                bloodRegistryEntity.getPinCode()
        ).orElseThrow(() -> new NoSuchElementException("Blood record not found."));
    }

    public List<BloodRegistryEntity> findAll() {
        return bloodRegistryRepository.findAll();
    }

    // Update only, does not persist.
    public BloodRegistryEntity update(BloodRegistryEntity bloodRegistryEntity) {
        long id = findBySpecification(bloodRegistryEntity).getId();
        bloodRegistryEntity.setId(id);
        return bloodRegistryRepository.save(bloodRegistryEntity);
    }
}

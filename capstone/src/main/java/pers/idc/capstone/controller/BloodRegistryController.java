package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.BloodRegistryEntity;
import pers.idc.capstone.service.BloodRegistryService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/registry")
public class BloodRegistryController {
    private final BloodRegistryService bloodRegistryService;

    @Autowired
    public BloodRegistryController(BloodRegistryService bloodRegistryService) {
        this.bloodRegistryService = bloodRegistryService;
    }

    @PostMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_BASIC', 'ROLE_ADMIN')")
    public ResponseEntity<BloodRegistryEntity> findSpecification(@RequestBody BloodRegistryEntity bloodRegistryEntity) {
        try {
            return ResponseEntity.ok(bloodRegistryService.findBySpecification(bloodRegistryEntity));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BloodRegistryEntity> add(@RequestBody BloodRegistryEntity bloodRegistryEntity) {
        try {
            return ResponseEntity.ok(bloodRegistryService.save(bloodRegistryEntity));
        } catch (IdNotNullException | UniqueConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }
}

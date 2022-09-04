package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.UserEntity;
import pers.idc.capstone.repo.UserRepository;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity userEntity) {
        // Non-null id risks overwriting another entry.
        if (userEntity.getId() != null) throw new IdNotNullException();
        // Email must be unique.
        String email = userEntity.getEmail();
        if (userRepository.findByEmail(email).isPresent()) throw new UniqueConstraintViolationException("Email", email);

        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity userEntity) {
        // Email must be unique.
        String email = userEntity.getEmail();
        if (userRepository.findByEmail(email).isPresent()) throw new UniqueConstraintViolationException("Email", email);

        userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new NoSuchElementException("User does not exist."));
        return userRepository.save(userEntity);
    }

    public UserEntity findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}

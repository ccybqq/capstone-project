package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.model.UserEntity;
import pers.idc.capstone.repo.UserRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity userEntity) throws SQLIntegrityConstraintViolationException {
        // Non-null id risks overwriting another entry.
        if (userEntity.getId() != null) throw new IdNotNullException();
        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity userEntity) {
        if (
                userEntity.getId() == null ||
                userRepository.findById(userEntity.getId()).isEmpty()
        ) throw new NoSuchElementException();
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

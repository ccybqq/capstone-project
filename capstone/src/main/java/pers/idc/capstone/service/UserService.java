package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findById(String id) throws NoSuchElementException{
        return userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
